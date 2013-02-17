package org.leve.web.ui.tags;

@SuppressWarnings("serial")
public class FilterTag extends InputTag {

	@Override
	protected String writeBeforeBody() {
		StringBuilder sb = new StringBuilder();
		printStartTag(sb, HTML_TAG_DIV,
				"id", "colf-"+getCollumnTag().getAttribute(),
				"class", "leve-tablecolldef",
				"data-property", getCollumnTag().getAttribute(),
				"data-label", getMessage(getCollumnTag().resolveLabel()),
				"data-width", getCollumnTag().getWidth().toString(),
				"data-tableid", getFormTag().getId()); //start 
		return sb.toString();
	}
	
	@Override
	protected String writeAfterBody() {
		StringBuilder sb = new StringBuilder();
		printEndTag(sb, HTML_TAG_DIV);//end 
		return sb.toString();
	}
	
	protected CollumnTag getCollumnTag(){
		LeveBaseTag tag = this;
		do {
			tag = (LeveBaseTag) tag.getParent();
		} while ( !(tag instanceof CollumnTag));
		return (CollumnTag) tag;
	}

}
