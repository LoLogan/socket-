package socket.llh.utils;
/***
 * 
 * @author dragon
 * <pre>
 * 用于定义某些字符来判断socket信息的类型
 * </pre>
 */
public interface Protocol {
	//协议字符串的长度
	int PROTOCAL_LEN = 2;
	//协议字符串，应该在前、后添加这种特殊字符串
	String MSG_ROUND = "§γ";
	String USER_ROUND = "∏∑";
	String LOGIN_SUCCESS = "1";
	String NAME_REP = "-1";
	String PRIVATE_ROUND = "★【";
	String SPLIT_SIGN = "※";
	String SYSTEM = "∏γ";
	String LOOK = "∑γ";
	String ADD_FRI="∏§";
	String ADD_AGREE="★∏";
}
