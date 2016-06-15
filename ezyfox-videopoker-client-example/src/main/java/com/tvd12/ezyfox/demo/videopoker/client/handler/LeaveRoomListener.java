/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.client.handler;

import sfs2x.client.entities.Room;
import sfs2x.client.entities.User;

/**
 * @author tavandung12
 *
 */
public class LeaveRoomListener extends BaseEventAdapter {

    @Override
    public void dispatch(User user, Room room) {
         System.out.println("user " + user + ", leave room " + room);
    }

}
