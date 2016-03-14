/**
 * 
 */
package com.pipasolutions.learning;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RobertHarkess
 *
 */
public class JavaServer {
	
	List<User> room;
	User user;
	Message message;
	Board board;
	
	public JavaServer(){
		room  = new ArrayList();
		board = new Board();
	}
	
	///-----------------------------------------------
	
	public String getRoom() {
		String listString = "";
		for (User s : room){
			listString += " + " + s.getName() + "\t";
		}
		return listString;
	}

	public User getUser() {
		return user;
	}

	public Message getMessage() {
		return message;
	}

	public String getBoard() {
		String listString = "";
		for (Message s : board.getBoard()){
			listString += s.getMessage() + "\t";
		}
		return listString;
	}
	
	///-----------------------------------------------

	
	//new user logs on Requires a name
	public void newUser(String name){
		User x = new User();
		x.setName(name);
		useraddedtoroomlist(x);
	}
	
	
	//user added to list
	public void useraddedtoroomlist(User user){
		room.add(user);
	}
	
	
	//Name is displayed to screen
	public void updateRoomListToScreen(){
		//Javascript /html
	}
	
	
	//message board is loaded
	public Board retrieveMessageBoard(){
		//Only one board currently
		return board;
	}
	
	//User is given the ability to chat
	public void startChat(){
		//Javascript /html
	}
	
	
	
	
	
	//user logs off
	//User name is removed from conversation group and online list
	public void removeFromList(User user){
		if (checkUser(user) != null){
			room.remove(user.getName());
		}
	}
	
	//message received from user
	public void messageRecieved(){
		//...
	}
	

	//user is identified
	public String checkUser(User user){
		if (user.getName() == null){
			//newUserName(user);
			return null;
		}else{
			System.out.println("ok - has name");
			return user.getName();
		}
	}
	
	
	
	
	
	
	//message is created
	public Message newMessage(String text){
		return message;
	}

	//message is forwarded to users
	//message is added to board
	public void dealWithMessages(Message message){
		forwardOnMessage(message);
		passOnMessage(message);
	}
	
	
	//message forwarding to message server
	public void forwardOnMessage(Message message){
		addToBoard(message);
	}
	
	//message is sent back to web socket and to client screens
	public void passOnMessage(Message message){
		//Javascript /html
	}
	
	//message board create new one
	public void createNewBoard(){
		board = new Board();
	}

	//message is added to list of messages
	public void addToBoard(Message message){
		board.addToBoard(this.message);
	}
		
}
