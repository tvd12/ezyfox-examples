/**
 * 
 */
package com.tvd12.ezyfox.videopoker.interceptor;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.RequestParam;
import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.exception.BadRequestException;
import com.tvd12.ezyfox.videopoker.entities.VideoPokerRoom;
import com.tvd12.ezyfox.videopoker.entities.VideoPokerUser;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
@Data
@ClientRequestListener(command = "2", priority = -1)
public class BetRequestInterceptor {
    
    @RequestParam("1")
    @ResponseParam("1")
    private int bettingTypeId;
    
    public void execute(AppContext context, VideoPokerRoom room, VideoPokerUser user) throws Exception {
        context.command(Log.class).from(this).info("intercept user " 
                + user.getName() + " with betting type id = " + bettingTypeId + ", room = " + room.getName());
        if(room.getBettingType(bettingTypeId) == null)
            throw new BadRequestException("Has no betting type with id = " + bettingTypeId, 1);
    }
    
}
