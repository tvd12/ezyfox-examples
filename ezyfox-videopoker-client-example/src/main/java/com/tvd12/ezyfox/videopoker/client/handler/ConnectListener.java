package com.tvd12.ezyfox.videopoker.client.handler;

import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.videopoker.client.basehandler.IData;
import com.tvd12.ezyfox.videopoker.client.basehandler.IEventHandler;

import lombok.Setter;
import sfs2x.client.core.BaseEvent;

public class ConnectListener extends BaseEventAdapter {
    
    @Setter
    private IData data;
    
    public ConnectListener(IData data) {
        this.data = data;
    }

	@Override
	public void dispatch(BaseEvent event) throws SFSException {
		System.out.println("connection successful...");
		getHandler().handle(data);
	}
	
	@Override
	public IEventHandler getHandler() {
		return new LoginHandler();
	}

}
