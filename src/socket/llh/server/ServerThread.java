package socket.llh.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import socket.llh.dao.UserDao;
import socket.llh.utils.Protocol;

/***
 * 
 * @author dragon
 * 这是一个处理客户端发送的请求的类
 */
public class ServerThread extends Thread {
	private Socket socket;
	BufferedReader bufferedReader = null;
	PrintStream printStream = null;

	public ServerThread(Socket s) {
		this.socket = s;
	}
	/**
	 * 重写run方法实现多线程，回应客户端请求
	 */
	public void run() {
		try {
			// 获取socket对应的输入流
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// 获取socket对应的输出流
			printStream = new PrintStream(socket.getOutputStream());
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				
				// 如果读到以Protocol.USER_ROUND包裹，则读到的是用户名,进行登陆判断
				if (line.startsWith(Protocol.USER_ROUND) && line.endsWith(Protocol.USER_ROUND)) {
					IndexServer indexServer = new IndexServer(printStream);
					indexServer.indexserver(line);
				}
				
				
				// 如果读到以Protocol.PRIVATE_ROUND包裹，则读到的是私聊信息
				else if (line.startsWith(Protocol.PRIVATE_ROUND) && line.endsWith(Protocol.PRIVATE_ROUND)) {
					ChatServer chatServer = new ChatServer(printStream);
					chatServer.chatprivate(line);
				}
				
				
				// 若是Protocol.SYSTEM包裹,则获取系统信息
				else if (line.startsWith(Protocol.SYSTEM) && line.endsWith(Protocol.SYSTEM)) {
					SystemServer systemServer = new SystemServer(printStream);
					systemServer.systemserver(line);
				}
				
				
				//获取添加好友请求
				else if (line.startsWith(Protocol.ADD_FRI) && line.endsWith(Protocol.ADD_FRI)){
					FriendServer friendServer = new FriendServer(printStream);
					friendServer.addfriend(line);
				}
				
				
				// 同意好友请求
				else if (line.startsWith(Protocol.ADD_AGREE) && line.endsWith(Protocol.ADD_AGREE)){
					FriendServer friendServer = new FriendServer(printStream);
					friendServer.agreefriend(line);
				}
				
				
				// 公聊则向每个Socket发送
				else {
					ChatServer chatServer = new ChatServer(printStream);
					chatServer.groupchatServer(line);
				}
			}
		} catch (IOException e) {
			// 关闭网络IO资源
			try {
			
				for (PrintStream clientPs : MyServer.clients.valueSet()) {
					clientPs.println(MyServer.clients.getKeyByvalue(printStream)+"已下线");}
				MyServer.clients.removeByValue(printStream);
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (printStream != null) {
					printStream.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	/**
	 * 该方法将读到的内容去掉前后的协议自负，恢复成真实数据
	 * @param line 客户端发来的请求信息
	 * @return 分解恢复后的信息
	 */
	static String getReal(String line) {
		return line.substring(Protocol.PROTOCAL_LEN, line.length() - Protocol.PROTOCAL_LEN);
	}
}
