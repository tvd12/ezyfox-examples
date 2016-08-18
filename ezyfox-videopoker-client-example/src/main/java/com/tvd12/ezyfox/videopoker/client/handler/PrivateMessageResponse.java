/**
 * 
 */
package com.tvd12.ezyfox.videopoker.client.handler;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.exceptions.SFSException;

import sfs2x.client.core.BaseEvent;

/**
 * @author tavandung12
 *
 */
public class PrivateMessageResponse extends BaseEventAdapter {

    /* (non-Javadoc)
     * @see com.puppet.videopoker.client.handler.BaseEventAdapter#dispatch(sfs2x.client.core.BaseEvent)
     */
    @Override
    public void dispatch(BaseEvent event) throws SFSException {
        System.out.println("private message response: " + event.getArguments()
                + "\ndata = " + ((ISFSObject)event.getArguments().get("data")).toJson());
    }
    
}
