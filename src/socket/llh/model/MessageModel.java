package socket.llh.model;

import java.io.Serializable;
import java.util.Date;
/***
 * 
 * @author dragon
 *<pre>
 *该类主要封装了离线信息，即包括发信人，收信人，信息内容
 *</pre>
 */
public class MessageModel implements Serializable{
	 private String user;
	 private String message;
	 private String target;
	 
	 public MessageModel(String user,String message,String target){
		 this.message=message;
		 this.user=user;
		 this.target=target;
	 }
	 
	 /**
	  * 获取信息的发送者
	  * @return 返回信息的发送者
	  */
	 public String getuser(){
		 return user;
	 }
	 
	 /**
	  * 获取聊天信息
	  * @return 返回聊天信息
	  */
	 public String getmessage(){
		 return message;
	 }

	 /**
	  * 获取信息的接受者
	  * @return 返回信息的接受者
	  */
	 public String gettarget(){
		 return target;
	 }
	 
	 /**
	  * 设置信息的发送者
	  * @param user 信息发送者
	  */
	 public void setuser(String user){
		 this.user=user;
	 }
	 
	 /**
	  * 设置信息
	  * @param message 信息
	  */
	 public void setmessage(String message){
		 this.message=message;
	 }
	 
	 /**
	  * 设置信息的接受者
	  * @param target 信息接受者
	  */
	 public void settarget(String target){
		 this.target=target;
	 }
}
