package socket.llh.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

import socket.llh.dao.UserDao;
import socket.llh.utils.Protocol;
/***
 * 
 * @author dragon
 * <pre>
 * 这是实现客户机输出的类，即是将客户端从服务器socket读来的信息发布
 * </pre>
 */
public class ClientThread extends Thread{
	//该客户端所对应的输入流
	BufferedReader bufferedReader = null;

	public ClientThread(BufferedReader bufferedReader) 
	{
		this.bufferedReader = bufferedReader;
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 * 开启线程时重写的方法
	 */
	public void run()
	{
			try{
				String line=null;
				while((line=bufferedReader.readLine())!=null)
				{
					if(line.startsWith(Protocol.ADD_FRI))
					{
						line=line.substring(Protocol.PROTOCAL_LEN, line.length());
						String user = line.split(Protocol.SPLIT_SIGN)[0];
						MyClient.requset.add(user);
						System.out.println(user+" 想添加您为好友       ");
					}
					else System.out.println(line);
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
			finally{
				try
				{
					if(bufferedReader != null)
					{
						bufferedReader.close();
					}
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}
		
	}
}
