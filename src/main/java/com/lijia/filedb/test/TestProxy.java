package com.lijia.filedb.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.lijia.filedb.ObjectWrapper;

public class TestProxy {
	public static class A{
		private int a1;
		private int a2;
		public int getA1() {
			return a1;
		}
		public void setA1(int a1) {
			this.a1 = a1;
		}
		public int getA2() {
			return a2;
		}
		public void setA2(int a2) {
			this.a2 = a2;
		}
		
	}

	public static void main(String[] args) {
		ObjectWrapper wrapper = new ObjectWrapper();
		
		System.out.println(A.class.getInterfaces().length);
//		final A a = A.class.newInstance();
//		wrapper.s__t(a);
//		A obj =  (A) Proxy.newProxyInstance(A.class.getClassLoader(), A.class.getInterfaces(), new InvocationHandler(){
//			
//			@Override
//			public Object invoke(Object proxy, Method method, Object[] args)
//					throws Throwable {
//				return method.invoke(a, args);
//			}
//			
//		});
		
//		a.setA1(1);
	}
}
