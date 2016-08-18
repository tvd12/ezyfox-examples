/**
 * 
 */
package com.tvd12.ezyfox.videopoker.event;

import com.tvd12.ezyfox.core.annotation.RequestParam;
import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiLogin;

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
    
    public void handle(AppContext context, ApiLogin login) throws Exception {
        context.command(Log.class).info("user {} login with password = {}, on version = {}", 
                login.username(), login.password(), version);
        hello = "hello " + login.username();
    }
    
}
