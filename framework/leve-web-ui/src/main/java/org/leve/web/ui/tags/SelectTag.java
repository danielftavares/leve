package org.leve.web.ui.tags;

import java.util.Collection;

import javax.ejb.EJB;

import org.leve.annotation.LeveDomain;
import org.leve.beans.DomainValue;
import org.leve.ejb.DomainValueBean;
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

		SelectHtmlElement select = new SelectHtmlElement(null, null,getAttribute());
		
		addOptions(select);
		
		root.addChild(select);

		root.addChild(getRequeiredElement());

		return root;
	}

	private void addOptions(SelectHtmlElement select) {
		LeveDomain leveDomainAnnotation = getFieldAnnotation(LeveDomain.class);
		Collection<DomainValue> dvList = domainValueBean.listByDomain(leveDomainAnnotation.value());
		
		for (DomainValue domainValue : dvList) {
			select.addChild(new OptionHtmlElement(null, domainValue.getDescription(), domainValue.getValue()));
		}
		
	}
	
	
}
