package com.lijia.filedb.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

import com.lijia.filedb.DBEngine;
import com.lijia.filedb.exception.ColumnValidationException;
import com.lijia.filedb.exception.NotRegisterException;

public class Ptest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		DBEngine.getDBEngine().setFilepath("E:/tool/eclipseself/workspacetool/com.lijia.filedb/db");
		DBEngine.getDBEngine().registerClass(TestObj.class);
//		testadd();
//		testsearch();
//		testupdate();
		
		int i=2;
		long c = System.currentTimeMillis();
		TestObj obj = (TestObj) DBEngine.getDBEngine().find(TestObj.class,i);
		System.out.println(obj.getName()+obj.getDescrption());
		System.out.println(i+"=="+(System.currentTimeMillis()-c));
		obj.setDescrption("update-----------0000000000000000000");
		DBEngine.getDBEngine().update(obj);
	}

	private static void testupdate() throws Exception {
		int[] ids = {1,1000,2000,3000,4000,5000,6000,7000,8000,9000,10000};
		for(int i:ids){
			long c = System.currentTimeMillis();
			TestObj obj = (TestObj) DBEngine.getDBEngine().find(TestObj.class,i);
			System.out.println(obj.getName()+obj.getDescrption());
			System.out.println(i+"=="+(System.currentTimeMillis()-c));
//			obj.setDescrption("update-----------0000000000000000000");
//			DBEngine.getDBEngine().update(obj);
//			System.out.println(i+"=update="+(System.currentTimeMillis()-c));
		}
		
	}

	private static void testsearch() throws FileNotFoundException, InstantiationException, IllegalAccessException, UnsupportedEncodingException, NotRegisterException, InterruptedException, ExecutionException {
		int[] ids = {1,1000,2000,3000,4000,5000,6000,7000,8000,9000,10000};
		for(int i:ids){
			long c = System.currentTimeMillis();
			TestObj obj = (TestObj) DBEngine.getDBEngine().find(TestObj.class,i);
			System.out.println(obj.getName()+obj.getDescrption());
			System.out.println(i+"=="+(System.currentTimeMillis()-c));
		}
		
	}

	private static void testadd() throws Exception, IOException {
		System.out.println("start");
		long c = System.currentTimeMillis();
		for(int i=1;i<10001;i++){
			TestObj obj = new TestObj();
			obj.setId(i);
			obj.setName("li jia"+i);
			obj.setDescrption("this is lijia"+i+". hello world.12345678===()");
			DBEngine.getDBEngine().add(obj);
			if(i%1000==1){
				System.out.println(i+"=="+(System.currentTimeMillis()-c));
			}
		}
		System.out.println("finish");
		
	}

}
