/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server.request;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.RequestParam;
import com.tvd12.ezyfox.core.command.FindRoom;
import com.tvd12.ezyfox.core.command.Response;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.demo.videopoker.model.Game;
import com.tvd12.ezyfox.demo.videopoker.server.VideoPokerRoom;
import com.tvd12.ezyfox.demo.videopoker.server.VideoPokerUser;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
@Data
@ClientRequestListener(command = "3")
public class DrawRequestListener {

    @RequestParam("1")
    private int roomId;
    
    public void execute(AppContext context, VideoPokerUser user) {
        VideoPokerRoom room = context.command(FindRoom.class).by(roomId);
        Game game = user.newGame();
        game.draw(room.cardPack());
        context.command(Response.class)
            .command("3").recipient(user).data(game).execute();
    }

}
