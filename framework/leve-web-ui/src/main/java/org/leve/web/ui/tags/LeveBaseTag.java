package org.leve.web.ui.tags;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Locale;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import org.leve.bundles.ResourceBundleFactory;
import org.leve.ejb.UiBean;
import org.leve.web.ui.servlets.LeveTagResourcesServlet;

@SuppressWarnings("serial")
public abstract class LeveBaseTag extends BodyTagSupport implements Cloneable {
	
	@Inject 
	private ResourceBundleFactory bundleFactory;
	
	@EJB
	protected UiBean uiBean;
	
	protected static final Logger LOGGER = Logger.getLogger(LeveBaseTag.class.getName());
	
	private static final Calendar startDate = Calendar.getInstance();
	
	protected static final String NULL_RETURN = "";
	
	protected static final String HTML_TAG_HTML = "html";
	protected static final String HTML_TAG_HEAD = "head";
	protected static final String HTML_TAG_TITLE = "title";
	protected static final String HTML_TAG_SCRIPT = "script";
	protected static final String HTML_TAG_FORM = "form";
	protected static final String HTML_TAG_DIV = "div";
	protected static final String HTML_TAG_H1 = "h1";
	protected static final String HTML_TAG_H2 = "h2";
	protected static final String HTML_TAG_H3 = "h3";
	protected static final String HTML_TAG_H4 = "h4";
	protected static final String HTML_TAG_H5 = "h5";
	protected static final String HTML_TAG_H6 = "h6";
	protected static final String HTML_TAG_LABEL = "label";
	protected static final String HTML_TAG_INPUT = "input";
	protected static final String HTML_TAG_A = "a";
	protected static final String HTML_TAG_I = "i";
	protected static final String HTML_TAG_P = "p";
	protected static final String HTML_TAG_UL = "ul";
	protected static final String HTML_TAG_LI = "li";
	protected static final String HTML_TAG_BODY = "body";
	protected static final String HTML_TAG_BUTTON = "button";
	protected static final String HTML_TAG_TABLE = "table";
	protected static final String HTML_TAG_TFOOT = "tfoot";
	protected static final String HTML_TAG_THEAD = "thead";
	protected static final String HTML_TAG_TBODY = "tbody";
	protected static final String HTML_TAG_TR = "tr";
	protected static final String HTML_TAG_TH = "th";
	protected static final String HTML_TAG_SPAN = "span";
	
	protected static final Integer FRAME_TYPE_CAD = 1;
	protected static final Integer FRAME_TYPE_FIND = 2;
	protected static final Integer FRAME_TYPE_LOOKUP = 3;
	
	Collection<LeveBaseTag> childs = new ArrayList<LeveBaseTag>();
	@Override
	public void setParent(Tag tag) {
		if(tag instanceof LeveBaseTag){
			((LeveBaseTag) tag).addChild(this);
		}
		
		super.setParent(tag);
	}
	
	protected abstract String writeBeforeBody();
	protected abstract String writeAfterBody();
	
	
    @Override
    public final int doAfterBody() throws JspException {
    	return super.doAfterBody();
    }
    
    @Override
    public final int doEndTag() throws JspException {
    	try{
	    	BodyContent bodyContent = super.getBodyContent();
	    	String      bodyString  = "";
	    	JspWriter   out = pageContext.getOut();
	    	if(bodyContent != null){
	    		bodyString  = bodyContent.getString();
	    		out         = bodyContent.getEnclosingWriter();
	    	}
	    	
	    	// inicia a tag
	        out.print(writeBeforeBody());
	        //coloca conteudo da tag
	        out.write(bodyString);
	        // termina a tag
	        out.print(writeAfterBody());
    	} catch (IOException exc) {
    		throw new JspException(exc);
    	} finally {
    		release();
    	}
        
        
    	return super.doEndTag();
    }
    
    @Override
    public void release() {
    	super.release();
    	id = null;
    	childs = new ArrayList<LeveBaseTag>();
    }

