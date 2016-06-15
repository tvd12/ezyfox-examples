/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server;

import com.tvd12.ezyfox.core.annotation.RoomAgent;
import com.tvd12.ezyfox.core.model.ApiRoom;

/**
 * @author tavandung12
 *
 */
@RoomAgent
public class LobbyRoom extends ApiRoom {
    public LobbyRoom() {
        setUseWordsFilter(true);
    }
    
}
