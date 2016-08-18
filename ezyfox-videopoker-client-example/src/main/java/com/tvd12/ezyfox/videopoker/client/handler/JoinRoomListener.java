package com.tvd12.ezyfox.videopoker.client.handler;

import com.tvd12.ezyfox.videopoker.client.AppInput;
import com.tvd12.ezyfox.videopoker.client.AppSingleton;

import sfs2x.client.entities.Room;
import sfs2x.client.entities.User;

public class JoinRoomListener extends BaseEventAdapter {

	@Override
	public void dispatch(User user, Room room) {
		System.out.println(user.getName() + " join room " + room.getName());
	}
	
	@Override
	public void dispatch(Room room) {
	    AppSingleton.getInstance().setCurrentRoom(room);
	    System.out.println("user join room " + room.getName() + ", " + room.getId());
	    if(room.getName().equals("Lobby")) {
	        RequestUtil.initBuddyList();
	        System.out.print("Play game or test? (p/t): ");
	        char ch = AppInput.nextChar();
	        if(ch != 't') {
                RequestUtil.joinGameRoom("vjob");
	        }
	        else {
	            AppTest.test();
	        }
	    }
	}

}
