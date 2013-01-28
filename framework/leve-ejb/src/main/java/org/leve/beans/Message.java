package org.leve.beans;

import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.leve.annotation.LeveDomain;

@Entity
@Table(name="SYS_MESSAGE")
public class Message {

	@Id
	@SequenceGenerator(name = "SEQ_SYS_MESSAGE_GENERATOR", sequenceName = "SEQ_SYS_MESSAGE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SYS_MESSAGE_GENERATOR")
	@Column(name="id_message")
	private Long idMessage;
	
	@NotNull
	@Size(max=100)
	private String key;
	
	@NotNull
	@Size(max=100)
	private String message;
	
	@LeveDomain("LANGUAGE")
	@NotNull
	private Locale locale;


	public Long getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}

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
