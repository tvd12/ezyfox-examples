/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server.request;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.command.Response;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.demo.videopoker.model.DoubleTurn;
import com.tvd12.ezyfox.demo.videopoker.server.VideoPokerUser;

/**
 * @author tavandung12
 *
 */
@ClientRequestListener(command = "5")
public class DoubleRequestListener {

    public void execute(AppContext context, VideoPokerUser user) {
        context.command(Log.class).info("user selected double");
        DoubleTurn turn = user.getGame().startDoubleTurn();
        turn.setMoney(user.getGameMoney());
        context.command(Response.class)
            .command("5")
            .data(turn)
            .recipient(user)
            .execute();
    }

}
