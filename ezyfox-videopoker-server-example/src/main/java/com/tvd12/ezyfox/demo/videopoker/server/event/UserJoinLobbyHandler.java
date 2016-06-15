/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server.event;

import com.tvd12.ezyfox.core.annotation.RoomName;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.command.UpdateUser;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.demo.videopoker.server.LobbyRoom;
import com.tvd12.ezyfox.demo.videopoker.server.VideoPokerUser;
import com.tvd12.ezyfox.demo.videopoker.server.cmd.Welcome;

/**
 * @author tavandung12
 *
 */
@RoomName("Lobby")
@ServerEventHandler(event = ServerEvent.USER_JOIN_ROOM)
public class UserJoinLobbyHandler {

    public void handle(AppContext context, LobbyRoom room, VideoPokerUser user) {
        context.command(Log.class).from(this).info("user {} has joined lobby", user.getName());
        context.command(Welcome.class).execute(user);
        context.command(UpdateUser.class).user(user).execute();
    }
    
}
