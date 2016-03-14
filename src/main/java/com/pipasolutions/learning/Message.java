/**
 * 
 */
package com.pipasolutions.learning;

/**
 * @author RobertHarkess
 *
 */
public class Message {
	private String sUser;	
	private String message;
	//private User user;
	
	
	public Message(String message, String user){
		this.message = message;
		this.sUser = user;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getsUser() {
		return sUser;
	}
	
	public void setsUser(String sUser) {
		this.sUser = sUser;
	}
	
	//public User getUser() {
	//	return user;
	//}
	//public void setUser(User user) {
	//	this.user = user;
	//}
}
