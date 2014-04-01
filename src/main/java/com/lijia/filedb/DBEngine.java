package com.lijia.filedb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.lijia.filedb.annotation.Column;
import com.lijia.filedb.annotation.Table;
import com.lijia.filedb.annotation.Type;
import com.lijia.filedb.exception.ColumnValidationException;
import com.lijia.filedb.exception.NotRegisterException;
import com.lijia.filedb.test.TestObj;
import com.lijia.filedb.util.ObjectUtil;

public class DBEngine {
	static int HEAD_LENGTH=32;
	static int COLUNN_HEAD_LENGTH=8;
	static byte[] head = new byte[HEAD_LENGTH];
	static byte FILL = 0;
	static byte NORMAL = (byte)' ';
	static byte DELETE = 1;
	static {
		for (int i = 0; i < head.length; i++) {
			head[i] = FILL;
		}
	}
	Map<String, _table> map = new HashMap<String, _table>();

	private static DBEngine instance = new DBEngine();

	public static DBEngine getDBEngine() {
		return instance;
	}

	private String filepath;

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public void registerClass(Class _class) throws NotRegisterException,
			ColumnValidationException {
		if (_class == null) {
			throw new NotRegisterException("class is null");
		}

		Table table = (Table) _class.getAnnotation(Table.class);
		if (table == null) {
			throw new NotRegisterException(_class.getName()
					+ " not annotation Table");
		}
		String schema = table.schema();
		_table _table = new _table();

		String name = table.name();
		String unicode = table.unicode();
		_table.setName(name);
		_table.setUnicode(unicode);
		_table.setSchema(schema);
		_table.set_class(_class);
		Field[] fs = _class.getDeclaredFields();
		int length = 8;
		for (Field f : fs) {
			_column _column = new _column();
			Column column = f.getAnnotation(Column.class);
			_column.setIsid(column.id());
			_column.setName(f.getName());
			if(column.id()){
				if(_table.getPk()!=null){
					throw new ColumnValidationException(
							"a table must just one primary key."
									+ _table.getName());
				}
				_table.setPk(_column);
			}

			if (column.type().equals(Type.auto)) {
				if (f.getType().equals(Integer.class)) {
					_column.setType(Type.integer);
				} else {
					_column.setType(Type.String);
				}
			}
			if (_column.getType().equals(Type.integer)) {
				_column.setLength(11);
			} else {
				if (column.length() < 0) {
					_column.setLength(200);
				} else {
					if (column.length() > 2048) {

						throw new ColumnValidationException(
								"The string length is max 2048, current value:"
										+ column.length());
					} 
					_column.setLength(column.length());
				}
			}
			length+=_column.getLength();
			_table.getColumns().put(_column.getName(), _column);
		}
		_table.setLength(length);
		map.put(_table.getName(), _table);
		checkdbfile(_table);
	}

