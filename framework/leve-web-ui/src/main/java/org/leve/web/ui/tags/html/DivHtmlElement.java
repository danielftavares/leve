package org.leve.web.ui.tags.html;

public class DivHtmlElement extends AbstractHtmlElement {

	public DivHtmlElement(String styleClass, String style, String id) {
		super(styleClass, style, id);
	}
	
	public DivHtmlElement(String styleClass, String style) {
		super(styleClass, style, null);
	}
	
	public DivHtmlElement(String styleClass) {
		super(styleClass, null, null);
	}

	protected static final String HTML_TAG_DIV = "div";
	
	@Override
	protected String getElementName() {
		return HTML_TAG_DIV;
	}

}
