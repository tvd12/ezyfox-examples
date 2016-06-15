/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server.event;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.model.ApiBuddy;
import com.tvd12.ezyfox.core.model.ApiZone;
import com.tvd12.ezyfox.core.content.AppContext;

/**
 * @author tavandung12
 *
 */
@ServerEventHandler(event = ServerEvent.BUDDY_REMOVE)
public class BuddyRemoveHandler {

    public void handle(AppContext context, ApiZone zone, ApiBuddy buddy) {
        context.command(Log.class).from(this)
            .info("user " + buddy.getOwner().getName() + " " + 
                    "removed " + buddy.getName() + " " + 
                    "from zone " + zone.getName());
    }
    
}
