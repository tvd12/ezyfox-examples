package com.tvd12.ezyfox.demo.videopoker.client.handler;

import java.util.Map;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.demo.videopoker.client.basehandler.IBaseEventListener;
import com.tvd12.ezyfox.demo.videopoker.client.basehandler.IEventHandler;

import sfs2x.client.core.BaseEvent;
import sfs2x.client.entities.Room;
import sfs2x.client.entities.User;

public abstract class BaseEventAdapter implements IBaseEventListener {

	protected IEventHandler handler;
	
	@Override
	public void dispatch(BaseEvent event) throws SFSException {
	    Map<String, Object> args = event.getArguments();
	    User user = args.containsKey("user") 
	            ? (User)args.get("user") : null;
	    Room room = args.containsKey("room")
	            ? (Room)args.get("room") : null;
	    String command = (String) args.get("cmd");
	    ISFSObject params = (SFSObject)args.get("params");
	    
	    if(user != null && room != null)
	        dispatch(user, room);
	    if(user != null)
	        dispatch(user, args);
	    if(room != null)
	        dispatch(room);
	    dispatch(args);
	    dispatch(command, params);
	    
	}
	
	public void dispatch(User user, Room room) {
	    
	}
	
	public void dispatch(Room room) {
        
    }
	
	public void dispatch(User user, Map<String, Object> args) {
	    
	}
	
	public void dispatch(Map<String, Object> args) {
        
    }
	
	public void dispatch(String command, ISFSObject params) {
        
    }
	
	@Override
	public IEventHandler getHandler() {
		return handler;
	}

	@Override
	public void setHanlder(IEventHandler handler) {
		this.handler = handler;
	}
	
}
