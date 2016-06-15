/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server.db;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tvd12.ezyfox.db.Query;
import com.tvd12.ezyfox.db.QueryExecutor;
import com.tvd12.ezyfox.db.query.FetchByField;
import com.tvd12.ezyfox.db.query.LoadByNaturalId;
import com.tvd12.ezyfox.db.query.Save;
import com.tvd12.ezyfox.db.query.Update;
import com.tvd12.ezyfox.demo.videopoker.server.GameApplication;
import com.tvd12.ezyfox.demo.videopoker.server.VideoPokerUser;

/**
 * @author tavandung12
 *
 */
public class UserRepository {

    private static final QueryExecutor EXECUTOR
            = new QueryExecutor();
    protected static final Logger LOGGER 
            = LoggerFactory.getLogger(UserRepository.class);
    
    public static boolean exists(VideoPokerUser user) {
        return get(user.getName()) != null;
    }
    
    public static VideoPokerUser get(String username) {
        return execute(new FetchByField(VideoPokerUser.class)
                    .field("name", username));
    }
    
    public static VideoPokerUser load(final String username) {
        return execute(new LoadByNaturalId()
                .entityClass(VideoPokerUser.class)
                .using("name", username));
    }
    
    public static VideoPokerUser save(VideoPokerUser user) {
        if(!exists(user))
            return execute(new Save(user));
        return get(user.getName());
    }
    
    public static void getAndUpdate(VideoPokerUser user) {
        VideoPokerUser saved = save(user);
        saved.setLastLogoutTime(new Date());
        user.update(saved);
    }
    
    public static void update(VideoPokerUser user) {
        VideoPokerUser cached = load(user.getName());
        cached.setLastLogoutTime(new Date());
        cached.setMoney(user.getMoney());
        execute(new Update(cached));
    }
    
    protected static <T> T execute(Query query) {
        return EXECUTOR.execute(GameApplication.class, query);
    }
    
}
