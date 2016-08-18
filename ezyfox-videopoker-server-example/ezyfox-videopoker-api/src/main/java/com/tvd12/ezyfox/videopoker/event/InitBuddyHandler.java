/**
 * 
 */
package com.tvd12.ezyfox.videopoker.event;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.command.AddBuddy;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiZone;
import com.tvd12.ezyfox.videopoker.entities.VideoPokerUser;

/**
 * @author tavandung12
 *
 */
@ServerEventHandler(event = ServerEvent.BUDDY_LIST_INIT)
public class InitBuddyHandler {

    public void handle(AppContext context, ApiZone zone, VideoPokerUser user) {
        context.command(Log.class).info("user " + user.getName() + " init buddy");
        try {
        if(!user.getName().equals("dungtv14"))
            context.command(AddBuddy.class)
                .owner(user)
                .buddy("dungtv14")
                .execute();
        
        if(!user.getName().equals("dungtv"))
            context.command(AddBuddy.class)
                .owner(user)
                .buddy("dungtv")
                .execute();
        } catch(IllegalStateException e) {
            e.printStackTrace();
        }
    }
    
}
