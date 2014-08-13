package lijia.tool.httpporxy.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import lijia.tool.Messages;
import lijia.tool.httpporxy.SerializableUtil;

public class ProxyInvoker {

	public static byte[] sendData(byte[] receivedata) throws IOException {
	    Authenticator.setDefault(new BasicAuthenticator(Messages.getString("proxyuser"), Messages.getString("proxypswd"))); 
	    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Messages.getString("proxyurl"), Integer.parseInt(Messages.getString("proxyport"))));  
	    URL url = new URL("http://www.baidu.com");
		URLConnection connection = url.openConnection(proxy); 
		InputStream response = connection.getInputStream();
		int r = response.read();
		while(r!=-1){
			System.out.print(((char)r));
			r = response.read();
		}
		return null;
	}
	public static void main(String[] aa){
		try {
			sendData(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
