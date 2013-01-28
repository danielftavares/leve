package org.leve.web.ui.tags.html;

public class UlHtmlElement extends AbstractHtmlElement {
	public UlHtmlElement(String styleClass, String style, String id) {
		super(styleClass, style, id);
	}
	public UlHtmlElement(String styleClass) {
		super(styleClass, null, null);
	}
	public UlHtmlElement() {
		super();
	}

	protected static final String HTML_TAG_UL = "ul";
	
	@Override
	protected String getElementName() {
		return  HTML_TAG_UL;
	}

}
