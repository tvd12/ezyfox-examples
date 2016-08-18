/**
 * 
 */
package com.tvd12.ezyfox.videopoker.event;

import com.tvd12.ezyfox.core.annotation.MessageParam;
import com.tvd12.ezyfox.core.annotation.MessageParams;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiPrivateMessage;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
@Data
@MessageParams
@ServerEventHandler(event = ServerEvent.PRIVATE_MESSAGE)
public class PrivateMessageHandler {
    
    @MessageParam("1")
    private String hello;
    
    public void handle(AppContext context, ApiPrivateMessage message) throws Exception {
        context.command(Log.class).from(this)
            .info("User " + message.sender().getName() + 
                    " send to user " + message.recipient().getName() + 
                    " a msessage: " + message.content() + 
                    " and hello = " + hello);
        hello = "Hello " + message.recipient().getName();
    }
}
