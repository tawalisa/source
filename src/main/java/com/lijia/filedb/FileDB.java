package com.lijia.filedb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.lijia.filedb.annotation.Type;
import com.lijia.filedb.test.TestObj;
import com.lijia.filedb.util.ConditionUtil;



public class FileDB {
	private static Map<String,RandomAccessFile> map = new Hashtable<String,RandomAccessFile>();
	
	static ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newFixedThreadPool(50);
	static LinkedBlockingQueue<Runnable> runnerqueue = new LinkedBlockingQueue<Runnable>();
	static ThreadPoolExecutor ayncexecutor = new ThreadPoolExecutor(1, 100000, 5, TimeUnit.DAYS,
			runnerqueue);
	
	static class WriteRunnder implements Callable {
		byte[] w;
		FileChannel fc;
		long position ;_table t;
		public WriteRunnder(byte[] w,FileChannel fc, long position, _table t){
			this.w = w;
			this.fc = fc;
			this.position = position;
			this.t = t;
		}
	
		public Object call() throws Exception {
			try {
				FileChannel fc = getWriteFileChannel(t);
				fc.position(fc.size());
				fc.write(ByteBuffer.wrap(w) );
				fc.force(true);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}finally{
//				try {
//					fc.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
		}
		}
	
		
	}

	public static Future<?> addAColumn(_table t, byte[] w) throws IOException, InterruptedException, ExecutionException {
		
		
		WriteRunnder t1 = new WriteRunnder(w,null,0,t);
//		t1.run();
		return ayncexecutor.submit(t1);
//		ayncexecutor.execute(t1);
//		.execute(t1);
	}
	private static FileChannel getWriteFileChannel(_table t) throws FileNotFoundException {
		RandomAccessFile fa = null;
		fa = map.get(t.getName());
		if(fa == null){
		synchronized(t){
				fa = new RandomAccessFile(new File(DBEngine.getDBEngine().getFilepath(), t.getName()), "rw");
				map.put(t.getName(), fa);
			}
		}
		FileChannel re = fa.getChannel();
		return fa.getChannel();
	}


	
	static class FindRunnder implements Callable<byte[]> {
		private _table t;
		private _column c;
		private int start;
		private Object val;
		private Operation operation; 
		
		
		public FindRunnder(_table t, _column c, int start, Object val,
				Operation operation) {
			super();
			this.t = t;
			this.c = c;
			this.start = start;
			this.val = val;
			this.operation = operation;
		}


		public byte[] call() {
			
			try {
				FileChannel fc = getWriteFileChannel(t);
				long filesize = fc.size();
				int position = DBEngine.HEAD_LENGTH+ start;
				byte[] val = new byte[c.getLength()];
				ByteBuffer[] dsts = new ByteBuffer[1];
		         
				
				while(position <= filesize){
					dsts[0]=ByteBuffer.allocate(c.getLength());
					fc.read(dsts[0], position);
					byte[] res = dsts[0].array();
					if(c.getType().equals(Type.integer)){
						Integer id = Integer.parseInt(new String(res,t.getUnicode()).trim());
						if(ConditionUtil.compareInt(id,Integer.parseInt(this.val.toString()),operation)){
							ByteBuffer res1 = ByteBuffer.allocate(t.getLength());
							fc.read(res1, position-start);
							return res1.array();
						}
					}
					position +=t.getLength();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
//				try {
//					fc.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
			}
			return null;
		}
	
		
	}

	public static Future<byte[]> findcondition(_table t, _column c, int start,
			Object val, Operation operation) throws FileNotFoundException {
		
		FindRunnder t1 = new FindRunnder(t,c,start,val,operation);
		return executor.submit(t1);
	}
	public static void update(_table t, _column c, int start, Object obj, Object idval) throws FileNotFoundException {
		
		UpdateRunnder t1 = new UpdateRunnder(t,c,start,obj,idval);
		ayncexecutor.submit(t1);
	}
	static class UpdateRunnder implements Callable {
		_table t; 
		_column c; int start; Object obj;
		Object idval;
		public UpdateRunnder( _table t, _column c, int start, Object obj,Object idval) {
			super();
			this.t = t;
			this.c = c;
			this.start = start;
			this.obj = obj;
			this.idval = idval;
		}

		public Object call() {
			try {
				FileChannel fc = getWriteFileChannel(t);
				long filesize = fc.size();
				int position = DBEngine.HEAD_LENGTH+ start;
				byte[] val = new byte[c.getLength()];
				ByteBuffer[] dsts = new ByteBuffer[1];
		         
				
				while(position <= filesize){
					dsts[0]=ByteBuffer.allocate(c.getLength());
					fc.read(dsts[0], position);
					
					byte[] res = dsts[0].array();
					if(c.getType().equals(Type.integer)){
						Integer id = Integer.parseInt(new String(res,t.getUnicode()).trim());
						if(ConditionUtil.compareInt(id,(int)idval,Operation.eq)){
							
							fc.position(position-start);
							fc.write(ByteBuffer.wrap(DBEngine.obj2byte(obj, t)) );
							fc.force(true);
						}
					}
					position +=t.getLength();
				}
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}finally{
//				try {
//					fc.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
			}
		}
	
		
	}

	public static Future<Boolean> delete(_table t, int start, Object idval) {
		DeleteRunnder runner = new DeleteRunnder(t,start,idval);
		return ayncexecutor.submit(runner);
	}
	
	static class DeleteRunnder implements Callable<Boolean> {
		_table t; 
		int start; 
		Object idval;


		
		public DeleteRunnder(_table t, int start, Object idval) {
			super();
			this.t = t;
			this.start = start;
			this.idval = idval;
		}



		public Boolean call() {
			try {
				FileChannel fc = getWriteFileChannel(t);
				_column c = t.getPk();
				long filesize = fc.size();
				int position = DBEngine.HEAD_LENGTH+ start;
				byte[] val = new byte[c.getLength()];
				ByteBuffer[] dsts = new ByteBuffer[1];
		         
				
				while(position <= filesize){
					dsts[0]=ByteBuffer.allocate(c.getLength());
					fc.read(dsts[0], position);
					
					byte[] res = dsts[0].array();
					if(c.getType().equals(Type.integer)){
						Integer id = Integer.parseInt(new String(res,t.getUnicode()).trim());
						if(ConditionUtil.compareInt(id,(int)idval,Operation.eq)){
							byte[] w= new byte[t.getLength()];
							w[0] = DBEngine.DELETE;
							DBEngine.writebytefill(w,1,w.length-1,DBEngine.FILL);
							fc.position(position-start);
							fc.write(ByteBuffer.wrap(w) );
							fc.force(true);
							return true;
						}
					}
					position +=t.getLength();
				}
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}finally{
//				try {
//					fc.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
			}
		}
	
		
	}
}
