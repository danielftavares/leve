package org.leve.web.ui.tags;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

@SuppressWarnings("serial")
public class HeaderTag extends LeveBaseTag {

	@Override
	protected String writeBeforeBody() {

		StringBuilder out = new StringBuilder();

		printStartTag(out, HTML_TAG_HEAD);

		print(out,
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />");

		printPageTitle(out);

		printImportJquery(out);

		printImportBootstrap(out);

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
	}

	private void printImportBootstrap(StringBuilder out) {
		printImportCss(out, "bootstrap/css/bootstrap.min.css");
		printImportScript(out, "bootstrap/js/bootstrap.min.js");

		printImportScript(out, "bootstrap/js/datagrid.js");
		printImportCss(out, "bootstrap/css/fuelux.min.css");

		printImportCss(out, "bootstrap/css/datepicker.css");
		printImportScript(out, "bootstrap/js/bootstrap-datepicker.js");
		printImportScript(out, "bootstrap/js/locales/bootstrap-datepicker."
				+ getRequestLocaleString() + ".js");
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
		return out.toString();
	}

}