/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server.event;

import com.tvd12.ezyfox.core.annotation.RequestParam;
import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
@Data
@ServerEventHandler(event = ServerEvent.USER_LOGIN)
public class UserLoginHandler {

    @RequestParam("1")
    private String version;
    
    @ResponseParam
    private String hello;
    
    public void handle(AppContext context, String username, String password) throws Exception {
        context.command(Log.class).info("user {} login with password = {}, on version = {}", 
                username, password, version);
        hello = "hello " + username;
    }
    
}
