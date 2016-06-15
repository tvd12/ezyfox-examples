package com.tvd12.ezyfox.demo.videopoker.client.basehandler;

public interface IData {

	/**
	 * 
	 * @param key
	 */
	public <T> T get(String key, Class<T> clazz);
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	public <T> void put(String key, T value);
	
}
