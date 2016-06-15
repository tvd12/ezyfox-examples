/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server.event;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.command.AddCommand;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.db.SessionFactoryProvider;
import com.tvd12.ezyfox.demo.videopoker.server.GameApplication;
import com.tvd12.ezyfox.demo.videopoker.server.VideoPokerUser;
import com.tvd12.ezyfox.demo.videopoker.server.cmd.Welcome;
import com.tvd12.ezyfox.demo.videopoker.server.cmd.iml.WelcomeImpl;

/**
 * @author tavandung12
 *
 */
@ServerEventHandler(event = ServerEvent.SERVER_INITIALIZING)
public class ServerInitializingHandler {

    public void handle(AppContext context) {
        SessionFactoryProvider.getInstance()
            .loader()
            .context(GameApplication.class)
            .setProperties("videopoker/hibernate_mysql.properties")
            .addAnnotatedClass(VideoPokerUser.class)
            .load();
        context.command(AddCommand.class).add(Welcome.class, WelcomeImpl.class);
    }
    
}
