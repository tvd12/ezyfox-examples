/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server.event;

import java.util.Date;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.demo.videopoker.server.VideoPokerUser;
import com.tvd12.ezyfox.demo.videopoker.server.db.UserRepository;

/**
 * @author tavandung12
 *
 */
@ServerEventHandler(event = ServerEvent.USER_DISCONNECT)
public class UserDisconnectHandler {

    public void handle(AppContext context, VideoPokerUser user) {
        context.command(Log.class).info("user " + user.getName() + " has disconnected");
        user.setLastLoginTime(new Date());
        user.setLastLogoutTime(new Date());
        UserRepository.update(user);
    }
    
}
