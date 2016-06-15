/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server.cmd.iml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.demo.videopoker.server.cmd.Welcome;

/**
 * @author tavandung12
 *
 */
public class WelcomeImpl implements Welcome {
    
    private static final Logger LOGGER = 
            LoggerFactory.getLogger(Welcome.class);

    /* (non-Javadoc)
     * @see com.lagente.videopoker.server.cmd.Welcome#execute(com.tvd12.ezyfox.core.model.ApiBaseUser)
     */
    @Override
    public void execute(ApiBaseUser user) {
        LOGGER.info("welcom user {}", user.getName());
    }

}
