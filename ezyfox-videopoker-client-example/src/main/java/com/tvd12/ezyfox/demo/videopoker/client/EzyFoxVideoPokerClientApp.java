package com.tvd12.ezyfox.demo.videopoker.client;

import com.tvd12.ezyfox.demo.videopoker.client.basehandler.IData;
import com.tvd12.ezyfox.demo.videopoker.client.handler.BaseData;
import com.tvd12.ezyfox.demo.videopoker.client.handler.BuddyAddedResponse;
import com.tvd12.ezyfox.demo.videopoker.client.handler.ConnectHandler;
import com.tvd12.ezyfox.demo.videopoker.client.handler.ConnectionLostResponse;
import com.tvd12.ezyfox.demo.videopoker.client.handler.ConnectionResumResponse;
import com.tvd12.ezyfox.demo.videopoker.client.handler.ConnectionRetryResponse;
import com.tvd12.ezyfox.demo.videopoker.client.handler.ExtensionResponseListener;
import com.tvd12.ezyfox.demo.videopoker.client.handler.JoinRoomListener;
import com.tvd12.ezyfox.demo.videopoker.client.handler.LeaveRoomListener;
import com.tvd12.ezyfox.demo.videopoker.client.handler.LoginErrorListener;
import com.tvd12.ezyfox.demo.videopoker.client.handler.PrivateMessageResponse;
import com.tvd12.ezyfox.demo.videopoker.client.handler.PublicMessageResponse;
import com.tvd12.ezyfox.demo.videopoker.client.handler.RoomUpdateListener;
import com.tvd12.ezyfox.demo.videopoker.client.handler.UserUpdateListener;

import sfs2x.client.SmartFox;
import sfs2x.client.core.SFSBuddyEvent;
import sfs2x.client.core.SFSEvent;

/**
 * Hello world!
 *
 */
public class EzyFoxVideoPokerClientApp {
    
    private static SmartFox smartFox = SmartFoxConfig.getInstance().getSmartFox();
    
    public static void main(String[] args) {
        smartFox.addEventListener(SFSEvent.ROOM_VARIABLES_UPDATE, new RoomUpdateListener());
        smartFox.addEventListener(SFSEvent.USER_VARIABLES_UPDATE, new UserUpdateListener());
        smartFox.addEventListener(SFSEvent.ROOM_JOIN, new JoinRoomListener());
        smartFox.addEventListener(SFSEvent.ROOM_REMOVE, new LeaveRoomListener());
        smartFox.addEventListener(SFSEvent.EXTENSION_RESPONSE, new ExtensionResponseListener());
        smartFox.addEventListener(SFSEvent.CONNECTION_RETRY, new ConnectionRetryResponse());
        smartFox.addEventListener(SFSEvent.CONNECTION_RESUME, new ConnectionResumResponse());
        smartFox.addEventListener(SFSEvent.CONNECTION_LOST, new ConnectionLostResponse());
        smartFox.addEventListener(SFSEvent.PRIVATE_MESSAGE, new PrivateMessageResponse());
        smartFox.addEventListener(SFSEvent.PUBLIC_MESSAGE, new PublicMessageResponse());
        smartFox.addEventListener(SFSEvent.LOGIN_ERROR, new LoginErrorListener());
        smartFox.addEventListener(SFSBuddyEvent.BUDDY_ADD, new BuddyAddedResponse());
        String username = "dungtv";
        String password = "123456";
        if(args.length > 1) {
            username = args[0];
            password = args[1];
        }
        IData data = new BaseData();
        data.put("username", username);
        data.put("password", password); 
        new ConnectHandler().handle(data);
    }
}
