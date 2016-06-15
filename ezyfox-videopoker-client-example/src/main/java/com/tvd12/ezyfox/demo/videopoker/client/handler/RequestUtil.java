/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.client.handler;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.demo.videopoker.client.AppSingleton;
import com.tvd12.ezyfox.demo.videopoker.client.SmartFoxConfig;

import sfs2x.client.SmartFox;
import sfs2x.client.entities.Buddy;
import sfs2x.client.entities.Room;
import sfs2x.client.entities.User;
import sfs2x.client.requests.ExtensionRequest;
import sfs2x.client.requests.JoinRoomRequest;
import sfs2x.client.requests.PrivateMessageRequest;
import sfs2x.client.requests.PublicMessageRequest;
import sfs2x.client.requests.buddylist.BuddyMessageRequest;
import sfs2x.client.requests.buddylist.InitBuddyListRequest;

/**
 * @author tavandung12
 *
 */
public class RequestUtil {

    public static final SmartFox SMARFOX = SmartFoxConfig.getInstance().getSmartFox();
    
    private RequestUtil() {}
    
    public static void joinGameRoom(String roomName) {
        SMARFOX.send(new JoinRoomRequest(roomName));
    }
    
    public static void bet(int bettingTypeId) {
        ISFSObject params = new SFSObject();
        int roomId = AppSingleton
                .getInstance().getCurrentRoom().getId();
        params.putInt("1", bettingTypeId);
        params.putInt("2", roomId);
        SMARFOX.send(new ExtensionRequest("2", params));
    }
    
    public static void draw() {
        ISFSObject params = new SFSObject();
        int roomId = AppSingleton
                .getInstance().getCurrentRoom().getId();
        params.putInt("1", roomId);
        SMARFOX.send(new ExtensionRequest("3", params));
    }
    
    public static void deal(int indexes) {
        System.out.println("start deal cards");
        ISFSObject params = new SFSObject();
        int roomId = AppSingleton
                .getInstance().getCurrentRoom().getId();
        params.putInt("1", indexes);
        params.putInt("2", roomId);
        SMARFOX.send(new ExtensionRequest("4", params));
    }
    
    public static void selectDouble() {
        System.out.println("select double");
        ISFSObject params = new SFSObject();
        SMARFOX.send(new ExtensionRequest("5", params));
    }
    
    public static void nothing() {
        System.out.println("select nothing");
        ISFSObject params = new SFSObject();
        SMARFOX.send(new ExtensionRequest("6", params));
    }
    
    public static void dealInDouble(int selectedIndex) {
        System.out.println("select deal in double");
        ISFSObject params = new SFSObject();
        params.putInt("1", selectedIndex);
        SMARFOX.send(new ExtensionRequest("7", params));
    }
    
    public static void initBuddyList() {
        System.out.println("init buddy");
        SMARFOX.send(new InitBuddyListRequest());
    }
    
    public static void sendPrivateMessage(String targetUser) {
        Room room = AppSingleton.getInstance().getCurrentRoom();
        System.out.println("send a private message in room " + room.getName()
                + " user count = " + room.getUserCount());
        User user = getUserByName(targetUser);
        if(user != null) {
            ISFSObject params = new SFSObject();
            params.putUtfString("1", "Hello coco");
            PrivateMessageRequest request = new PrivateMessageRequest("Hey guy", 
                    user.getId(), params);
            SMARFOX.send(request);
        }
    }
    
    public static void sendPublicMessage() {
        Room room = AppSingleton.getInstance().getCurrentRoom();
        System.out.println("send a public message in room " + room.getName()
                + " user count = " + room.getUserCount());
        PublicMessageRequest request = new PublicMessageRequest("Hey guys", new SFSObject());
            SMARFOX.send(request);
    }
    
    public static void sendBuddyMessage(String targetUser) {
        Room room = AppSingleton.getInstance().getCurrentRoom();
        System.out.println("send a buddy message in room " + room.getName()
                + " user count = " + room.getUserCount());
        User user = getUserByName(targetUser);
        if(user != null) {
            ISFSObject params = new SFSObject();
            params.putUtfString("1", "Hello coco");
            //sorry, I must test use word filter
            BuddyMessageRequest request = new BuddyMessageRequest("Hey guy shit", 
                    getBuddyByName(targetUser), params);
            SMARFOX.send(request);
        }
    }
    
    public static User getUserByName(String name) {
        return AppSingleton
            .getInstance().getCurrentRoom().getUserByName(name);
    }
    
    public static Buddy getBuddyByName(String name) {
        return SMARFOX.getBuddyManager().getBuddyByName(name);
    }
}
