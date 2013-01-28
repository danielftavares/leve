package org.leve.web.ui.tags.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractHtmlElement {
	

	protected List<AbstractHtmlElement> children = new ArrayList<AbstractHtmlElement>();
	
	private String styleClass = null;
	private String style = null;
	private String id = null;
	protected Map<String, String> customAttr = new HashMap<String, String>();
	

	public AbstractHtmlElement() {
		super();
	}
	public AbstractHtmlElement(String styleClass, String style, String id) {
		this.styleClass = styleClass;
		this.style = style;
		this.id = id;
	}

	public AbstractHtmlElement addChild(AbstractHtmlElement element){
		if(element != null){
			children.add(element);
		}
		return element;
	}
	
	public void print(StringBuilder sb){
		sb.append('<').append(getElementName());
		printAttribute(sb, "id", getId());
		printAttribute(sb, "class", getStyleClass());
		printAttribute(sb, "style", getStyle());
		
		for (String attr : customAttr.keySet()) {
			printAttribute(sb, attr, customAttr.get(attr));
		}
		
		sb.append('>');
		for (AbstractHtmlElement child : children) {
			child.print(sb);
		}
		sb.append("</").append(getElementName()).append('>');
		
	}
	
	protected void printAttribute(StringBuilder sb,String attrName, String attrValue){
		if(attrValue != null && !attrValue.isEmpty()){
			sb.append(" "+attrName+"='").append(attrValue).append("'");
		}
	}
	
	public AbstractHtmlElement addCustomAttribute(String attr, String val){
		if(attr != null && !attr.isEmpty() && 
				val != null && !val.isEmpty()){
			customAttr.put(attr, val);
		}
		return this;
	}
	
	protected abstract String getElementName();
	
	public String getStyleClass() {
		return styleClass;
	}
	
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
