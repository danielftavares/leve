package org.leve.web.ui.tags.html;

public class IHtmlElement extends AbstractHtmlElement {

	public IHtmlElement(String styleClass, String style, String id) {
		super(styleClass, style, id);
	}
	public IHtmlElement(String styleClass) {
		super(styleClass, null, null);
	}

	protected static final String HTML_TAG_I = "i";
	
	@Override
	protected String getElementName() {
		return HTML_TAG_I;
	}

}
