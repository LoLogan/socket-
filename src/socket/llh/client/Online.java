package socket.llh.client;

import java.io.PrintStream;

import socket.llh.utils.Protocol;
/***
 * 
 * @author dragon
 * <pre>
 * 该类用于查看当前在线人数
 * </pre>
 *
 */
public class Online {
	
	public PrintStream printStream;
	public String user;
	
	public  Online(String user,PrintStream printStream) {
		this.printStream=printStream;
		this.user=user;
	} 
	
	/**
	 * 这是一个查看在线人数的方法
	 * @param line 用户输入的查询在线人数的指令 -look
	 */
	public void onlinenumber(String line){
		printStream.println(Protocol.SYSTEM+"系统：当前在线人员有:"+Protocol.SPLIT_SIGN+user+Protocol.SYSTEM);
	}
}
