package org.leve.web.ui.tags.html;

public class LiHtmlElement extends AbstractHtmlElement {
	public LiHtmlElement(String styleClass, String style, String id) {
		super(styleClass, style, id);
	}
	
	public LiHtmlElement(String styleClass) {
		super(styleClass, null, null);
	}
	
	public LiHtmlElement() {
		super();
	}

	protected static final String HTML_TAG_LI = "li";
	
	@Override
	protected String getElementName() {
		return HTML_TAG_LI;
	}

}
