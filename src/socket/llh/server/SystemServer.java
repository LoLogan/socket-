package socket.llh.server;

import java.io.PrintStream;

import socket.llh.utils.Protocol;
/***
 * 
 * @author dragon
 * <pre>
 * 这是一个服务器端处理发送系统信息的类。
 * </pre>
 */
public class SystemServer {
	public PrintStream printStream;
	
	public SystemServer(PrintStream printStream){
		this.printStream=printStream;
	}
	/**
	 * 该方法用来查询当前在线人数
	 * @param line 用户请求的信息
	 */
	public void systemserver(String line) {
		String msg = ServerThread.getReal(line);
		if (msg.startsWith("系统：当前")) {
			String people = " ";
			for (String name : MyServer.clients.keySet()) {
				if(MyServer.clients.get(name)!=null)
				people = people + name + " ";
			}
			String system = msg.split(Protocol.SPLIT_SIGN)[0];
			String user = msg.split(Protocol.SPLIT_SIGN)[1];
			system = system + people;
			MyServer.clients.get(user).println(system);
			
		}
		else{
		// 遍历每每个输出流
		for (PrintStream clientPs : MyServer.clients.valueSet()) {
			clientPs.println(msg);
		}
		}
	}
}
