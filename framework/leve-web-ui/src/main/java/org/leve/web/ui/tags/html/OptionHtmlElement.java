package org.leve.web.ui.tags.html;

public class OptionHtmlElement extends AbstractHtmlElement {

	private String label;
	private String value;
	
	public OptionHtmlElement() {
		super();
	}

	protected static final String HTML_TAG_OPTION = "option";
	
	
	@Override
	public void print(StringBuilder sb) {
		sb.append('<').append(getElementName());
		printAttribute(sb, "id", getId());
		printAttribute(sb, "class", getStyleClass());
		printAttribute(sb, "style", getStyle());
		printAttribute(sb, "value", getValue());
		sb.append('>');
		sb.append(getLabel());
		sb.append("</").append(getElementName()).append('>');
	}
	
	@Override
	protected String getElementName() {
		return HTML_TAG_OPTION;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public OptionHtmlElement(String id, String label, String value) {
		super(null, null, id);
		this.label = label;
		this.value = value;
		
	}

}
