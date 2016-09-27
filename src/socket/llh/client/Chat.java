package socket.llh.client;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import socket.llh.dao.UserDao;
import socket.llh.utils.Protocol;
/***
 * 
 * @author dragon
 * <pre>
 * 这是一个实现聊天功能的类，可公聊可私聊
 * </pre>
 */
public class Chat {
	
	public PrintStream printStream;
	
	public  Chat(PrintStream printStream) {
		this.printStream=printStream;
	} 
	/**
	 * 这是一个私聊其他用户方法
	 * @param line 用户输入的信息，私聊指令  //名字:
	 */
	public void privatechat(String line){
		line = line.substring(2);
		if(new UserDao().finduser(line.split(":")[0]))
			{printStream.println(Protocol.PRIVATE_ROUND+
					line.split(":")[0]+Protocol.SPLIT_SIGN+
					line.split(":")[1]+Protocol.PRIVATE_ROUND);
			}
		else{
			System.out.println("该用户不存在");
		}
	}
	/**
	 * 这是一个发送群聊信息的方法
	 * @param line 用户输入的信息，公聊不需任何指令
	 */
	public void groupchat(String line){
		printStream.println(Protocol.MSG_ROUND+line+Protocol.MSG_ROUND);
	}
}
