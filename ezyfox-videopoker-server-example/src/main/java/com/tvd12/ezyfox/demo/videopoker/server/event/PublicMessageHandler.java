/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server.event;

import com.tvd12.ezyfox.core.annotation.MessageParams;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.model.ApiMessage;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
@Data
@MessageParams
@ServerEventHandler(event = ServerEvent.PUBLIC_MESSAGE)
public class PublicMessageHandler {

    public void handle(AppContext context, ApiMessage message) {
        context.command(Log.class).from(this)
            .info("user " + message.sender().getName() + 
                    " send to room " + message.room().getName() + 
                    " a message: " + message.content());
    }
    
}
