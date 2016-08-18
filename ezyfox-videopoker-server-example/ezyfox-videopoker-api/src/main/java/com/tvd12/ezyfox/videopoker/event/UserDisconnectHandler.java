/**
 * 
 */
package com.tvd12.ezyfox.videopoker.event;

import java.util.Date;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiDisconnection;
import com.tvd12.ezyfox.videopoker.db.UserRepository;
import com.tvd12.ezyfox.videopoker.entities.VideoPokerUser;

/**
 * @author tavandung12
 *
 */
@ServerEventHandler(event = ServerEvent.USER_DISCONNECT)
public class UserDisconnectHandler {

    public void handle(AppContext context, ApiDisconnection dis) {
        VideoPokerUser user = dis.user();
        context.command(Log.class).info("user " + user.getName() + " has disconnected");
        user.setLastLoginTime(new Date());
        user.setLastLogoutTime(new Date());
        UserRepository.update(user);
    }
    
}
