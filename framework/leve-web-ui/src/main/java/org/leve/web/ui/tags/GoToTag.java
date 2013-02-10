package org.leve.web.ui.tags;

import org.leve.web.ui.tags.html.AHtmlElement;
import org.leve.web.ui.tags.html.LiHtmlElement;
import org.leve.web.ui.tags.html.PureTextHtmlElement;

@SuppressWarnings("serial")
public class GoToTag extends LookUpTag {

	@Override
	protected String writeBeforeBody() {
		StringBuilder out = new StringBuilder();
		LiHtmlElement li = new LiHtmlElement("leve-goto-item");
		
		AHtmlElement link = new AHtmlElement();
		link.setHref("#");
		link.addChild(new PureTextHtmlElement(getMessage(resolveLabel())));
		link.addCustomAttribute("data-page", getBaseCadUrl());
		link.addCustomAttribute("data-id", getAttribute()+"."+getIdFieldManyToOne());
		li.addChild(link);
		
		li.print(out);
		return out.toString();
	}

	@Override
	protected String writeAfterBody() {
		// TODO Auto-generated method stub
		return NULL_RETURN;
	}

}
