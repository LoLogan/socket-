package socket.llh.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

import socket.llh.dao.UserDao;
import socket.llh.model.MessageModel;
import socket.llh.utils.Protocol;
/***
 * 
 * @author dragon
 * <pre>
 * 这是一个实现用户登录的类
 * </pre>
 */
public class Index {
	public String user;
	public String password;
	private static final int SERVER_PORT = 30000;

	public Socket socket;
	public PrintStream printStream;
	public BufferedReader bufferedReader;
	/**
	 * 这是一个检验用户登陆时的账号密码是否正确以及是否已经登陆的方法
	 */
	public void index() {
		UserDao userdao = new UserDao();
		while (true) {
			System.out.println("用户名：");
			user = new Scanner(System.in).next();
			System.out.println("密码：");
			password = new Scanner(System.in).next();

			if (userdao.finduser(user) && userdao.index(user, password)) {

				try {
					// 连接到服务器
					socket = new Socket("127.0.0.1", SERVER_PORT);
					// 获得输入输出流
					printStream = new PrintStream(socket.getOutputStream());
					bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

					 /* 检验用户是否已经登录*/
					printStream.println(Protocol.USER_ROUND + user + Protocol.USER_ROUND);// 将用户名前后增加协议字符串后发送
					String result = bufferedReader.readLine();// 读取响应
					if (result.equals(Protocol.NAME_REP)) {
						System.out.println("该用户已登录");
						continue;// 如果用户已经登陆，则开始下次循环
					}
					if (result.equals(Protocol.LOGIN_SUCCESS)) {
						printStream.println(Protocol.SYSTEM + "系统：欢迎" + user + "加入聊天室" + Protocol.SYSTEM);
						
						
						/* 从数据库中读取离线时所受到的私聊以及好友申请*/
						List<MessageModel> msgs = new UserDao().getmsg(user);
						for (MessageModel msg : msgs) {
							if (msg.getmessage().startsWith(Protocol.ADD_FRI)) {
								System.out.println(msg.getuser() + " 想添加您为好友       ");
								MyClient.requset.add(msg.getuser());
							} else
								System.out.println(msg.getuser() + "悄悄对你说:" + msg.getmessage());
						}
						new UserDao().deletemsg(user); //读完离线信息即把他们删除

						new ClientThread(bufferedReader).start();
						break;
					}
				} catch (UnknownHostException ex) {
					System.out.println("找不到远程服务器，请确定服务器已经启动！");
					closeRs();
					System.exit(1);
				} catch (IOException ex) {
					System.out.println("网络异常!请重新登录!");
					closeRs();
					System.exit(1);
				}

			} else {
				System.out.println("账号或密码错误");
			}

		}
	}
	/***
	 * 这是一个关闭流关闭socket的方法
	 */
	private void closeRs() {
		try {

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
