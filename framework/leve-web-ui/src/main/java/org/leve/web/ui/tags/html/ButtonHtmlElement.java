package org.leve.web.ui.tags.html;

public class ButtonHtmlElement extends AbstractHtmlElement {

	public ButtonHtmlElement(String styleClass, String style, String id) {
		super(styleClass, style, id);
	}
	public ButtonHtmlElement(String styleClass) {
		super(styleClass, null, null);
	}

	protected static final String HTML_TAG_BUTTON = "button";
	
	@Override
	protected String getElementName() {
		return HTML_TAG_BUTTON;
	}

}
