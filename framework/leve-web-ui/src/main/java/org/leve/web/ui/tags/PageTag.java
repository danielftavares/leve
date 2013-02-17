package org.leve.web.ui.tags;

@SuppressWarnings("serial")
public class PageTag extends LeveBaseTag {

	public static final String PAGE_TYPE_CAD = "cad";
	public static final String PAGE_TYPE_FIND = "find";
	public static final String PAGE_TYPE_MAIN = "main";
	public static final String PAGE_TYPE_LOGIN = "login";
	
	private String pageType = PAGE_TYPE_CAD;
	private String title = "";

	@Override
	protected String writeBeforeBody(){
		StringBuilder out = new StringBuilder();
		print(out, "<!DOCTYPE html>");
		printStartTag(out, HTML_TAG_HTML);
		return out.toString();
	}

	@Override
	protected String writeAfterBody(){
		StringBuilder out = new StringBuilder();
		if(isModule()){
			printEndTag(out, HTML_TAG_DIV); // "class","container-fluid" // view header
		}
		
		printEndTag(out, HTML_TAG_BODY);
		printEndTag(out, HTML_TAG_HTML);
		return out.toString();
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
	
	protected boolean isModule(){
		return isCad() || isFind();
	}
	
	protected boolean isCad(){
		return pageType.equals(PAGE_TYPE_CAD);
	}
	
	protected boolean isFind(){
		return pageType.equals(PAGE_TYPE_FIND);
	}
	
	protected boolean isMain(){
		return pageType.equals(PAGE_TYPE_MAIN);
	}
	
	protected boolean isLogin(){
		return pageType.equals(PAGE_TYPE_LOGIN);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
