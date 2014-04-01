package com.lijia.filedb;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Proxy;
import java.util.Map;

import com.lijia.filedb.annotation.Table;
import com.lijia.filedb.annotation.Type;
import com.lijia.filedb.util.ObjectUtil;

public class MappingHelp {

	public static Object str2Obj(byte[] dbstr, _table t, _column c) throws InstantiationException, IllegalAccessException, UnsupportedEncodingException {
		int start = DBEngine.COLUNN_HEAD_LENGTH;
		Object obj = t.get_class().newInstance();
		for (Map.Entry<String, _column> entry : t.getColumns().entrySet()) {
			_column c1 = entry.getValue();
			String str = new String(dbstr,start,c1.getLength(),t.getUnicode());
			if(c1.getType().equals(Type.integer)){
				Integer val = Integer.parseInt(str.trim());
				ObjectUtil.setFieldValue(obj, c1.getName(),val);
			}else{
				ObjectUtil.setFieldValue(obj, c1.getName(),str.trim());
			}
			
			start +=c1.getLength();
		}
		return obj;
	}

}
