package org.leve.web.ui.tags;

import org.leve.web.ui.tags.html.LabelHtmlElement;

@SuppressWarnings("serial")
public class LabelTag extends LeveBaseTag {

	private String label;

	@Override
	protected String writeBeforeBody() {
		StringBuilder out = new StringBuilder();

		LabelHtmlElement l = new LabelHtmlElement();
		l.setLabel(getMessage(label));
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

}
