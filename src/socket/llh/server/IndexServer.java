package socket.llh.server;

import java.io.PrintStream;

import socket.llh.utils.Protocol;
/***
 * 
 * @author dragon
 * <pre>
 * 这是一个处理用户登陆的类
 * </pre>
 */

public class IndexServer {
	public PrintStream printStream;
	
	public IndexServer(PrintStream printStream){
		this.printStream=printStream;
	}
	/**
	 * 判断该用户是否登陆成功
	 * @param line 客户端发来的登陆信息
	 */
	public void indexserver(String line) {
		String username = ServerThread.getReal(line);
		if (MyServer.clients.containsKey(username)) {
			printStream.println(Protocol.NAME_REP);
		} else {
			printStream.println(Protocol.LOGIN_SUCCESS);
			MyServer.clients.put(username, printStream);
			System.out.println(username);
		}
	}
}
