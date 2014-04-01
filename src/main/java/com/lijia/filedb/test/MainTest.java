package com.lijia.filedb.test;


import com.lijia.filedb.DBEngine;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DBEngine.getDBEngine().setFilepath("E:/tool/eclipseself/workspacetool/com.lijia.filedb/db");
			DBEngine.getDBEngine().registerClass(TestObj.class);
			
			TestObj obj = new TestObj();
//			obj.setId(1);
//			obj.setName("li jia");
//			obj.setDescrption("this is lijia. hello world.12345678===()");
//			DBEngine.getDBEngine().add(obj);
//			obj = new TestObj();
//			obj.setId(2);
//			obj.setName("ze xuan");
//			obj.setDescrption("this is zexuan. hello world.12345678===()");
//			DBEngine.getDBEngine().add(obj);
//			obj = new TestObj();
//			obj.setId(3);
//			obj.setName("zhenyan");
//			obj.setDescrption("this is zhenyan. hello world.12345678===()");
//			DBEngine.getDBEngine().add(obj);
			
//			System.out.println(new String((byte[])DBEngine.getDBEngine().find(TestObj.class,1),"utf-8"));
//			obj = (TestObj) DBEngine.getDBEngine().find(TestObj.class,2);
//			System.out.println(obj);
//			System.out.println(obj.getName()+obj.getDescrption());
//			obj.setName("=========");
//			DBEngine.getDBEngine().update(obj);
			
			 DBEngine.getDBEngine().delete(TestObj.class,2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
