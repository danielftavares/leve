package org.leve.web.ui.tags;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import org.leve.web.ui.tags.html.AbstractHtmlElement;
import org.leve.web.ui.tags.html.HXHtmlElement;
import org.leve.web.ui.tags.html.LiHtmlElement;
import org.leve.web.ui.tags.html.PureTextHtmlElement;
import org.leve.web.ui.tags.html.UlHtmlElement;

@SuppressWarnings("serial")
public class HeaderTag extends LeveBaseTag {

	
	private String commandBar = "default";
	private String actionBar = "default";
	
	public static final String COMMAND_DEFAULT_CAD = "find.search,close.arrow-left,closeall.remove";
	public static final String ACTION_DEFAULT_CAD = "new.file,save.save,delete.trash";
	public static final String COMMAND_DEFAULT_FIND = "new.file,close.arrow-left,closeall.remove";
	public static final String ACTION_DEFAULT_FIND = "";
	
	@Override
	protected String writeBeforeBody() {

		StringBuilder out = new StringBuilder();

		printStartTag(out, HTML_TAG_HEAD);

		print(out,
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />");

		printPageTitle(out);

		printImportJquery(out);

		printImportBootstrap(out);
		
		printImportFontAwesome(out);

		printImportForm2js(out);

		printImportLeve(out);
		
		DecimalFormat formatter = (DecimalFormat) NumberFormat
				.getInstance(pageContext.getRequest().getLocale());
		DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

		print(out, "<script type=\"text/javascript\" >var servlet_path = \""
				+ pageContext.getServletContext().getContextPath() + "\";" +
				"var gotoLabel = '"+getMessage("goto")+"';" +
				// conffigura internacionalizacao
				"var locale_decimal = '"+ symbols.getMonetaryDecimalSeparator() + "';"+ 
				"var locale_thousands = '" + symbols.getGroupingSeparator() + "'; "+
				"$(function() {" +
				"	 $('.datepicker input').mask('"+getDateFormatMaskRequestLocale()+"');" +
				"});" + "</script>");

		return out.toString();
	}

	private void printImportJquery(StringBuilder out) {
		printImportScript(out, "jquery/jquery.min.js");
		printImportScript(out, "jquery/jquery.maskedinput.min.js");
		printImportScript(out, "jquery/jquery.maskMoney.js");

		printImportScript(out, "jquery/jquery.getUrlParam.js");
		printImportScript(out, "jquery/jquery.stringifyjson.js");
		
		if (getPage().isModule()) {
			printImportScript(out, "jquery/jquery.blockUI.js");
		}
	}

	private void printImportBootstrap(StringBuilder out) {
		printImportCss(out, "bootstrap/css/bootstrap.min.css");
		printImportScript(out, "bootstrap/js/bootstrap.min.js");

		printImportScript(out, "bootstrap/js/datagrid.js");
		printImportCss(out, "bootstrap/css/fuelux.grid.min.css");

		printImportCss(out, "bootstrap/css/datepicker.css");
		printImportScript(out, "bootstrap/js/bootstrap-datepicker.js");
		printImportScript(out, "bootstrap/js/locales/bootstrap-datepicker."
				+ getRequestLocaleString() + ".js");
	}
	
	private void printImportFontAwesome(StringBuilder out) {
		printImportCss(out, "font-awesome/css/font-awesome.min.css");
		
		out.append("<!--[if IE 7]>"); 
		printImportCss(out, "font-awesome/css/font-awesome-ie7.min.css");
		out.append("<![endif]-->");
	}

	private void printImportForm2js(StringBuilder out) {
		printImportScript(out, "form2js/form2js.js");
		printImportScript(out, "form2js/js2form.js");
		printImportScript(out, "form2js/jquery.toObject.js");
	}

	private void printImportLeve(StringBuilder out) {
		printImportScript(out, "leve/js/leve.js");
		printImportCss(out, "leve/css/leve.css");

		if (getPage().isModule()) {
			printImportScript(out, "leve/js/LeveDataSource.js");
			printImportScript(out, "leve/js/leve.module.js");
			printImportCss(out, "leve/css/leve.module.css");
		} else if (getPage().isMain()) {
			printImportScript(out, "leve/js/leve.main.js");
			printImportCss(out, "leve/css/leve.main.css");
		} else if (getPage().isLogin()) {
			printImportCss(out, "leve/css/login.css");
		}
	}

	private void printPageTitle(StringBuilder out) {
		printStartTag(out, HTML_TAG_TITLE);
		print(out, "LEVE");
		printEndTag(out, HTML_TAG_TITLE);
	}

	@Override
	protected String writeAfterBody() {
		StringBuilder out = new StringBuilder();
		printEndTag(out, HTML_TAG_HEAD);
		printStartTag(out, HTML_TAG_BODY);
		
		
		if(getPage().isModule()){
			printStartTag(out, HTML_TAG_DIV, "class", "container-fluid");
			printTitle(out);
			printButtonBar(out);
		}
		
		return out.toString();
	}
	
	private void printTitle(StringBuilder out) {
		if (getPage().getTitle() != null && !getPage().getTitle().trim().isEmpty()) {
			UlHtmlElement ul = new UlHtmlElement("breadcrumb");
			AbstractHtmlElement li = ul.addChild(new LiHtmlElement());
			AbstractHtmlElement h5 = li.addChild(new HXHtmlElement(5));
			h5.addChild(new PureTextHtmlElement(getMessage(getPage().getTitle())));
			
			ul.print(out);
			
//			printStartTag(out, HTML_TAG_UL,"class", "breadcrumb");
//			printStartTag(out, HTML_TAG_LI);
//			printStartTag(out, HTML_TAG_H5);
//			print(out, getMessage(getPage().getTitle()));
//			printEndTag(out, HTML_TAG_H5);
//			printEndTag(out, HTML_TAG_LI);
//			printEndTag(out, HTML_TAG_UL);
		}
	}
	

	private void printButtonBar(StringBuilder out) {
		if (!haveActionBar() && !haveCommandBar()) {
			return;
		}

		String commandButtons = getCommandBar();
		String actionButtons = getActionBar();
		
		if(isLookUpPage()){
			if (getPage().isCad()) {
				commandButtons = "close.arrow-left,closeall.remove";
				actionButtons = "new.file,save.chevron-down";
			} else if (getPage().isFind()) {
				commandButtons = "close.arrow-left,closeall.remove";
				actionButtons = "find.filter";
			}
		} else {
			if (getPage().isCad()) {
				commandButtons = commandButtons.replace("default",
						COMMAND_DEFAULT_CAD);
				actionButtons = actionButtons
						.replace("default", ACTION_DEFAULT_CAD);
			} else if (getPage().isFind()) {
				commandButtons = commandButtons.replace("default",
						COMMAND_DEFAULT_FIND);
				actionButtons = actionButtons
						.replace("default", ACTION_DEFAULT_FIND);
			}
		}

		printStartTag(out, HTML_TAG_DIV, "class", "navbar navbar-module");
		printStartTag(out, HTML_TAG_DIV, "class", "navbar-inner");

		if (haveActionBar()) {
			mountBar(out, actionButtons, "left", "leve-action-bar");
		}

		if (haveCommandBar()) {
			mountBar(out, commandButtons, "right", "leve-command-bar");
		}

		printEndTag(out, HTML_TAG_DIV); // "class","navbar"
		printEndTag(out, HTML_TAG_DIV); // "class","navbar-inner"

	}

	private void mountBar(StringBuilder out, String actionButtons,
			String position, String type) {
		printStartTag(out, HTML_TAG_DIV, "class", "btn-group pull-" + position+" "+type);
		if(actionButtons != null && !actionButtons.isEmpty()){
			for (String btn : actionButtons.split(",")) {
				String[] btInf = btn.split("\\.");
				printStartTag(out, HTML_TAG_BUTTON, "class", "btn"+getBtnColor(btInf), "id", btInf[0]);
				if (btInf.length > 1) {
					printStartTag(out, HTML_TAG_I, "class", "icon-" + btInf[1]);
					printEndTag(out, HTML_TAG_I);
					print(out, " ");
				}
				print(out, getMessage(btInf[0]));
				printEndTag(out, HTML_TAG_BUTTON);
			}
		}
		
		printEndTag(out, HTML_TAG_DIV);
	}

	protected String getBtnColor(String[] btInf) {
		String colorClass = "";
		if (btInf[0].equals("save")){
			colorClass = " btn-primary";
		} else if(btInf[0].equals("delete")){
			colorClass = " btn-warning";
		} else if(btInf[0].equals("new")){
			colorClass = " btn-info";
		}
		
		return colorClass;
	}

	private boolean haveCommandBar() {
		return getCommandBar() != null && !getCommandBar().trim().isEmpty();
	}

	private boolean haveActionBar() {
		return getActionBar() != null && !getActionBar().trim().isEmpty();
	}

	public String getCommandBar() {
		return commandBar;
	}

	public void setCommandBar(String commandBar) {
		this.commandBar = commandBar;
	}

	public String getActionBar() {
		return actionBar;
	}

	public void setActionBar(String actionBar) {
		this.actionBar = actionBar;
	}
}