    @Override
    protected void finalize() throws Throwable {
    	super.finalize();
    	id = null;
    	childs = new ArrayList<LeveBaseTag>();
    }
	private void addChild(LeveBaseTag tag){
		try {
			childs.add((LeveBaseTag) tag.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	
	protected PageTag getPage(){
		if(this instanceof PageTag){
			return (PageTag)this;
		} else {
			return (PageTag) findAncestorWithClass(this, PageTag.class);
		}
	}
	
	
	protected void print(StringBuilder out, String txt){
		out.append(txt);
	}
	
	protected void printStartTag(StringBuilder out, String htmlTag){
		print(out, "<"+htmlTag+">");
	}
	
	protected void printStartTag(StringBuilder out, String htmlTag, String ... attsVals ){
		print(out, "<"+htmlTag+" ");
		for (int i = 0; i < attsVals.length; i = i + 2) {
			if(attsVals[i] == null || attsVals[i].isEmpty() || 
			   attsVals[i+1] == null || attsVals[i+1].isEmpty() ){
				continue;
			}
			print(out, attsVals[i]);
			print(out, "=\"");
			print(out, attsVals[i+1]);
			print(out, "\" ");
		}
		print(out, ">");
	}
	
	protected void printEndTag(StringBuilder out, String htmlTag){
		print(out, "</"+htmlTag+">");
	}
	
	
	protected void printImportScript(StringBuilder out, String scriptFile){
		printStartTag(out, HTML_TAG_SCRIPT, 
							"type", "text/javascript",
							"src",getResourceBaseUri()+"/"+scriptFile+"?"+getPreventCacheAppender() );
		printEndTag(out, HTML_TAG_SCRIPT);
	}
	
	protected void printImportCss(StringBuilder out, String cssFile){
		print(out, "<link rel=\"stylesheet\" href=\""+getResourceBaseUri()+"/"+cssFile+"?"+getPreventCacheAppender()+"\" rel=\"stylesheet\" /> ");
	}
	
   	public long getPreventCacheAppender() {
   		return startDate.getTimeInMillis();
	}
   	
	protected FormContainerAbstractTag getFormTag() {
		LeveBaseTag tag = this;
		do {
			tag = (LeveBaseTag) tag.getParent();
		} while ( !(tag instanceof FormContainerAbstractTag));
		return (FormContainerAbstractTag) tag;
	}
	

   	protected String getDateFormatMaskRequestLocale(){
   		return getDateFormatRequestLocale().replaceAll("m", "9").replaceAll("y", "9").replaceAll("d", "9");// MONTH
   	}

   	protected String getDateFormatRequestLocale(){
   		SimpleDateFormat sf = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.MEDIUM, getRequestLocale());
   		String p2 = sf.toLocalizedPattern();
   		return p2.replaceAll("MM", "mm");// MONTH
   	}
   	
	protected String getResourceBaseUri(){
		return pageContext.getServletContext().getContextPath() + "/" + LeveTagResourcesServlet.RESOURCE_SERVLET_URI;
	}
	
	protected String getRequestLocaleString() {
		return getRequestLocale().toString().replaceAll("_", "-");
	}
	
	protected Locale getRequestLocale() {
		return pageContext.getRequest().getLocale();
	}
	
	protected String getMessage(String msgKey){
		return bundleFactory.getResourceBundle(getRequestLocale()).getString(msgKey);
	}
	
	protected int getFrameType(){
		String frameType = pageContext.getRequest().getParameter("frameType");
		if(frameType == null || frameType.isEmpty()){
			return 0;
		} else {
			return Integer.parseInt(frameType);
		}
	}
	
	protected boolean isLookUpPage(){
		return getFrameType() == FRAME_TYPE_LOOKUP;
	}
	
	protected String getJaxPath() {
		return pageContext.getServletContext().getContextPath() + "/leve";
	}
	
	private String generateId(){
		Long idCount = (Long) pageContext.getRequest().getAttribute("LEVE_ID_COUNT");
		if(idCount == null){
			idCount = 0l;
		}
		idCount = idCount + 1;
		pageContext.getRequest().setAttribute("LEVE_ID_COUNT", idCount);
		return "leve_elid_"+idCount;
	}
	
	public String getId() {
		if (id == null || id.isEmpty()) {
			id = generateId();
		}
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
