package org.leve.web.ui.tags.html;

public class InputHtmlElement extends AbstractHtmlElement {

	private String type= "text";

	public InputHtmlElement(String styleClass, String style, String id, String type) {
		super(styleClass, style, id);
		this.type = type;
	}
	public InputHtmlElement(String styleClass, String style, String id) {
		super(styleClass, style, id);
	}
	public InputHtmlElement(String styleClass) {
		super(styleClass, null, null);
	}

	protected static final String HTML_TAG_INPUT = "input";
	
	@Override
	protected String getElementName() {
		return HTML_TAG_INPUT;
	}
	
	@Override
	public void print(StringBuilder sb) {
		sb.append('<').append(getElementName());
		printAttribute(sb, "id", getId());
		printAttribute(sb, "name", getId());
		printAttribute(sb, "class", getStyleClass());
		printAttribute(sb, "style", getStyle());
		printAttribute(sb, "type", getType());
		for (String attr : super.customAttr.keySet()) {
			printAttribute(sb, attr, super.customAttr.get(attr));
		}
		sb.append('>');
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
