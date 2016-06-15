package com.tvd12.ezyfox.demo.videopoker.client.handler;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.exceptions.SFSException;

import sfs2x.client.core.BaseEvent;

public class LoginListener extends BaseEventAdapter {

	@Override
	public void dispatch(BaseEvent event) throws SFSException {
		System.out.println("login successful... " + event.getArguments());
		if(event.getArguments().containsKey("data")) {
		    System.out.println(((ISFSObject)event.getArguments().get("data")).toJson());
		}
	}
	
}
