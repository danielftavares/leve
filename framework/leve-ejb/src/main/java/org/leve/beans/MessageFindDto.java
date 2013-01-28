package org.leve.beans;

import java.util.Locale;

import org.leve.annotation.LeveDomain;

public class MessageFindDto {

	
	private String key;
	
	private String message;
	
	@LeveDomain("LANGUAGE")
	private Locale locale;
	

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	
}