	private void checkdbfile(_table _table) {
		File dbfile = new File(this.getFilepath(), _table.getName());
		if (!dbfile.exists()) {
			try {
				dbfile.createNewFile();
				initDBFileHead(dbfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void initDBFileHead(File dbfile) throws IOException {
		OutputStream ops = new FileOutputStream(dbfile);
		ops.write(head);
		ops.flush();
		ops.close();
	}

	

	

	public void add(Object obj) throws NotRegisterException, IOException, InterruptedException, ExecutionException {
		Table table = obj.getClass().getAnnotation(Table.class);
		if (table == null) {
			throw new NotRegisterException(obj.getClass()
					+ " not annotation Table");
		}
		_table t = map.get(table.name());
		String tablename = t.getName();
		String schema = t.getSchema();
		String unicode = t.getUnicode();
		
		byte[] w = obj2byte(obj,t);
		
		
		Future<?> re = add2DB(t,w);
		
	}

	public static byte[] obj2byte(Object obj,  _table t) throws UnsupportedEncodingException {
		LinkedHashMap<String, _column> columelist = t.getColumns();
		byte[] w= new byte[t.getLength()];
		writebytefill(w,0,8,FILL);
		int start = 8;
		for (Map.Entry<String, _column> entry : columelist.entrySet()) {
			_column c = entry.getValue();
			Object val = ObjectUtil.getFieldValue(obj, c.getName());
			writebytevall(w,start,c.getLength(),val==null?null:(val.toString().getBytes(t.getUnicode())),(byte)0);
			start +=c.getLength();
		}
		return w;
	}

	private Future<?> add2DB(_table t, byte[] w) throws IOException, InterruptedException, ExecutionException {
		return FileDB.addAColumn(t,w);
	}

	private static void writebytevall(byte[] w, int start, int length, byte[] content,byte fill) {
		if(content==null || content.length==0){
			for(int i=start;i<start+length && i<w.length;i++){
				w[i] = fill;
			}
		}else{
			int i = start;
			for(;i<start+content.length;i++){
				w[i] = content[i-start];
			}
			
			for(;i<start+length;i++){
				w[i] = fill;
			}
		}
		
	}



	public static void writebytefill(byte[] w, int start, int length, byte fill) {
		for(int i=start;i<start+length;i++){
			w[i] = fill;
		}
	}

	public Object find(Class class1, Object val) throws NotRegisterException, InterruptedException, ExecutionException, FileNotFoundException, InstantiationException, IllegalAccessException, UnsupportedEncodingException {
		Table table = (Table) class1.getAnnotation(Table.class);
		if (table == null) {
			throw new NotRegisterException(class1
					+ " not annotation Table");
		}
		_table t = map.get(table.name());
		String tablename = t.getName();
		String schema = t.getSchema();
		String unicode = t.getUnicode();
		LinkedHashMap<String, _column> columelist = t.getColumns();
		int start = COLUNN_HEAD_LENGTH;
		_column c = null;
		for (Map.Entry<String, _column> entry : columelist.entrySet()) {
			c = entry.getValue();
			if(c.isIsid()){
				break;
			}else{
				start +=c.getLength();
			}
			
		}
		Object obj = findDB(t,c,start,val,Operation.eq);
		return obj;
	}

	private Object findDB(_table t, _column c, int start, Object val,Operation operation) throws InterruptedException, ExecutionException, FileNotFoundException, InstantiationException, IllegalAccessException, UnsupportedEncodingException {
		Future<byte[]> res = FileDB.findcondition(t,c,start,val,operation);
		byte[] dbstr = res.get();
		return MappingHelp.str2Obj(dbstr,t,c);
	}

	public void update(Object obj) throws NotRegisterException, FileNotFoundException {
		Table table = (Table) obj.getClass().getAnnotation(Table.class);
		if (table == null) {
			throw new NotRegisterException(obj
					+ " not annotation Table");
		}
		_table t = map.get(table.name());
		String tablename = t.getName();
		String schema = t.getSchema();
		String unicode = t.getUnicode();
		LinkedHashMap<String, _column> columelist = t.getColumns();
		int start = COLUNN_HEAD_LENGTH;
		_column c = null;
		Object idval = null;
		for (Map.Entry<String, _column> entry : columelist.entrySet()) {
			c = entry.getValue();
			if(c.isIsid()){
				idval = ObjectUtil.getFieldValue(obj, c.getName());
				break;
			}else{
				start +=c.getLength();
			}
		}
		findandupdateDB(t,c,start,obj,idval);
	}

	private void findandupdateDB(_table t, _column c, int start, Object obj,Object idval) throws FileNotFoundException {

		FileDB.update(t,c,start,obj,idval);
	
	}

	public void delete(Class obj, Object idval) throws NotRegisterException, FileNotFoundException {
		Table table = (Table) obj.getAnnotation(Table.class);
		if (table == null) {
			throw new NotRegisterException(obj
					+ " not annotation Table");
		}
		_table t = map.get(table.name());
		String tablename = t.getName();
		String schema = t.getSchema();
		String unicode = t.getUnicode();
		LinkedHashMap<String, _column> columelist = t.getColumns();
		int start = COLUNN_HEAD_LENGTH;
		_column c = null;
//		Object idval = null;
		for (Map.Entry<String, _column> entry : columelist.entrySet()) {
			c = entry.getValue();
			if(c.isIsid()){
//				idval = ObjectUtil.getFieldValue(obj, c.getName());
				break;
			}else{
				start +=c.getLength();
			}
		}
		deleteDB(t,start,idval);
	}

	private void deleteDB(_table t, int start, 
			Object idval) {
		FileDB.delete(t,start,idval);
	}

}
