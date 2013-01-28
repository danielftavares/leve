package org.leve.web.ui.tags.html;

public class TrHtmlElement extends AbstractHtmlElement {
	public TrHtmlElement(String styleClass, String style, String id) {
		super(styleClass, style, id);
	}
	
	public TrHtmlElement() {
		super();
	}

	protected static final String HTML_TAG_TR = "tr";
	
	@Override
	protected String getElementName() {
		return HTML_TAG_TR;
	}

}
