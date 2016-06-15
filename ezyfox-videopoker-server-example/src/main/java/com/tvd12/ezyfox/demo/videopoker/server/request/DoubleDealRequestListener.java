/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server.request;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.RequestParam;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.command.Response;
import com.tvd12.ezyfox.core.command.UpdateUser;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.demo.videopoker.model.DoubleTurn;
import com.tvd12.ezyfox.demo.videopoker.model.TurnResult;
import com.tvd12.ezyfox.demo.videopoker.server.VideoPokerUser;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
@Data
@ClientRequestListener(command = "7")
public class DoubleDealRequestListener {

    @RequestParam("1")
    private int selectedIndex;
    
    public void execute(AppContext context, VideoPokerUser user) {
        context.command(Log.class).info("user deal in double");
        DoubleTurn turn = user.getGame().finishDoubleTurn(selectedIndex);
        context.command(Response.class)
            .command("7")
            .data(turn)
            .recipient(user)
            .execute();
        if(turn.getResult() == TurnResult.LOSE) {
            user.resetGameMoney();
            context.command(UpdateUser.class)
                .user(user)
                .toClient(true)
                .execute();
        }
        else {
            user.setGameMoney(turn.getMoney());
        }
    }
    
}
