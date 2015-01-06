package lijia.tool.ldap;

import java.io.IOException;

import org.apache.directory.api.ldap.model.exception.LdapException;

public class LdapMain {
	static String prefix;
	/**
	 * @param args
	 * @throws IOException
	 * @throws LdapException
	 */
	public static void main(String[] args) throws IOException, LdapException {
//		args = new String[]{"i","1","1","0","0","1000","500"};
		if(args==null && args.length!=7){
			usage();
			System.exit(0);
		}
		int addthreadcount = 0,addtimes=0,addmintime=0;
		int searchthreadcount = 0,searchtimes=0,searchmintime=0;
		try {
			prefix = args[0];
			addthreadcount = Integer.parseInt(args[1]);
			addtimes = Integer.parseInt(args[2]);
			addmintime = Integer.parseInt(args[3]);
			searchthreadcount = Integer.parseInt(args[4]);
			searchtimes = Integer.parseInt(args[5]);
			searchmintime = Integer.parseInt(args[6]);
		} catch (Exception e) {
			usage();
			System.exit(0);
		}
		for(int i=0;i<addthreadcount;i++){
			Tester test = new LdapTester(addtimes,addmintime, i);
			new Thread(test).start();
		}
		for(int i=0;i<searchthreadcount;i++){
			Tester test = new LdapSearchTester(searchtimes, searchmintime, i);
			new Thread(test).start();
		}
//		System.out.println(System.currentTimeMillis());
//		LdapConnection conn = new LdapNetworkConnection(
//				"sdmbeijingtest15.chn.hp.com", 5005);
//		conn.bind("cn=root", "secret");
//		// ("cn=root","secret");
//		Entry entry = new DefaultEntry("cn=testadd,ou=system",
//				"ObjectClass : top", "ObjectClass : person", "cn: testadd_sn",
//				"sn: testadd_sn");
//
//		AddRequest addRequest = new AddRequestImpl();
//		addRequest.setEntry(entry);
//		addRequest.addControl(new ManageDsaITImpl());
//		AddResponse response = conn.add(addRequest);
//		System.out.println(response.getMessageId());
//		conn.unBind();
//		conn.close();
	}
	private static void usage() {
		System.out.println("Usage: [prefix] [add thread count] [add times] [add minimum time] [search thread count] [search times] [search minimum time]");
	}
	
	

}
