package com.tvd12.ezyfox.demo.videopoker.client;

import lombok.Data;
import sfs2x.client.SmartFox;

@Data
public class SmartFoxConfig {

	private static SmartFoxConfig instance;

	private SmartFox smartFox = new SmartFox();
	
	private SmartFoxConfig() {}
	
	public static SmartFoxConfig getInstance() {
		if(instance == null) {
			synchronized (SmartFoxConfig.class) {
				if(instance == null) {
					instance = new SmartFoxConfig();
				}
			}
		}
		
		return instance;
	}
	
}
