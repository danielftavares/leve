package org.leve.web.ui.tags.html;

public class TableHtmlElement extends AbstractHtmlElement {
	public TableHtmlElement(String styleClass, String style, String id) {
		super(styleClass, style, id);
	}
	
	protected static final String HTML_TAG_TABLE = "table";
	
	@Override
	protected String getElementName() {
		return HTML_TAG_TABLE;
	}

}
