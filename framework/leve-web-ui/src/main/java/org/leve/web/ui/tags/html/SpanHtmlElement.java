package org.leve.web.ui.tags.html;

public class SpanHtmlElement extends AbstractHtmlElement {
	public SpanHtmlElement(String styleClass, String style, String id) {
		super(styleClass, style, id);
	}
	
	public SpanHtmlElement(String styleClass) {
		super(styleClass, null, null);
	}
	
	public SpanHtmlElement() {
		super();
	}

	protected static final String HTML_TAG_SPAN = "span";
	
	@Override
	protected String getElementName() {
		return HTML_TAG_SPAN;
	}

}
