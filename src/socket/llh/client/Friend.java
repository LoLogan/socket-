package socket.llh.client;

import java.io.PrintStream;

import socket.llh.dao.UserDao;
import socket.llh.utils.Protocol;
/***
 * 
 * @author dragon
 * <pre>
 * 这是一个对好友操作的类，具有添加好友，同意好友添加请求，删除好友等功能
 * </pre>
 */
public class Friend {
	public String user;
	public PrintStream printStream;
	
	public  Friend(String user,PrintStream printStream) {
		this.printStream=printStream;
		this.user=user;
	} 
	/**
	 * 这是一个添加好友的方法
	 * @param line 用户输入的添加好友请求 指令   //名字_ 
	 */
	public void addfriend(String line)
	{
		line=line.substring(2);
		if(new UserDao().finduser(line.split("_")[0]))
		{
			if(new UserDao().findfri(user,line.split("_")[0])||new UserDao().findfri(line.split("_")[0],user))
				System.out.println("已添加该好友");
			else{
				printStream.println(Protocol.ADD_FRI+
						line.split("_")[0]+Protocol.ADD_FRI);
						System.out.println("请求已发送");
			}
		}
		else
			System.out.println("该用户不存在");
	}
	/**
	 * 这是一个同意添加好友的方法
	 * @param line 用户输入的同意添加好友请求 指令 //名字-
	 */
	public void agreefri(String line){
		line=line.substring(2);
		if(new UserDao().finduser(line.split("-")[0]))
		{
			if(new UserDao().findfri(user,line.split("-")[0])||new UserDao().findfri(line.split("-")[0],user))
				System.out.println("已添加该好友");
			else
			{
				MyClient.requset.remove(line.split("-")[0]);
				System.out.println("成功添加"+line.split("-")[0]+"为好友");
				printStream.println(Protocol.ADD_AGREE+
				line.split("-")[0]+Protocol.ADD_AGREE);
			}
		}
	else{
		System.out.println("该用户不存在");
	}
}
	/**
	 *这是一个查看好友列表的方法 
	 * @param line 用户输入的查看好友命令 指令 -fri 
	 */
	public void lookfri(String line) {
		System.out.println("系统：好友列表："+new UserDao().getfri(user));	
	}
	/**
	 * 这是一个删除好友的方法
	 * @param line 用户输入的删除好友命令 指令 -dele名字
	 */
	public void dele(String line){
		line=line.substring(5);
		if (new UserDao().findfri(user,line)||new UserDao().findfri(line,user)) {
		new UserDao().dele(user, line);
		System.out.println("已删除");
		}
		else{
			System.out.println("您未添加该好友");
		}
	}
}
