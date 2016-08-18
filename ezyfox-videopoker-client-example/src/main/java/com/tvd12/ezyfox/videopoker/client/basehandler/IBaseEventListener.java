package com.tvd12.ezyfox.videopoker.client.basehandler;

import sfs2x.client.core.IEventListener;

public interface IBaseEventListener extends IEventListener {

	/**
	 * 
	 * @return
	 */
	IEventHandler getHandler();
	
	void setHanlder(IEventHandler handler);
	
}
