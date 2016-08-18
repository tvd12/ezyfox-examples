/**
 * 
 */
package com.tvd12.ezyfox.videopoker.request;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.ClientResponseHandler;
import com.tvd12.ezyfox.core.annotation.RequestParam;
import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.command.UpdateBuddyProperties;
import com.tvd12.ezyfox.core.command.UpdateRoom;
import com.tvd12.ezyfox.core.command.UpdateUser;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.videopoker.entities.BettingType;
import com.tvd12.ezyfox.videopoker.entities.VideoPokerRoom;
import com.tvd12.ezyfox.videopoker.entities.VideoPokerUser;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
@Data
@ClientResponseHandler
@ClientRequestListener(command = "2")
public class BetRequestListener {
    
    @RequestParam("1")
    @ResponseParam("1")
    private int bettingTypeId;
    
    public void execute(AppContext context, VideoPokerRoom room, VideoPokerUser user) {
        BettingType btype = room.getBettingType(bettingTypeId);
        context.command(Log.class).from(this).info("user " 
                + user.getName() + " bet " + btype.getMoney() + " chips");
        user.decreaseMoney(btype.getMoney());
        user.increaseGameMoney(btype.getMoney());
        
        context.command(UpdateUser.class).user(user).execute();
        context.command(UpdateBuddyProperties.class).owner(user).execute();
        
        
        room.increaseJackpotMoney((long)(btype.getMoney() * 0.01));
        context.command(UpdateRoom.class)
            .room(room).toClient(true).execute();
    }
    
}
