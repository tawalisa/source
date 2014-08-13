package lijia.tool.ldap;

import java.io.IOException;
import java.util.Date;

import org.apache.directory.api.ldap.model.entry.DefaultEntry;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.AddRequest;
import org.apache.directory.api.ldap.model.message.AddRequestImpl;
import org.apache.directory.api.ldap.model.message.AddResponse;
import org.apache.directory.api.ldap.model.message.controls.ManageDsaITImpl;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;

public class LdapMain {
	static String prefix;
	/**
	 * @param args
	 * @throws IOException
	 * @throws LdapException
	 */
	public static void main(String[] args) throws IOException, LdapException {
		args = new String[]{"e","10","1000","0"};
		if(args==null && args.length!=4){
			System.out.println("please input agrs: prefix threadcount times min_time");
			System.exit(0);
		}
		int threadcount = 0,times=0,mintime=0;
		try {
			prefix = args[0];
			threadcount = Integer.parseInt(args[1]);
			times = Integer.parseInt(args[2]);
			mintime = Integer.parseInt(args[3]);
		} catch (Exception e) {
			System.out.println("please input agrs: prefix threadcount times min_time");
			System.exit(0);
		}
		for(int i=0;i<threadcount;i++){
			Tester test = new LdapTester(times, mintime, i);
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
	
	

}
