/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.client.handler;

import com.smartfoxserver.v2.exceptions.SFSException;

import sfs2x.client.core.BaseEvent;

/**
 * @author tavandung12
 *
 */
public class ConnectionResumResponse extends BaseEventAdapter {

    /* (non-Javadoc)
     * @see com.puppet.videopoker.client.handler.BaseEventAdapter#dispatch(sfs2x.client.core.BaseEvent)
     */
    @Override
    public void dispatch(BaseEvent event) throws SFSException {
        System.out.println("connection resum response = " + event.getArguments());
    }
    
}
