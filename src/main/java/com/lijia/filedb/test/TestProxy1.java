package com.lijia.filedb.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxy1 {
	public interface IP{
		public boolean isIsdirty();
		public void setIsdirty(boolean isdirty);
	}
	public static class P implements IP{
		protected boolean isdirty = false;
		public boolean isIsdirty() {
			return isdirty;
		}
		public void setIsdirty(boolean isdirty) {
			this.isdirty = isdirty;
		}
	}
	public static class A extends P implements IA {
		private int a1;
		public int getA1() {
			return a1;
		}
		public void setA1(int a1) {
			this.a1 = a1;
		}
	}
	static interface IA{
		public void setA1(int i);
		public int getA1();
	}
	public static void main(String[] args) {
		final A fa = new A();
		Object a =   Proxy.newProxyInstance(A.class.getClassLoader(),new Class[] { IP.class ,IA.class}, new InvocationHandler(){
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				fa.setIsdirty(true);
				return method.invoke(fa, args);
			}
		});
		System.out.printf("invoke before %s\n\r",((IP)a).isIsdirty());
		((IA)a).setA1(1);
		System.out.printf("geting attribute a1==>>>%d\n\r",((IA)a).getA1());
		System.out.printf("invoke after %s\n\r",((IP)a).isIsdirty());
	}
}
