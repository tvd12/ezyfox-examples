/**
 * 
 */
package com.tvd12.ezyfox.videopoker.event;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.videopoker.entities.LobbyRoom;
import com.tvd12.ezyfox.videopoker.entities.VideoPokerUser;

/**
 * @author tavandung12
 *
 */
// @RoomName("")
@ServerEventHandler(event = ServerEvent.USER_LEAVE_ROOM)
public class UserLeaveRoomHandler {

    public void handle(AppContext context, LobbyRoom room, VideoPokerUser user) {
        context.command(Log.class).from(this).info("user {} left room {}", 
                user.getName(), room.getName());
    }
    
}
