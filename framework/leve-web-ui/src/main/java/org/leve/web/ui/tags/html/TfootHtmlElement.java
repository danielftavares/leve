package org.leve.web.ui.tags.html;

public class TfootHtmlElement extends AbstractHtmlElement {
	
	public TfootHtmlElement() {
		super();
	}
	
	public TfootHtmlElement(String styleClass, String style, String id) {
		super(styleClass, style, id);
	}
	

	protected static final String HTML_TAG_TFOOT = "tfoot";
	
	@Override
	protected String getElementName() {
		return HTML_TAG_TFOOT;
	}

}
