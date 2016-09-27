package socket.llh.server;

import java.io.PrintStream;

import socket.llh.client.MyClient;
import socket.llh.dao.UserDao;
import socket.llh.utils.Protocol;

/**
 * 
 * @author dragon
 * <pre>
 * 这是一个服务器端处理好友的类，包括添加好友，处理好友申请，删除好友操作
 * </pre>
 */
public class FriendServer {
	public PrintStream printStream;
	public FriendServer(PrintStream printStream){
		this.printStream=printStream;
	}
	/**
	 * 处理添加好友申请，并发送处理后的信息
	 * @param line 客户端发来的信息
	 */
	public void addfriend(String line){
		// 获取添加好友请求
		String request = ServerThread.getReal(line);
		if(MyServer.clients.containsKey(request))
		{	
			String user = MyServer.clients.getKeyByvalue(printStream);
			String me = request;
			MyServer.clients.get(request).println(Protocol.ADD_FRI+user+Protocol.SPLIT_SIGN+" 想添加您为好友       ");
		}
		else{
			new UserDao().save(MyServer.clients.getKeyByvalue(printStream), request, 
					Protocol.ADD_FRI+MyServer.clients.getKeyByvalue(printStream) +Protocol.SPLIT_SIGN+request+Protocol.ADD_FRI);
		}
	}
	/**
	 * 处理同意添加好友申请，并发送处理后的信息
	 * @param line 客户端发来的信息
	 */
	public void agreefriend(String line){
		String request = ServerThread.getReal(line);
		new UserDao().addfriend(MyServer.clients.getKeyByvalue(printStream),request);
		MyServer.clients.get(request).println("成功添加"+MyServer.clients.getKeyByvalue(printStream)+"为好友");
	}
}
