package org.leve.web.ui.tags.html;

public class ScriptHtmlElement extends AbstractHtmlElement {
	
	protected static final String HTML_TAG_SCRIPT = "script";
	
	private String script;
	
	public ScriptHtmlElement(String script) {
		super();
		this.script = script;
	}
	
	

	@Override
	protected String getElementName() {
		return HTML_TAG_SCRIPT;
	}
	
	public void print(StringBuilder sb) {
		sb.append("<script type=\"text/javascript\">");
		sb.append(getScript());
		sb.append("</script>");
	}



	public String getScript() {
		return script;
	}



	public void setScript(String script) {
		this.script = script;
	};

	
}
