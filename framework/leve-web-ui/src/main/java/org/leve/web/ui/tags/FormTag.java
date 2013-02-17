package org.leve.web.ui.tags;

import java.lang.reflect.Field;

import javax.persistence.Id;

import org.leve.reflections.ReflectionUtil;

@SuppressWarnings("serial")
public class FormTag extends FormContainerAbstractTag {


	private String action = "";
	private String callBack = "LeveModule.save_callback";

	private String successMsg = "save.success";
	private String successDeleteMsg = "delete.success";

	@Override
	protected String writeBeforeBody() {
		StringBuilder out = new StringBuilder();

		printStartTag(out, HTML_TAG_FORM, "id", getId(), "method", "POST",
				"action", getFormAction(), "autocomplete", "off",
				"class", "form-horizontal module_"+getPage().getPageType()+"_container leve_module_form",
				"data-id-property", getIdField() != null?  getIdField().getName() : "",
				"data-success-save-msg", getMessage(getSuccessMsg()),
				"data-success-delete-msg", getMessage(getSuccessDeleteMsg()));

		printIdField(out);

		printStartTag(out, HTML_TAG_DIV, "class", "row-fluid");

		printStartTag(out, HTML_TAG_DIV, "class", "span12 leve-form-start");

		return out.toString();
	}

	private String getFormAction() {
		return getJaxPath() + getAction();
	}

	private void printIdField(StringBuilder out) {
		if(getDto() != null && !getDto().isEmpty()){
			Field f = getIdField();
			if (f != null) {
				printStartTag(out, HTML_TAG_INPUT, "type", "hidden", "name",
						f.getName(), "id", f.getName(), "class", "form_id");
			}
		}
	}

	private Field getIdField() {
		Field f = ReflectionUtil.getFieldWithAnnotation(getDto(), Id.class);
		return f;
	}


	@Override
	protected String writeAfterBody() {
		StringBuilder out = new StringBuilder();

		printEndTag(out, HTML_TAG_DIV); // span12

		printEndTag(out, HTML_TAG_DIV); // row-fluid

		printEndTag(out, HTML_TAG_FORM);

		printSubmit(out);
		return out.toString();
	}

	private void printSubmit(StringBuilder out) {

		if(!getPage().isFind()){
			out.append("<script type=\"text/javascript\">" + "$(function() { "
					+ "$('form#" + getId() + "').submit(function(e) { "
					+ "	leve_base_submit(this, " + getCallBack() + "); "
					+ "	return false; " + "}); " + "});</script>");
		}

	}


	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCallBack() {
		return callBack;
	}

	public void setCallBack(String callBack) {
		this.callBack = callBack;
	}

	public String getSuccessMsg() {
		return successMsg;
	}

	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	public String getSuccessDeleteMsg() {
		return successDeleteMsg;
	}

	public void setSuccessDeleteMsg(String successDeleteMsg) {
		this.successDeleteMsg = successDeleteMsg;
	}

}
