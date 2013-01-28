package org.leve.bundles;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.leve.beans.Message;

public class LeveResourceBundle extends java.util.ResourceBundle {

	
	
	private java.util.Map<String, String> messages = new java.util.HashMap<String, String>();

	public LeveResourceBundle(List<Message> messagesList) {
		for (Message message : messagesList) {
			messages.put(message.getKey(), message.getMessage());
		}
	}
	
	@Override
	protected Object handleGetObject(String key) {
		if (key == null) {
			throw new NullPointerException();
		}

		if(messages.containsKey(key)){
			return messages.get(key);
		}
		return "???"+key+"???";
	}

	@Override
	public Enumeration<String> getKeys() {
		return Collections.enumeration(messages.keySet());
	}

}
