package lijia.tool.httpporxy;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public class HttpproxyTool {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try (ServerSocket proxyserver = new ServerSocket(48888);){
			while(true){
				Socket socket = proxyserver.accept();
				new PorxyThread(socket).start();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static class PorxyThread extends Thread{
		private Socket socket;
		public PorxyThread(Socket socket){
			this.socket = socket;
		}
		public void run(){
			try {
				InputStream ips = socket.getInputStream();
				SocketChannel channel = socket.getChannel();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
