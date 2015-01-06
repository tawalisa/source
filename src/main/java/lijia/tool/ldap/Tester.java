package lijia.tool.ldap;

import java.util.concurrent.TimeUnit;


public abstract class Tester implements Runnable {
	private int count;
	private int mintime;

	private int threadnum;
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getMintime() {
		return mintime;
	}

	public void setMintime(int mintime) {
		this.mintime = mintime;
	}

	
	public int getThreadnum() {
		return threadnum;
	}

	public void setThreadnum(int threadnum) {
		this.threadnum = threadnum;
	}

	public Tester(int count, int mintime,int threadnum) {
		super();
		this.count = count;
		this.mintime = mintime;
		this.threadnum = threadnum;
	}

	
	@Override
	public void run() {
		
		try {
			beforeTest();
			for (int i = 0; i < count; i++) {
				long c = System.currentTimeMillis();

				dotest(i);

				long spenttime = System.currentTimeMillis() - c;
				if ( mintime> spenttime) {
					try {
						TimeUnit.MILLISECONDS.sleep(mintime-spenttime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			afterTest();
		} catch (Exception e1) {
			exceptionTest(e1);
		}
		
	}

	protected void afterTest()throws Exception {
		
	}

	protected void exceptionTest(Exception e1) {
		e1.printStackTrace();
	}

	protected void beforeTest() throws Exception {

	}

	public abstract void dotest(int i,Object... objects) throws Exception;



}
