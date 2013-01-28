package org.leve.web.ui.tags.html;

public class PHtmlElement extends AbstractHtmlElement {
	public PHtmlElement(String styleClass, String style, String id) {
		super(styleClass, style, id);
	}
	
	public PHtmlElement(String styleClass) {
		super(styleClass, null, null);
	}
	
	public PHtmlElement() {
		super();
	}

	protected static final String HTML_TAG_P = "p";
	
	@Override
	protected String getElementName() {
		return HTML_TAG_P;
	}

}
