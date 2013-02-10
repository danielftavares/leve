package org.leve.web.ui.tags.html;

public class AHtmlElement extends AbstractHtmlElement {
	public AHtmlElement(String styleClass, String style, String id) {
		super(styleClass, style, id);
	}
	
	public AHtmlElement(String styleClass) {
		super(styleClass, null, null);
	}
	
	public AHtmlElement() {
		super();
	}

	protected static final String HTML_TAG_A = "a";
	
	public void setHref(String href){
		super.addCustomAttribute("href", href);
	}
	
	public String getHref(){
		return customAttr.get("href");
	}
	
	@Override
	protected String getElementName() {
		return HTML_TAG_A;
	}

}
