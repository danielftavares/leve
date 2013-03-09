package org.leve.web.ui.tags;

import org.leve.web.ui.tags.html.LabelHtmlElement;

@SuppressWarnings("serial")
public class LabelTag extends LeveBaseTag {

	private String label;
	private String foratt;


	@Override
	protected String writeBeforeBody() {
		StringBuilder out = new StringBuilder();

		LabelHtmlElement l = new LabelHtmlElement(getMessage(label));
		l.setForAtt(getForatt());
		l.print(out);

		return out.toString();
	}


	@Override
	protected String writeAfterBody() {
		return NULL_RETURN;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	

	public String getForatt() {
		return foratt;
	}


	public void setForatt(String foratt) {
		this.foratt = foratt;
	}

}
