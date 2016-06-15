/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server.event;

import java.util.List;

import com.tvd12.ezyfox.core.annotation.RoomName;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.command.FetchBuddyList;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.command.Response;
import com.tvd12.ezyfox.core.command.UserInfo;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.model.ApiBuddy;
import com.tvd12.ezyfox.demo.videopoker.server.VideoPokerRoom;
import com.tvd12.ezyfox.demo.videopoker.server.VideoPokerUser;

/**
 * @author tavandung12
 *
 */
@RoomName("v")
@ServerEventHandler(event = ServerEvent.USER_JOIN_ROOM)
public class UserJoinRoomHandler {

    public void handle(AppContext context, VideoPokerRoom room, VideoPokerUser user) {
        context.command(Log.class).from(this).info("user " + user.getName() + 
                " joined room " + room.getName() + 
                " playerId = " + context.command(UserInfo.class).user(user).getPlayerId());
        context.command(Response.class)
            .command("1")
            .recipient(user)
            .data(room)
            .execute();
        List<ApiBuddy> buddies = context.command(FetchBuddyList.class).user(user).execute();
        Log log = context.command(Log.class).from(this);
        for(ApiBuddy bd : buddies) {
            log.info("{}", bd);
        }
    }
    
}
