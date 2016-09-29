/**
 * 
 */
package com.tvd12.ezyfox.videopoker.request;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.ClientResponseHandler;
import com.tvd12.ezyfox.core.annotation.RequestParam;
import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.videopoker.entities.LobbyRoom;
import com.tvd12.ezyfox.videopoker.entities.VideoPokerUser;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
@Data
@ClientResponseHandler
@ClientRequestListener(command = "sum")
public class SumRequestListener {
    
    @RequestParam("ab")
    private int ab[];
    
    @ResponseParam("sum")
    private int sum;
    
    public void execute(AppContext context, LobbyRoom room, VideoPokerUser user) {
        setSum(ab[0] + ab[1]);
        context.command(Log.class).from(this)
            .info("=========sum = " + getSum());
    }
    
}
