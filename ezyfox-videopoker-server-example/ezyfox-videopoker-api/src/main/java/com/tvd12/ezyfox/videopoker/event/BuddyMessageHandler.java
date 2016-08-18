/**
 * 
 */
package com.tvd12.ezyfox.videopoker.event;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiBuddyMessage;

/**
 * @author tavandung12
 *
 */
@ServerEventHandler(event = ServerEvent.BUDDY_MESSAGE)
public class BuddyMessageHandler {

    public void handle(AppContext context, ApiBuddyMessage message) {
        context.command(Log.class).from(this).info("user {} send message {} to buddy {}",
                message.sender().getName(), message.content(), message.recipient().getName());
    }
    
}
