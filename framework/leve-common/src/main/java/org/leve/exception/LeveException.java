package org.leve.exception;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javax.validation.ConstraintViolation;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
public class LeveException extends RuntimeException {
	
	protected List<LeveException> errors;

	private String field;
	
	private Object bean;
	
	private LeveException parent;
	
	private ResourceBundle resourceBundle;
	
	public LeveException() {
		super();
	}
	
	public LeveException(String message) {
		super(message);
	}
	
	public LeveException(String message, String field, Object bean, LeveException parent) {
		super(message);
		this.field = field;
		this.bean = bean;
		this.parent = parent;
	}
	
	public LeveException(Exception e) {
		super(e);
	}

	public void addError(String message, String field, Object bean){
		if(errors == null){
			errors = new LinkedList<LeveException>();
		}
		errors.add(new LeveException(message,field, bean, this));
	}
	
	public void addError(ConstraintViolation<?> violation){
		addError(violation.getMessageTemplate(), violation.getPropertyPath().toString(), violation.getRootBean());
	}
	
	@JsonProperty
	public boolean isLeveError() {
		return true;
	}
	
	@JsonIgnore
	@Override
	public StackTraceElement[] getStackTrace() {
		return super.getStackTrace();
	}

	public void checkThrowErrorsList(){
		if(errors != null && !errors.isEmpty()){
			throw this;
		}
	}
	
	@Override
	public String getLocalizedMessage() {
		ResourceBundle resolveResourceBundle = resolveResourceBundle();
		if(resolveResourceBundle == null){
			return super.getLocalizedMessage();
		}
		if(getField() != null && !getField().isEmpty()){
			return String.format(resolveResourceBundle.getString(this.getMessage()), resolveResourceBundle.getString(getField()));
		} else {
			return resolveResourceBundle.getString(this.getMessage());
		}
	}
	
	private ResourceBundle resolveResourceBundle(){
		ResourceBundle resourceBundle = null;
		if(this.resourceBundle != null){
			resourceBundle = this.resourceBundle;
		} else if(getParent() != null && getParent().getResourceBundle() != null){
			resourceBundle = getParent().getResourceBundle();
		}
		return resourceBundle;
	}
	
	public List<LeveException> getErrors() {
		return errors;
	}

	public void setErrors(List<LeveException> errors) {
		this.errors = errors;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
	@JsonIgnore
	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}
	
	@JsonIgnore
	public LeveException getParent() {
		return parent;
	}

	public void setParent(LeveException parent) {
		this.parent = parent;
	}

	@JsonIgnore
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}
}
