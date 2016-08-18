package com.tvd12.ezyfox.videopoker.client.handler;

import java.util.HashMap;
import java.util.Map;

import com.tvd12.ezyfox.videopoker.client.basehandler.IData;

public class BaseData implements IData {

	private Map<String, Object> pairs = new HashMap<>();
	
	public BaseData() {}
	
	public BaseData(String key, Object value) {
		put(key, value);
	}

	@Override
	public <T> T get(String key, Class<T> clazz) {
		return clazz.cast(pairs.get(key));
	}

	@Override
	public <T> void put(String key, T value) {
		pairs.put(key, value);
	}
	
	
	
}
