package socket.llh.client;

import java.util.Scanner;

import javax.swing.JOptionPane;

import socket.llh.dao.UserDao;
/***
 * 
 * @author dragon
 * <pre>
 * 这是一个专门用于注册的类
 * </pre>
 * 
 */
public class Register {

	public String user;
	public String password;
	
	/**
	 * 这是一个注册账户的方法
	 */
	public  synchronized void register() {
		UserDao userdao = new UserDao();
		System.out.println("输入exit可退出");
		while (true) {
			System.out.println("用户名：");
			user = new Scanner(System.in).next();
			if (user.equals("exit")) {
				break;
			}
			
			if (userdao.finduser(user)) {
				System.out.println("该用户名已存在");
				
			}
			else 
			{
				System.out.println("密码：");
				password = new Scanner(System.in).next();
				if (!userdao.finduser(user))
				{
					userdao.adduser(user, password);
					System.out.println("注册成功,进行登陆");
					break;
				}
				else
					System.out.println("该用户名已存在");
			}
		}
	}
}
