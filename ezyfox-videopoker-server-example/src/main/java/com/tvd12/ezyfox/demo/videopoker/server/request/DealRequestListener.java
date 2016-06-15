/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server.request;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.RequestParam;
import com.tvd12.ezyfox.core.command.FindRoom;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.command.Response;
import com.tvd12.ezyfox.core.command.UpdateRoom;
import com.tvd12.ezyfox.core.command.UpdateUser;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.demo.videopoker.model.Game;
import com.tvd12.ezyfox.demo.videopoker.model.Pack;
import com.tvd12.ezyfox.demo.videopoker.server.VideoPokerRoom;
import com.tvd12.ezyfox.demo.videopoker.server.VideoPokerUser;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
@Data
@ClientRequestListener(command = "4")
public class DealRequestListener {

    @RequestParam("1")
    private int indexes;
    
    @RequestParam("2")
    private int roomId;
    
    public void execute(AppContext context, VideoPokerUser user) {
        context.command(Log.class).info("user deal with hold card indexes: " + indexes);
        VideoPokerRoom room = context.command(FindRoom.class).by(roomId);
        Game game = user.getGame();
        game.deal(room.cardPack(), indexes, room.matcherChain());
        context.command(Response.class).command("4")
            .recipient(user)
            .data(game)
            .execute();
        user.setGameMoney((long)(user.getGameMoney() 
                * room.getHandByPack(game.getResult().getPack()).getPayout()));
        if(game.getResult().getPack() == Pack.NONE) {
            updateUser(context, user);
        }
        else if(game.getResult().getPack() == Pack.ROYAL_FLUSH) {
            user.setMoney(room.getJackpotMoney());
            room.resetJackpotMoney();
            updateUser(context, user);
            context.command(UpdateRoom.class)
                .room(room).toClient(true).execute();
        }
        
    }
    
    private void updateUser(AppContext context, VideoPokerUser user) {
        user.resetGameMoney();
        context.command(UpdateUser.class)
            .toClient(true).user(user).execute();
    }
    
}
