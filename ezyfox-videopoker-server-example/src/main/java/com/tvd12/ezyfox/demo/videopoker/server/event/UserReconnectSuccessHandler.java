/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server.event;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.model.ApiZone;
import com.tvd12.ezyfox.demo.videopoker.server.VideoPokerUser;

/**
 * @author tavandung12
 *
 */
@ServerEventHandler(event = ServerEvent.USER_RECONNECTION_SUCCESS)
public class UserReconnectSuccessHandler {

    public void handle(AppContext context, ApiZone zone, VideoPokerUser user) {
        context.command(Log.class).from(this).info(
                "=====>> user " + user.getName()
                + " reconnection success zone = " 
                + zone.getName());
    }
    
}
