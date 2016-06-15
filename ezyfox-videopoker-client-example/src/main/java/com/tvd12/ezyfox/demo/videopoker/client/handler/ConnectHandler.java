package com.tvd12.ezyfox.demo.videopoker.client.handler;

import com.tvd12.ezyfox.demo.videopoker.client.SmartFoxConfig;
import com.tvd12.ezyfox.demo.videopoker.client.basehandler.IData;
import com.tvd12.ezyfox.demo.videopoker.client.basehandler.IEventHandler;

import sfs2x.client.SmartFox;
import sfs2x.client.core.SFSEvent;

public class ConnectHandler implements IEventHandler {

	@Override
	public void handle(IData data) {
		SmartFox smartFox = SmartFoxConfig.getInstance().getSmartFox();
		smartFox.addEventListener(SFSEvent.CONNECTION, new ConnectListener(data));
		smartFox.connect("localhost", 9933);
	}

}
