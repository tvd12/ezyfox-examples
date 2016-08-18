/**
 * 
 */
package com.tvd12.ezyfox.videopoker.request;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.ClientResponseHandler;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.command.UpdateUser;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.videopoker.entities.VideoPokerRoom;
import com.tvd12.ezyfox.videopoker.entities.VideoPokerUser;

/**
 * @author tavandung12
 *
 */
@ClientResponseHandler
@ClientRequestListener(command = "6")
public class NothingRequestListener {

    public void execute(AppContext context, VideoPokerRoom room, VideoPokerUser user) {
        context.command(Log.class).info("user selected nothing");
        user.increaseMoney(user.getGameMoney());
        user.resetGameMoney();
        context.command(UpdateUser.class)
            .user(user).toClient(true).execute();
    }

}
