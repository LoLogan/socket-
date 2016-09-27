package socket.llh.dao;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import socket.llh.model.MessageModel;
import socket.llh.utils.DBUtils;
import socket.llh.utils.Map;
/***
 * 
 * @author dragon
 * <pre>
 * 该类用于与数据库进行交互
 * </pre>
 */
public class UserDao {
	/**
	 * 该方法用于检测是否存在该用户
	 * @param Username 用户名
	 * @return true(存在)、false(不存在)
	 */
    public    boolean  finduser  (String Username){
	Connection conn=null;
	PreparedStatement stmt = null;
	 ResultSet rs = null;
	String sql="SELECT COUNT(1) FROM t_user WHERE user=?";
	boolean existed=false;
	try{
		conn=(Connection) DBUtils.getConnection();
		stmt=(PreparedStatement) conn.prepareStatement(sql);
		stmt.setString(1, Username);
		 rs = stmt.executeQuery();
		if(rs.next()){
			existed=(rs.getInt(1)==1);
			}
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("失败");
		}finally{
			DBUtils.close(rs,stmt,conn);
		}
	return existed;
	}
    
    /**
     * 该方法用于在数据库插入一条用户信息，以增加用户
     * @param user 用户名
     * @param password 用户密码
     */
    public  void adduser(String user,String password) {
	Connection conn=null;
	PreparedStatement stmt = null;
	String sql="INSERT INTO t_user(user,password)VALUES(?,?)";
	try{
		conn=(Connection) DBUtils.getConnection();
		stmt=(PreparedStatement) conn.prepareStatement(sql);
		stmt.setString(1,user);
		stmt.setString(2,password);
		stmt.executeUpdate();
		}	catch(SQLException e){
		e.printStackTrace();
		System.out.println("失败");
	}finally{
		DBUtils.close(null,stmt,conn);
	}
    }
	
	/**
	 * 该方法用户检测登陆时用户名和密码是否正确
	 * @param Username 用户名
	 * @param password 用户密码
	 * @return true（正确）false（错误）
	 */
	public  boolean index(String Username,String password){
		Connection conn=null;
		PreparedStatement stmt = null;
		 ResultSet rs = null;
		String sql="SELECT password FROM t_user WHERE user=?";
		boolean existed=false;
		try{
			conn=(Connection) DBUtils.getConnection();
			stmt=(PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, Username);
			 rs = stmt.executeQuery();
			if(rs.next()){
				existed=(rs.getString(1).equals(password));
				}
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("失败");
			}finally{
				DBUtils.close(rs,stmt,conn);
			}
		return existed;
		}
	/**
	 * 该方法用于保存用户发送的离线信息
	 * @param user 发送者
	 * @param target 接受者
	 * @param msg 信息内容
	 */
	public  void save(String user,String target,String msg){
		Connection conn=null;
		PreparedStatement stmt = null;
		String sql="INSERT INTO t_msg(user,target,msg)VALUES(?,?,?)";
		try{
			conn=(Connection) DBUtils.getConnection();	
			stmt=(PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, user);
			stmt.setString(2, target);
			stmt.setString(3, msg);
			stmt.executeUpdate();
	}catch(SQLException e){
		e.printStackTrace();
		System.out.println("失败");
	}finally{
		DBUtils.close(null,stmt,conn);
	}
	}
	/**
	 * 该方法用于获取离线时其他用户发送的信息
	 * @param name 用户名（接受者）
	 * @return 信息内容
	 */
	public List<MessageModel> getmsg(String name){
		Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs=null;
		List<MessageModel> msg = new ArrayList<MessageModel>();
		String sql="SELECT * FROM t_msg WHERE target=?";
		try{
			conn=(Connection) DBUtils.getConnection();
			stmt=(PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1,name);
			rs=stmt.executeQuery();
			while(rs.next()){
				msg.add(new MessageModel(rs.getString(1),rs.getString(3),name));
		}
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("失败");
		}finally{
			DBUtils.close(rs,stmt,conn);
		}
		return msg;
	}
	/**
	 * 该方法将阅读过后的离线信息删除
	 * @param name 用户名
	 */
	public void deletemsg (String name){
		Connection conn=null;
		PreparedStatement stmt = null;
		String sql="DELETE FROM t_msg WHERE target=?";
		try{
			conn=(Connection) DBUtils.getConnection();
			stmt=(PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1,name);
			stmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("失败");
		}finally{
			DBUtils.close(null,stmt,conn);
		}
	}
	
