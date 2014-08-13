package lijia.tool.ldap;



import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.directory.api.ldap.model.entry.DefaultEntry;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.AddRequest;
import org.apache.directory.api.ldap.model.message.AddRequestImpl;
import org.apache.directory.api.ldap.model.message.AddResponse;
import org.apache.directory.api.ldap.model.message.ModifyRequest;
import org.apache.directory.api.ldap.model.message.ModifyRequestImpl;
import org.apache.directory.api.ldap.model.message.ModifyResponse;
import org.apache.directory.api.ldap.model.message.controls.ManageDsaITImpl;
import org.apache.directory.api.ldap.model.name.Dn;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;
import org.apache.directory.ldap.client.api.future.AddFuture;
import org.apache.directory.ldap.client.api.future.ModifyFuture;

public class LdapTester extends Tester {
	
	public LdapTester(int count, int mintime, int threadnum) {
		super(count, mintime, threadnum);
	}



	private LdapNetworkConnection conn;
	



	@Override
	protected void afterTest() {
		clear();
	}



	private void clear() {
		if(conn!=null){
			try {
				conn.unBind();
			} catch (Exception e) {
			}
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}



	@Override
	protected void exceptionTest(Exception e1) {
		super.exceptionTest(e1);
		clear();
	}



	@Override
	protected void beforeTest() throws Exception {
		conn = new LdapNetworkConnection(
				"sdmbeijingtest15.chn.hp.com", 5005);
		conn.bind("cn=root", "secret");
		conn.setTimeOut( 0 );
	}



	@Override
	public void dotest(int i, Object... objects) throws Exception {
		mock1(i);
	}


	
	private void mock1(int i) throws Exception {
		String accountID=LdapMain.prefix+i+"_"+this.getThreadnum();
		// add accountNumber=03161582659,o=accounts,o=shawEnterprise
		Entry entry = new DefaultEntry("accountNumber="+accountID+",o=accounts,o=shawEnterprise"
				, "objectclass","shawenterpriseaccount" 
				,"objectclass","top"
				,"shawTelephoneNumber" ,"123456789"
				,"emailID","123@13.com"
				);
		addEntry(entry);
		
//		//[deviceId=M10527TCH818,o=devices,o=shawEnterprise
		
		entry = new DefaultEntry("deviceId="+accountID+",o=devices,o=shawEnterprise"
				, "objectclass","shawDevice" 
				,"objectclass","top"
				);
		addEntry(entry);
		
		// add deviceInfo [deviceAttrName=channelMap,deviceId=M10527TCH818,o=devices,o=shawEnterprise
		String[] attrs = new String[]{"channelMap","converterType","node","shawModel","locationId","mode","encoding","modulation","dctName","dac"};
		for(String attr:attrs){
			entry = new DefaultEntry("deviceAttrName="+attr+",deviceId="+accountID+",o=devices,o=shawEnterprise"
					, "objectclass","deviceInfo" 
					,"objectclass","top"
					);
			addEntry(entry);
		}
		
		
		//;modify=1 [576] [15] [cn=root,o=shawenterprise] [accountNumber=03161582659,o=accounts,o=shawEnterprise] [add: deviceid = M10527TCH818]
		ModifyRequest modRequest = new ModifyRequestImpl();
		modRequest.setName(new Dn("accountNumber="+accountID+",o=accounts,o=shawEnterprise"));
		modRequest.add("deviceid", accountID);
		modifyEntry(modRequest);
		
		//add serviceId=03161582659+serviceTypeId=ppv,accountNumber=03161582659,o=accounts,o=shawEnterprise
		
		entry = new DefaultEntry("serviceId=03161582659+serviceTypeId=ppv,accountNumber="+accountID+",o=accounts,o=shawEnterprise"
				, "objectclass","service" 
				,"objectclass","top"
				,"serviceStatus","1"
				,"serviceTypeId","ppv"
				);
		addEntry(entry);
		
		//[serviceId=03161582659+serviceTypeId=qamTv,accountNumber=03161582659,o=accounts,o=shawEnterprise
		
		entry = new DefaultEntry("serviceId=03161582659+serviceTypeId=qamTv,accountNumber="+accountID+",o=accounts,o=shawEnterprise"
				, "objectclass","service" 
				,"objectclass","top"
				,"serviceStatus","1"
				,"serviceTypeId","qamTv"
				);
		addEntry(entry);
		
		
		//serAttrName=branch,serviceId=03161582659+serviceTypeId=qamTv,accountNumber=03161582659,o=accounts,o=shawEnterprise]
		
		
		entry = new DefaultEntry("serAttrName=branch,serviceId=03161582659+serviceTypeId=qamTv,accountNumber="+accountID+",o=accounts,o=shawEnterprise"
				, "objectclass","serviceInfo" 
				,"objectclass","top"
				);
		addEntry(entry);
		
		//eName=enterpriseServiceCatalogueServiceId+eValue=5,serviceId=03161582659+serviceTypeId=qamTv,accountNumber=03161582659,o=accounts,o=shawEnterprise
		String eValues[] = {"5","10","102","126","310","313","317","320","325","326","331","333","381","409","526","527","560","583","599"};
		for(String eValue:eValues){
			entry = new DefaultEntry("eName=enterpriseServiceCatalogueServiceId+eValue="+eValue+",serviceId=03161582659+serviceTypeId=qamTv,accountNumber="+accountID+",o=accounts,o=shawEnterprise"
					, "objectclass","entitlementInfo" 
					,"objectclass","top"
					,"entitlementStatus","1"
					,"eName","enterpriseServiceCatalogueServiceId"
					,"eValue",eValue
					);
			addEntry(entry);
		}
		
		
		
		//add  [serviceId=03161582659+serviceTypeId=vod,accountNumber=03161582659,o=accounts,o=shawEnterprise]
		
		entry = new DefaultEntry("serviceId=03161582659+serviceTypeId=vod,accountNumber="+accountID+",o=accounts,o=shawEnterprise"
						, "objectclass","service" 
						,"objectclass","top"
						,"serviceStatus","1"
						,"serviceTypeId","vod"
						);
				addEntry(entry);
				
		//add  [serAttrName=branch,serviceId=03161582659+serviceTypeId=vod,accountNumber=03161582659,o=accounts,o=shawEnterprise]
		entry = new DefaultEntry("serAttrName=branch,serviceId=03161582659+serviceTypeId=vod,accountNumber="+accountID+",o=accounts,o=shawEnterprise"
						, "objectclass","serviceInfo" 
						,"objectclass","top"
						);
		addEntry(entry);
	}



	private void modifyEntry(ModifyRequest modRequest) throws LdapException,
			InterruptedException, ExecutionException, TimeoutException {
		long c = System.currentTimeMillis();
		try {
			
			ModifyFuture modifyFuture = conn.modifyAsync(modRequest );
			ModifyResponse response = modifyFuture.get(600, TimeUnit.SECONDS);
			System.out.printf("%s-%s  %s : modify : %s : %d %s\n",System.currentTimeMillis(),this.getThreadnum(),modRequest.getName(),(System.currentTimeMillis()-c),response.getLdapResult().getResultCode().getResultCode(),response.getLdapResult().getResultCode());
		}  catch (TimeoutException e) {
			System.out.printf("%s-%s  %s : modify : %s : timeout\n",System.currentTimeMillis(),this.getThreadnum(),modRequest.getName(),(System.currentTimeMillis()-c));
		}
		
	}





	private void addEntry(Entry entry) throws Exception {
		long c = System.currentTimeMillis();
		AddRequest addRequest = new AddRequestImpl();
		addRequest.setEntry(entry);
		addRequest.addControl(new ManageDsaITImpl());
		 AddFuture addFuture = conn.addAsync(addRequest);
		 AddResponse response;
		try {
			response = addFuture.get(600, TimeUnit.SECONDS);
			System.out.printf("%s-%s  %s : add : %s : %d %s\n",System.currentTimeMillis(),this.getThreadnum(),entry.getDn(),(System.currentTimeMillis()-c),response.getLdapResult().getResultCode().getResultCode(),response.getLdapResult().getResultCode());
		}  catch (TimeoutException e) {
			System.out.printf("%s-%s  %s : add : %s : timeout\n",System.currentTimeMillis(),this.getThreadnum(),entry.getDn(),(System.currentTimeMillis()-c));
		}
		
	}

}
