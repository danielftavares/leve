package org.leve.web.ui.tags.html;

public class SelectHtmlElement extends AbstractHtmlElement {

	public SelectHtmlElement(String styleClass, String style, String id) {
		super(styleClass, style, id);
		addCustomAttribute("name", id);
	}
	
	public SelectHtmlElement(String styleClass, String style) {
		super(styleClass, style, null);
	}
	
	public SelectHtmlElement(String styleClass) {
		super(styleClass, null, null);
	}
	
	public SelectHtmlElement() {
		super();
	}
	
	public void setSelectMany(boolean selectMany){
		if (selectMany){
			addCustomAttribute("multiple", "multiple");
		} else {
			customAttr.remove("multiple");
		}
	}

	protected static final String HTML_TAG_SELECT = "select";
	
	
	@Override
	protected String getElementName() {
		return HTML_TAG_SELECT;
	}

}
