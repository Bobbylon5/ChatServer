package com.pipasolutions.learning;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private List<Message> board;

	
	public Board() {
		board = new ArrayList<Message>();
	}
	
	public List<Message> getBoard() {
		return board;
	}

	private void setBoard(List<Message> board) {
		this.board = board;
	}
	
	public void addToBoard(Message arg0){
		board.add(arg0);
	}
	
	public void addMessage(Message e){
		board.add(e);
	}
	
	public void createMessage(String user, String message){
		Message m = new Message(user, message);
		board.add(m);
	}
}
