package com.tvd12.ezyfox.demo.videopoker.client.handler;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.demo.videopoker.client.SmartFoxConfig;
import com.tvd12.ezyfox.demo.videopoker.client.basehandler.IData;
import com.tvd12.ezyfox.demo.videopoker.client.basehandler.IEventHandler;

import sfs2x.client.SmartFox;
import sfs2x.client.core.SFSEvent;
import sfs2x.client.requests.LoginRequest;

public class LoginHandler implements IEventHandler {

	private static final String ZONE_NAME = "ezyfox-videopoker";
	
	@Override
	public void handle(IData data) {
		SmartFox smartfox = SmartFoxConfig.getInstance().getSmartFox();
		smartfox.addEventListener(SFSEvent.LOGIN, new LoginListener());
		login(smartfox, 
		        data.get("username", String.class), 
		        data.get("password", String.class));
	}
	
	private void login(SmartFox smartfox, String username, String password) {
		System.out.println("start login ...");
		ISFSObject params = new SFSObject();
		params.putUtfString("1", "1.0.0");
		LoginRequest request = new LoginRequest(username, password, ZONE_NAME, params);
		smartfox.send(request);
	}
	
}
