package socket.llh.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/***
 * 
 * @author dragon
 *<pre>
 *这是一个开启客户机的类，包含主方法，程序运行的主线。
 *</pre>
 */
public class MyClient {
	public static Set<String> requset = new HashSet<String>();
	/**
	 * 主方法
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("欢迎进入微聊系统");
		System.out.println(" ");
		while (true) {

			System.out.println("1:注册       2:登陆       3:退出");
			int choice = new Scanner(System.in).nextInt();
			switch (choice) {
			case 1: {
				new Register().register();
				break;
			}
			case 2: {

				BufferedReader keyIn = null;
				Index index = new Index();
				index.index();

				try {
					// 键盘输入
					keyIn = new BufferedReader(new InputStreamReader(System.in));
					String line = null;

					while ((line = keyIn.readLine()) != null) {
						// 私聊功能
						if (line.startsWith("//")&&line.indexOf(":") > 0 ) {
							Chat chat = new Chat(index.printStream);
							chat.privatechat(line);
						}
						// 查看在线人员
						else if (line.startsWith("-look")) {
							Online online = new Online(index.user, index.printStream);
							online.onlinenumber(line);
						}
						// 请求添加好友功能
						else if (line.startsWith("//")&&line.endsWith("_")   ) {
							Friend friend = new Friend(index.user, index.printStream);
							friend.addfriend(line);
						}
						// 同意好友申请功能
						else if (line.startsWith("//")&&(MyClient.requset.contains(line.substring(2).split("-")[0])) && line.indexOf("-") > 0
								) {
							Friend friend = new Friend(index.user, index.printStream);
							friend.agreefri(line);
						}
						// 查看好友列表功能
						else if (line.startsWith("-fri")) {
							Friend friend = new Friend(index.user, index.printStream);
							friend.lookfri(line);
						}
						//删除好友功能
						else if (line.startsWith("-dele")) {
							Friend friend = new Friend(index.user, index.printStream);
							friend.dele(line);
						}
						//
						else if (line.equals("-exit")) {
							break;
						}
						// 群聊功能
						else {
							Chat chat = new Chat(index.printStream);
							chat.groupchat(line);
						}
					}
				} catch (IOException ex) {
					System.out.println("网络异常!");
					if (index.bufferedReader != null) {
						index.bufferedReader.close();
					}
					if (index.printStream != null) {
						index.printStream.close();
					}
					if (keyIn != null) {
						keyIn.close();
					}
					if (index.socket != null) {
						index.socket.close();
					}
				}
			}
			case 3: {
				System.exit(1);
			}

			default: {
				System.out.println(" 输入错误");
				break;
			}

			}
		}
	}

}