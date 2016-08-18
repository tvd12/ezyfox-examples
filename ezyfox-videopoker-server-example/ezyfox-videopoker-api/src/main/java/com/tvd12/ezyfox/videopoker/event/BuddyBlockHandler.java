/**
 * 
 */
package com.tvd12.ezyfox.videopoker.event;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiBuddy;
import com.tvd12.ezyfox.core.entities.ApiZone;

/**
 * @author tavandung12
 *
 */
@ServerEventHandler(event = ServerEvent.BUDDY_BLOCK)
public class BuddyBlockHandler {

    public void handle(AppContext context, ApiZone zone, ApiBuddy buddy) {
        context.command(Log.class).from(this)
            .info("user " + buddy.getOwner().getName() + " " + 
                    "blocked " + buddy.getName() + " " + 
                    "from zone " + zone.getName());
    }
    
}
