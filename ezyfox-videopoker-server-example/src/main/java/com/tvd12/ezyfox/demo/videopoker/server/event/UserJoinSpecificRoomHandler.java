/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server.event;

import com.tvd12.ezyfox.core.annotation.RoomName;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.demo.videopoker.server.VideoPokerRoom;
import com.tvd12.ezyfox.demo.videopoker.server.VideoPokerUser;

/**
 * @author tavandung12
 *
 */
@RoomName("vjob")
@ServerEventHandler(event = ServerEvent.USER_JOIN_ROOM)
public class UserJoinSpecificRoomHandler {

    public void handle(AppContext context, VideoPokerRoom room, VideoPokerUser user) {
        context.command(Log.class).from(this).info("user " + user.getName() + " joined room " + room.getName());
    }
    
}
