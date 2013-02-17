package org.leve.web.ui.tags;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.Id;

import org.leve.annotation.LeveDesc;
import org.leve.annotation.LeveDomain;
import org.leve.annotation.LeveKey;
import org.leve.beans.DomainValue;
import org.leve.ejb.DomainValueBean;
import org.leve.reflections.ReflectionUtil;
import org.leve.web.ui.tags.html.AbstractHtmlElement;
import org.leve.web.ui.tags.html.DivHtmlElement;
import org.leve.web.ui.tags.html.OptionHtmlElement;
import org.leve.web.ui.tags.html.SelectHtmlElement;

@SuppressWarnings("serial")
public class SelectTag extends InputTag {

	@EJB
	DomainValueBean domainValueBean;
	
	@Override
	protected AbstractHtmlElement getInputElement() {
		DivHtmlElement root = new DivHtmlElement("controls");

		SelectHtmlElement select = null;
		
		if(getFieldAttribute().isAnnotationPresent(LeveDomain.class)){
			select = new SelectHtmlElement(null, null,getAttribute());
			addOptionsDomain(select);
		} else {
			// many to one
			// options load by ajax. Can`t determine ejb here!
			select = new SelectHtmlElement("leve-select-bean", null,getAttribute() + "."+ getIdFieldManyToOne());
			addOptionsBeans(select, getFieldAttribute().getType());
		}
		
		root.addChild(select);

		root.addChild(getRequeiredElement());

		return root;
	}

	protected void addOptionsBeans(SelectHtmlElement select, Class<?> type) {
		List<?> l = uiBean.lsitBean(type);
		
		for (Object bean : l) {
			Long id = (Long) ReflectionUtil.getFieldValueWithAnnotation(bean, Id.class);
			Object key = ReflectionUtil.getFieldValueWithAnnotation(bean, LeveKey.class);
			Object desc = ReflectionUtil.getFieldValueWithAnnotation(bean, LeveDesc.class);
			
			if(key != null && key != desc){
				select.addChild(new OptionHtmlElement(null, key.toString() +" - " + desc.toString(), id.toString()));
			} else {
				select.addChild(new OptionHtmlElement(null, desc.toString(), id.toString()));
			}
		}
	}

	private void addOptionsDomain(SelectHtmlElement select) {
		LeveDomain leveDomainAnnotation = getFieldAnnotation(LeveDomain.class);
		Collection<DomainValue> dvList = domainValueBean.listByDomain(leveDomainAnnotation.value());
		
		for (DomainValue domainValue : dvList) {
			select.addChild(new OptionHtmlElement(null, domainValue.getDescription(), domainValue.getValue()));
		}
		
	}
	
	
}
