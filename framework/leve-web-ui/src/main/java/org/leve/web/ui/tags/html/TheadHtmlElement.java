package org.leve.web.ui.tags.html;

public class TheadHtmlElement extends AbstractHtmlElement {
	
	public TheadHtmlElement() {
		super();
	}
	
	public TheadHtmlElement(String styleClass, String style, String id) {
		super(styleClass, style, id);
	}
	

	protected static final String HTML_TAG_THEAD = "thead";
	
	@Override
	protected String getElementName() {
		return HTML_TAG_THEAD;
	}

}
