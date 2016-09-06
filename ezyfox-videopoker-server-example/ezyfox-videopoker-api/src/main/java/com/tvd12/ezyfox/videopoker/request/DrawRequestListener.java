/**
 * 
 */
package com.tvd12.ezyfox.videopoker.request;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.command.Response;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.videopoker.entities.VideoPokerRoom;
import com.tvd12.ezyfox.videopoker.entities.VideoPokerUser;
import com.tvd12.ezyfox.videopoker.model.Game;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
@Data
@ClientRequestListener(command = "3")
public class DrawRequestListener {

    public void execute(AppContext context, VideoPokerRoom room, VideoPokerUser user) {
        Game game = user.newGame();
        game.draw(room.cardPack());
        context.command(Response.class)
            .command("3").recipients(user).data(game).execute();
    }

}
