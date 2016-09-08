/**
 * 
 */
package com.tvd12.ezyfox.videopoker.event;

import com.tvd12.ezyfox.core.annotation.RoomName;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.command.UpdateUser;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.videopoker.cmd.Welcome;

/**
 * @author tavandung12
 *
 */
@RoomName("Lobby")
@ServerEventHandler(event = ServerEvent.USER_JOIN_ROOM)
public class UserJoinLobbyHandler {

    public void handle(AppContext context, ApiRoom room, ApiBaseUser user) {
        context.command(Log.class).from(this).info("user {} has joined lobby", user.getName());
        context.command(Welcome.class).execute(user);
        context.command(UpdateUser.class).user(user).execute();
    }
    
}
