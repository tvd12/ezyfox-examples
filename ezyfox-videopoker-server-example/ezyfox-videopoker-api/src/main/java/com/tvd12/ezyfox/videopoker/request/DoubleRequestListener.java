/**
 * 
 */
package com.tvd12.ezyfox.videopoker.request;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.command.Response;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.videopoker.entities.VideoPokerRoom;
import com.tvd12.ezyfox.videopoker.entities.VideoPokerUser;
import com.tvd12.ezyfox.videopoker.model.DoubleTurn;

/**
 * @author tavandung12
 *
 */
@ClientRequestListener(command = "5")
public class DoubleRequestListener {

    public void execute(AppContext context, VideoPokerRoom room, VideoPokerUser user) {
        context.command(Log.class).info("user selected double");
        DoubleTurn turn = user.getGame().startDoubleTurn();
        turn.setMoney(user.getGameMoney());
        context.command(Response.class)
            .command("5")
            .data(turn)
            .recipients(user)
            .execute();
    }

}
