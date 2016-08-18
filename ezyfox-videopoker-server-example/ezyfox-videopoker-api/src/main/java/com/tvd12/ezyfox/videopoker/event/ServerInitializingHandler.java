/**
 * 
 */
package com.tvd12.ezyfox.videopoker.event;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.command.AddCommand;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.db.SessionFactoryProvider;
import com.tvd12.ezyfox.videopoker.cmd.Welcome;
import com.tvd12.ezyfox.videopoker.cmd.impl.WelcomeImpl;
import com.tvd12.ezyfox.videopoker.db.DatabaseContext;
import com.tvd12.ezyfox.videopoker.entities.VideoPokerUser;

/**
 * @author tavandung12
 *
 */
@ServerEventHandler(event = ServerEvent.SERVER_INITIALIZING)
public class ServerInitializingHandler {

    public void handle(AppContext context) {
        SessionFactoryProvider.getInstance()
            .loader()
            .context(DatabaseContext.class)
            .setProperties("videopoker/hibernate_mysql.properties")
            .addAnnotatedClass(VideoPokerUser.class)
            .load();
        context.command(AddCommand.class).add(Welcome.class, WelcomeImpl.class);
    }
    
}
