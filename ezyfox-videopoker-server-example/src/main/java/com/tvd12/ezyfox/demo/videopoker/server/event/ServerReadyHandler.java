/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server.event;

import com.tvd12.ezyfox.core.annotation.ConfigProperty;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.command.CreateRoom;
import com.tvd12.ezyfox.core.command.UpdateRoom;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.demo.videopoker.server.VideoPokerRoom;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
@Data
@ServerEventHandler(event = ServerEvent.SERVER_READY)
public class ServerReadyHandler {
    
    @ConfigProperty("appId")
    private String appId;
    
    public void handle(AppContext context) {
        context.command(CreateRoom.class)
            .agents(RoomProvider.lobby()).execute();
        VideoPokerRoom[] rooms = RoomProvider.gameRooms(); 
        context.command(CreateRoom.class).agents(rooms).execute();
        for(VideoPokerRoom room : rooms) {
            context.command(UpdateRoom.class)
                .toClient(true).room(room).execute();
        }
    }
    
}
