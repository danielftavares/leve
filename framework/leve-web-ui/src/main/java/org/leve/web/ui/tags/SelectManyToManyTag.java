package org.leve.web.ui.tags;

import java.lang.reflect.Field;

import javax.persistence.Id;

import org.leve.reflections.ReflectionUtil;
import org.leve.web.ui.tags.html.AbstractHtmlElement;
import org.leve.web.ui.tags.html.ButtonHtmlElement;
import org.leve.web.ui.tags.html.DivHtmlElement;
import org.leve.web.ui.tags.html.PureTextHtmlElement;
import org.leve.web.ui.tags.html.SelectHtmlElement;

@SuppressWarnings("serial")
public class SelectManyToManyTag extends SelectTag {

	private String rightAttribute;
	private String leftAttribute;
	
	@Override
	protected AbstractHtmlElement getInputElement() {
		DivHtmlElement root = new DivHtmlElement("controls leve-select-many");
		
		DivHtmlElement left = getLeftItem();
		
		DivHtmlElement right = getRightItem();
		
		
		root.addChild(left);
		root.addChild(right);
		return root;
	}

	protected DivHtmlElement getRightItem() {
		DivHtmlElement right = new DivHtmlElement("leve-select-right span4");
		
		
		Class<?> relType = ReflectionUtil.getFieldGenericType(getFieldAttribute());
		Class<?> leftType = ReflectionUtil.getField(relType, getLeftAttribute()).getType();
		Field f = ReflectionUtil.getFieldWithAnnotation(leftType, Id.class);
		
		SelectHtmlElement select = new SelectHtmlElement(null, null, getAttribute() + "." + getLeftAttribute() + "." + f.getName() );
		select.setSelectMany(true);
		
		
		right.addChild(select);
		
		ButtonHtmlElement btn = new ButtonHtmlElement("btn btn-mini");
		btn.addChild(new PureTextHtmlElement("remove"));
		right.addChild(btn);
		return right;
	}

	protected DivHtmlElement getLeftItem() {
		DivHtmlElement left = new DivHtmlElement("leve-select-left span4");
		
		SelectHtmlElement select = new SelectHtmlElement(null);
		left.addChild(select);
		select.setSelectMany(true);
		
		// List<XX>
		Class<?> relType = ReflectionUtil.getFieldGenericType(getFieldAttribute());
		Class<?> leftType = ReflectionUtil.getField(relType, getLeftAttribute()).getType();
		addOptionsBeans(select, leftType);
		
		ButtonHtmlElement btn = new ButtonHtmlElement("btn btn-mini");
		btn.addChild(new PureTextHtmlElement("add"));
		left.addChild(btn);
		return left;
	}
	
	
	@Override
	public void release() {
		super.release();
		rightAttribute = null;
		leftAttribute = null;
	}
	
	
	public String getRightAttribute() {
		return rightAttribute;
	}


	public void setRightAttribute(String rightAttribute) {
		this.rightAttribute = rightAttribute;
	}


	public String getLeftAttribute() {
		return leftAttribute;
	}


	public void setLeftAttribute(String leftAttribute) {
		this.leftAttribute = leftAttribute;
	}
	
}
