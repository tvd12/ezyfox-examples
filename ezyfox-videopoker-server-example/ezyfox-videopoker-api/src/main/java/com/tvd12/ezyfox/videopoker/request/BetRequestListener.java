/**
 * 
 */
package com.tvd12.ezyfox.videopoker.request;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.ClientResponseHandler;
import com.tvd12.ezyfox.core.annotation.ParamsMapper;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.command.UpdateBuddyProperties;
import com.tvd12.ezyfox.core.command.UpdateRoom;
import com.tvd12.ezyfox.core.command.UpdateUser;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiGameUser;
import com.tvd12.ezyfox.videopoker.entities.BettingType;
import com.tvd12.ezyfox.videopoker.entities.VideoPokerRoom;
import com.tvd12.ezyfox.videopoker.entities.VideoPokerUser;
import com.tvd12.ezyfox.videopoker.mapper.BetRequestDeserializer;
import com.tvd12.ezyfox.videopoker.mapper.BetRequestSerializer;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
@Data
@ClientResponseHandler
@ClientRequestListener(command = "2")
@ParamsMapper(serializer = BetRequestSerializer.class,
              deserializer = BetRequestDeserializer.class)
public class BetRequestListener {
    
    private int bettingTypeId;
    
    public void execute(AppContext context, VideoPokerRoom room, ApiGameUser buser) {
        VideoPokerUser user = (VideoPokerUser)buser;
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