    /**
     * 该方法用于在数据库中添加好友
     * @param name1 用户名
     * @param name2 用户名
     */
    public  void addfriend(String name1,String name2) {
	Connection conn=null;
	PreparedStatement stmt = null;
	String sql="INSERT INTO t_friend(name1,name2)VALUES(?,?)";
	try{
		conn=(Connection) DBUtils.getConnection();
		stmt=(PreparedStatement) conn.prepareStatement(sql);
		stmt.setString(1,name1);
		stmt.setString(2,name2);
		stmt.executeUpdate();
		}	catch(SQLException e){
		e.printStackTrace();
		System.out.println("失败");
	}finally{
		DBUtils.close(null,stmt,conn);
	}
    }
    
    
  /**
   * 该方法用于按断添加好友时候是否已经添加该好友了
   * @param name1 
   * @param name2
   * @return true（存在） false（不存在）
   */
    public    boolean  findfri  (String name1,String name2){
	Connection conn=null;
	PreparedStatement stmt = null;
	 ResultSet rs = null;
	String sql="SELECT COUNT(1) FROM t_friend WHERE name1=? AND name2=?";
	boolean existed=false;
	try{
		conn=(Connection) DBUtils.getConnection();
		stmt=(PreparedStatement) conn.prepareStatement(sql);
		stmt.setString(1, name1);
		stmt.setString(2, name2);
		 rs = stmt.executeQuery();
		if(rs.next()){
			existed=(rs.getInt(1)==1);
			}
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("失败");
		}finally{
			DBUtils.close(rs,stmt,conn);
		}
	return existed;
	}
    
   /**
    * 获取好友列表 
    * @param name 用户名
    * @return 返回一个名字集合，即好友列表
    */
    public Set<String> getfri(String name){
    	Connection conn=null;
		PreparedStatement stmt = null;
		PreparedStatement st = null;
		ResultSet rs=null;
		ResultSet RS=null;
		Set<String> friend = new HashSet<String>();
		String sql="SELECT name2 FROM t_friend WHERE name1=?";
		String SQL="SELECT name1 FROM t_friend WHERE name2=?";
		try{
			conn=(Connection) DBUtils.getConnection();
			stmt=(PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1,name);
			rs=stmt.executeQuery();
			while(rs.next()){
				friend.add(rs.getString(1));
		}
			st=(PreparedStatement) conn.prepareStatement(SQL);
			st.setString(1,name);
			RS=st.executeQuery();
			while(RS.next()){
				friend.add(RS.getString(1));
		}
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("失败");
		}finally{
			DBUtils.close(rs,stmt,conn);
		}
		return friend;
	}
    /**
     * 该方法用于删除好友
     * @param name1
     * @param name2
     */
    public void dele(String name1,String name2){
    	Connection conn=null;
		PreparedStatement stmt = null;
		PreparedStatement st = null;
		String sql="DELETE FROM t_friend WHERE name2=? AND name1=?";
		String SQL="DELETE FROM t_friend WHERE name1=? AND name2=?";
		try{
			conn=(Connection) DBUtils.getConnection();
			stmt=(PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, name1);
			stmt.setString(2, name2);
			stmt.executeUpdate();
			st=(PreparedStatement) conn.prepareStatement(SQL);
			st.setString(1, name1);
			st.setString(2, name2);
			st.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("失败");
		}finally{
			DBUtils.close(null,stmt,conn);
		}
	}
}
