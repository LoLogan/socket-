package socket.llh.server;

import java.io.PrintStream;

import socket.llh.dao.UserDao;
import socket.llh.utils.Protocol;
/***
 * 
 * @author dragon
 * <pre>
 * 这是一个服务器端处理聊天信息的类，包括私聊，公聊
 * </pre>
 */
public class ChatServer {
	public PrintStream printStream;
	public ChatServer(PrintStream printStream){
		this.printStream=printStream;
	}
	
	/**
	 * 处理私聊信息，并作出相应的反应
	 * @param line
	 */
	public void chatprivate(String line){
		String userAndMsg =  ServerThread.getReal(line);
		// 以split分割字符串，前面部分是私聊用户，后面部分是聊天信息
		String user = userAndMsg.split(Protocol.SPLIT_SIGN)[0];
		String message = userAndMsg.split(Protocol.SPLIT_SIGN)[1];
		if (MyServer.clients.containsKey(user))
		// 如果该用户存在则获取私聊用户的输出流并发送私聊信息，写进流中
		{
			MyServer.clients.get(user).println(MyServer.clients.getKeyByvalue(printStream) + "悄悄对你说：" + message);
		}
		// 否则存入数据库
		else {
			new UserDao().save(MyServer.clients.getKeyByvalue(printStream), user, message);
		}
	}
	/**
	 * 处理公聊信息，并作出相应反应
	 * @param line
	 */
	public void groupchatServer(String line)
	{
		// 获取公聊信息
		String message = ServerThread.getReal(line);
		// 遍历每每个输出流
		for (PrintStream clientPs : MyServer.clients.valueSet()) {
			clientPs.println(MyServer.clients.getKeyByvalue(printStream) + "说：" + message);
		}
	}
}
