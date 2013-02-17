package org.leve.web.ui.tags.html;

public class HXHtmlElement extends AbstractHtmlElement {
	int x;
	public HXHtmlElement(int x) {
		this();
		this.x = x;
	}
	
	private HXHtmlElement(){
		super();
	}
	

	protected static final String HTML_TAG_HX = "h";
	
	@Override
	protected String getElementName() {
		return HTML_TAG_HX + x;
	}

}
