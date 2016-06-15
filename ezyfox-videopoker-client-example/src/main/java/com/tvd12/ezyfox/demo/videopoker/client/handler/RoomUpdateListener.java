package com.tvd12.ezyfox.demo.videopoker.client.handler;

import sfs2x.client.entities.Room;

public class RoomUpdateListener extends BaseEventAdapter {

    @Override
    public void dispatch(Room room) {
        System.out.println("room update listener, data = \n" + room.getVariable("1"));
        
    }
    
}
