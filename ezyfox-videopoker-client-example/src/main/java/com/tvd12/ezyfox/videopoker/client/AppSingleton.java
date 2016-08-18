/**
 * 
 */
package com.tvd12.ezyfox.videopoker.client;

import lombok.Getter;
import lombok.Setter;
import sfs2x.client.entities.Room;

/**
 * @author tavandung12
 *
 */
public final class AppSingleton {

    @Setter @Getter
    private Room currentRoom;
    
    private static final AppSingleton INSTANCE 
            = new AppSingleton();
    
    private AppSingleton() {}
    
    public static AppSingleton getInstance() {
        return INSTANCE;
    }
    
}
