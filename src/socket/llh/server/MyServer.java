package socket.llh.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import socket.llh.utils.Map;

/***
 * 
 * @author dragon
 *<pre>
 *服务端，用于监听客户端socket发来的信息
 *</pre>
 */
public class MyServer {

	private static final int SERVER_PORT = 30000;
	// 使用Map对象来保存每个客户和对应输出流之间的关系
	public static Map<String, PrintStream> clients = new Map<String, PrintStream>();

	/**
	 * 服务端的主方法，循环监听socket请求
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket ss = null;
		try{
			//建立监听的ServerSocket
			 ss = new ServerSocket(SERVER_PORT);
			 //死循环不断接受来自客户端的请求
			 while (true)
			 {
				 Socket s=ss.accept();
				 new ServerThread(s).start();
			 }
		}catch(IOException ex){
			System.out.println("服务器启动失败，检查端口是否被占用");
		}
		finally{
			try{
				if(ss!=null)
				{
					ss.close();
				}
			}catch(IOException ex)
			{
				ex.printStackTrace();
			}
			System.exit(1);
		}
	}

}
