	package com.tvd12.ezyfox.videopoker.event;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvd12.ezyfox.videopoker.entities.LobbyRoom;
import com.tvd12.ezyfox.videopoker.entities.VideoPokerRoom;

public class RoomProvider {
	
	private RoomProvider() {}

	public static LobbyRoom lobby() {
	    LobbyRoom lobby = new LobbyRoom();
		lobby.setName("Lobby");
		lobby.setMaxUsers(2000);
		lobby.setMaxRoomVariablesAllowed(30);
		lobby.setGame(false);
		return lobby; 
	}
	
	public static VideoPokerRoom[] gameRooms() {
	    try {
            return new ObjectMapper()
                    .readValue(roomsConfigs(), VideoPokerRoom[].class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
	}
	
	private static InputStream roomsConfigs() {
	    return RoomProvider.class.getClassLoader()
	            .getResourceAsStream("videopoker/rooms.json");
	}
	
}