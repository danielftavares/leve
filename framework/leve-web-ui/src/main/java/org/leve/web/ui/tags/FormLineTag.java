package org.leve.web.ui.tags;

@SuppressWarnings("serial")
public class FormLineTag extends LeveBaseTag {

	@Override
	protected String writeBeforeBody() {
		StringBuilder out = new StringBuilder();
		super.printStartTag(out, HTML_TAG_DIV, "class", "span12");
		return out.toString();
	}

	@Override
	protected String writeAfterBody() {
		StringBuilder out = new StringBuilder();
		super.printEndTag(out, HTML_TAG_DIV);
		return out.toString();
	}

}
