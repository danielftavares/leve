package org.leve.web.ui.tags;

@SuppressWarnings("serial")
public class CollumnTag extends LeveBaseTag{

	private String attribute;
	private String label;
	private Integer width;
	
	@Override
	protected String writeBeforeBody() {
		StringBuilder out = new StringBuilder();
		print(out, "{property: '"+getAttribute()+"',label: '"+getMessage(getLabel())+"',width: "+getWidth()+"},");
		return out.toString();
	}

	@Override
	protected String writeAfterBody() {
		return "";
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}
}
