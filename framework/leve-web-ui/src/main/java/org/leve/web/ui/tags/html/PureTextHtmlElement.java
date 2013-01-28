package org.leve.web.ui.tags.html;

public class PureTextHtmlElement extends AbstractHtmlElement {
	
	private String txt;
	
	public PureTextHtmlElement(String txt) {
		super();
		this.txt = txt;
	}
	
	

	@Override
	protected String getElementName() {
		return null;
	}
	
	public void print(StringBuilder sb) {
		sb.append(txt);
	};

	
	public String getTxt() {
		return txt;
	}



	public void setTxt(String txt) {
		this.txt = txt;
	}
}
