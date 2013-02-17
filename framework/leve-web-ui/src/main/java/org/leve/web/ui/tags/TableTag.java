package org.leve.web.ui.tags;

import org.leve.web.ui.tags.html.AbstractHtmlElement;
import org.leve.web.ui.tags.html.ButtonHtmlElement;
import org.leve.web.ui.tags.html.DivHtmlElement;
import org.leve.web.ui.tags.html.HXHtmlElement;
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
public class TableTag extends FormContainerAbstractTag{
	
	private static final String LEVE_MODAL_ID = "leve-findmodal";
	private String listAction;
	
	@Override
	protected String writeBeforeBody() {
		
		StringBuilder out = new StringBuilder();

		AbstractHtmlElement root = new DivHtmlElement("container-fluid fuelux leve-grid", null, "table-"+getId());
		
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
		
		
		div.addChild(new ButtonHtmlElement("btn grid-modalfind"))
			.addCustomAttribute("data-toggle", "modal")
			.addCustomAttribute("data-target", "#"+resolveModalId())
			.addChild(new IHtmlElement("icon-filter"));
		
		root.print(out);
		
		out.append("<script type=\"text/javascript\"> var collumns_"+getId()+" = [];</script>");
		
		return out.toString();
	}

	@Override
	protected String writeAfterBody() {
		StringBuilder out = new StringBuilder();
		out.append("<script type=\"text/javascript\">" + "$(function() { " +
				"var ds_"+getId()+" = new LeveDataSource({columns: collumns_"+getId()+","+
				"data: [],"+
				"modalid: '"+resolveModalId()+"',"+
				"listAction: '" +getJaxPath() +getListAction()+"' });" +
				
				"$('#"+getId()+"').datagrid({dataSource: ds_"+getId()+", stretchHeight: true});"+
				
				"});</script>");
		printModal(out);
		
		return out.toString();
	}
	

	private void printModal(StringBuilder out) {
		AbstractHtmlElement root = new DivHtmlElement("modal hide fade leve-modalfind", null, resolveModalId())
									.addCustomAttribute("tabindex", "-1")
									.addCustomAttribute("role", "dialog")
									.addCustomAttribute("aria-labelledby", "myModalLabel")
									.addCustomAttribute("aria-hidden", "true");
		
		
		AbstractHtmlElement header = root.addChild(new DivHtmlElement("modal-header"));
		//close btn
		header.addChild(new ButtonHtmlElement("close")
							.addCustomAttribute("data-dismiss","modal")
							.addCustomAttribute("aria-hidden","true")).addChild(new PureTextHtmlElement("x"));
		//close title
		header.addChild(new HXHtmlElement(3).addCustomAttribute("id", "myModalLabel")).addChild(new PureTextHtmlElement("Pesquisa ..."));
		
		
		//body
		root.addChild(new DivHtmlElement("modal-body form-horizontal"));
		
		
		AbstractHtmlElement footer = root.addChild(new DivHtmlElement("modal-footer"));
		footer.addChild(new ButtonHtmlElement("btn")
							.addCustomAttribute("data-dismiss", "modal")
							.addCustomAttribute("aria-hidden", "true")).addChild(new PureTextHtmlElement("Close"));
		footer.addChild(new ButtonHtmlElement("btn btn-primary leve-filter").addCustomAttribute("data-tableid", "table-"+getId())).addChild(new PureTextHtmlElement("Find"));
		
		root.print(out);
		/*
		<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    <h3 id="myModalLabel">Modal header</h3>
		  </div>
		  <div class="modal-body">
		    <p>One fine body…</p>
		  </div>
		  <div class="modal-footer">
		    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		    <button class="btn btn-primary">Save changes</button>
		  </div>
		</div>
		*/
	}

	private String resolveModalId() {
		return LEVE_MODAL_ID+"_"+getId();
	}
	
	public String getListAction() {
		return listAction;
	}

	public void setListAction(String listAction) {
		this.listAction = listAction;
	}
	
}
