package org.leve.web.ui.tags;

@SuppressWarnings("serial")
public abstract class FormContainerAbstractTag extends LeveBaseTag  {
	private String dto = "";
	
	
	public String getDto() {
		return dto;
	}

	public void setDto(String dto) {
		this.dto = dto;
	}
}
