/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.testing;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvd12.ezyfox.demo.videopoker.server.VideoPokerRoom;

/**
 * @author tavandung12
 *
 */
public class RoomJsonMappingTest {
    
    @Test
    public void test() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        VideoPokerRoom[] rooms = mapper.readValue(RoomJsonMappingTest
                .class.getClassLoader().getResourceAsStream("videopoker/rooms.json"), VideoPokerRoom[].class);
        System.out.println(rooms[0].getPayTable().getHands()[0].getPack());
    }

    
}
