package org.leve.web.ui.tags.html;

public class ThHtmlElement extends AbstractHtmlElement {
	public ThHtmlElement(String styleClass, String style, String id) {
		super(styleClass, style, id);
	}
	
	public ThHtmlElement() {
		super();
	}

	protected static final String HTML_TAG_TH = "th";
	
	@Override
	protected String getElementName() {
		return HTML_TAG_TH;
	}

}
