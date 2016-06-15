/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server.event;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.annotation.ZoneName;
import com.tvd12.ezyfox.core.command.JoinRoom;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.model.ApiZone;
import com.tvd12.ezyfox.demo.videopoker.server.VideoPokerUser;
import com.tvd12.ezyfox.demo.videopoker.server.db.UserRepository;

/**
 * @author tavandung12
 *
 */
@ZoneName("ezyfox-videopoker")
@ServerEventHandler(event = ServerEvent.USER_JOIN_ZONE)
public class UserJoinZoneHandler {

    public void handle(AppContext context, ApiZone zone, VideoPokerUser user) {
        context.command(Log.class).from(this).info(
                "user " + user.getName() + 
                " at ip = " + user.getIp() +
                " join zone " + zone.getName());
        
        UserRepository.getAndUpdate(user);
        
        context.command(JoinRoom.class)
            .fireServerEvent(true)
            .roomToJoin("Lobby")
            .user(user)
            .execute();
    }
    
}
