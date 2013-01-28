package org.leve.web.ui.tags;

import org.leve.web.ui.tags.html.AbstractHtmlElement;
import org.leve.web.ui.tags.html.ButtonHtmlElement;
import org.leve.web.ui.tags.html.DivHtmlElement;
import org.leve.web.ui.tags.html.IHtmlElement;
import org.leve.web.ui.tags.html.PureTextHtmlElement;
import org.leve.web.ui.tags.html.SelectHtmlElement;
import org.leve.web.ui.tags.html.SpanHtmlElement;
import org.leve.web.ui.tags.html.TableHtmlElement;
import org.leve.web.ui.tags.html.TfootHtmlElement;
import org.leve.web.ui.tags.html.ThHtmlElement;
import org.leve.web.ui.tags.html.TheadHtmlElement;
import org.leve.web.ui.tags.html.TrHtmlElement;

@SuppressWarnings("serial")
public class TableTag extends LeveBaseTag{
	
	private String formId =  "";

	@Override
	protected String writeBeforeBody() {
		
		StringBuilder out = new StringBuilder();

		AbstractHtmlElement root = new DivHtmlElement("container-fluid fuelux");
		
		AbstractHtmlElement talbe = root.addChild(new TableHtmlElement("table table-bordered datagrid table-hover table-condensed", null, getId()));
		talbe.addChild(new TheadHtmlElement());
		
		AbstractHtmlElement th = talbe.addChild(new TfootHtmlElement()).addChild(new TrHtmlElement()).addChild(new ThHtmlElement());
		
		AbstractHtmlElement div =  th.addChild(new DivHtmlElement("datagrid-footer-left", "display:none")).addChild(new DivHtmlElement("grid-controls"));
		
		AbstractHtmlElement span = div.addChild(new SpanHtmlElement());
		span.addChild(new SpanHtmlElement("grid-start"));
		span.addChild(new PureTextHtmlElement(" - "));
		span.addChild(new SpanHtmlElement("grid-end"));
		span.addChild(new PureTextHtmlElement(" of "));
		span.addChild(new SpanHtmlElement("grid-count"));
		
		div =  th.addChild(new DivHtmlElement("datagrid-footer-right", "display:none")).addChild(new DivHtmlElement("grid-pager"));
		div.addChild(new ButtonHtmlElement("search", "display: none", null));
		div.addChild(new ButtonHtmlElement("btn grid-prevpage")).addChild(new IHtmlElement("icon-chevron-left"));
		div.addChild(new SpanHtmlElement()).addChild(new PureTextHtmlElement("Page"));
		div.addChild(new SelectHtmlElement());
		
		span = div.addChild(new SpanHtmlElement());
		span.addChild(new PureTextHtmlElement("of "));
		span.addChild(new SpanHtmlElement("grid-pages"));
		
		div.addChild(new ButtonHtmlElement("btn grid-nextpage")).addChild(new IHtmlElement("icon-chevron-right"));
		
		root.print(out);
		
		
		
		out.append("<script type=\"text/javascript\">" + "$(function() { " +
				"var ds_"+getId()+" = new LeveDataSource({columns: [");
		
		return out.toString();
	}

	@Override
	protected String writeAfterBody() {
		StringBuilder out = new StringBuilder();
		
		
		out.append("],"+
				"data: [],"+
				"formid: '"+getFormId()+"' });" +
				
				"$('#"+getId()+"').datagrid({dataSource: ds_"+getId()+"});"+
				
				"});</script>");
		
		return out.toString();
	}
	
	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}


}
