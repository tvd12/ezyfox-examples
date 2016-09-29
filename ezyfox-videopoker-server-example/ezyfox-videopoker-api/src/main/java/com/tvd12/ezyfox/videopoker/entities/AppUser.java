/**
 * 
 */
package com.tvd12.ezyfox.videopoker.entities;

import java.io.Serializable;

import com.tvd12.ezyfox.core.annotation.UserAgent;
import com.tvd12.ezyfox.core.entities.ApiBuddyProperties;
import com.tvd12.ezyfox.core.entities.ApiUser;

/**
 * @author tavandung12
 *
 */
@UserAgent
public class AppUser extends ApiUser implements Serializable {
    private static final long serialVersionUID = 1889820827523361607L;

    public AppUser() {
        setBuddyProperties(new ApiBuddyProperties());
    }
    
}
