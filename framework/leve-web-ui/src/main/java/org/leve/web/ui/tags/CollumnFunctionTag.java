package org.leve.web.ui.tags;

@SuppressWarnings("serial")
public class CollumnFunctionTag extends CollumnTag {
	private String function = "Leve.return_value";
	private String icon = "icon-share";
	
	
	@Override
	protected String writeBeforeBody() {
		StringBuilder sb = new StringBuilder();

		sb.append("<script type=\"text/javascript\">collumns_"+getFormTag().getId() +".push({funccol: '"+getFunction()+"',icon: '"+getIcon()+"'});</script>");
		
		return sb.toString();
	}

	@Override
	protected String writeAfterBody() {
		return NULL_RETURN;
	}
	
	
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
