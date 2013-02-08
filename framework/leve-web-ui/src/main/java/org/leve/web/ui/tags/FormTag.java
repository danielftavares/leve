package org.leve.web.ui.tags;

import java.lang.reflect.Field;
import java.util.UUID;

import javax.persistence.Id;

import org.leve.reflections.ReflectionUtil;

@SuppressWarnings("serial")
public class FormTag extends LeveBaseTag {

	public static final String COMMAND_DEFAULT_CAD = "find.search,close.arrow-left,closeall.remove";
	public static final String ACTION_DEFAULT_CAD = "new.file,save.chevron-down,delete.trash";
	public static final String COMMAND_DEFAULT_FIND = "new.file,close.arrow-left,closeall.remove";
	public static final String ACTION_DEFAULT_FIND = "find.filter";
	private String action = "";
	private String dto = "";
	private String callBack = "LeveModule.save_callback";
	private String id = "";
	private String commandBar = "default";
	private String actionBar = "default";
	private String successMsg = "save.success";
	private String successDeleteMsg = "delete.success";

	@Override
	protected String writeBeforeBody() {
		StringBuilder out = new StringBuilder();

		printStartTag(out, HTML_TAG_DIV, "class", "container-fluid");

		printTitle(out);

		printButtonBar(out);

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


	private void printTitle(StringBuilder out) {
		if (getPage().getTitle() != null && !getPage().getTitle().trim().isEmpty()) {
			printStartTag(out, HTML_TAG_UL,"class", "breadcrumb");
			printStartTag(out, HTML_TAG_LI);
			printStartTag(out, HTML_TAG_H5);
			print(out, getMessage(getPage().getTitle()));
			printEndTag(out, HTML_TAG_H5);
			printEndTag(out, HTML_TAG_LI);
			printEndTag(out, HTML_TAG_UL);
		}
	}

	@Override
	protected String writeAfterBody() {
		StringBuilder out = new StringBuilder();

		printEndTag(out, HTML_TAG_DIV); // span12

		printEndTag(out, HTML_TAG_DIV); // row-fluid

		printEndTag(out, HTML_TAG_FORM);

		printEndTag(out, HTML_TAG_DIV); // "class","container-fluid"

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
		for (String btn : actionButtons.split(",")) {
			String[] btInf = btn.split("\\.");
			printStartTag(out, HTML_TAG_BUTTON, "class", "btn", "id", btInf[0]);
			if (btInf.length > 1) {
				printStartTag(out, HTML_TAG_I, "class", "icon-" + btInf[1]);
				printEndTag(out, HTML_TAG_I);
				print(out, " ");
			}
			print(out, getMessage(btInf[0]));
			printEndTag(out, HTML_TAG_BUTTON);
		}
		printEndTag(out, HTML_TAG_DIV);
	}

	private boolean haveCommandBar() {
		return getCommandBar() != null && !getCommandBar().trim().isEmpty();
	}

	private boolean haveActionBar() {
		return getActionBar() != null && !getActionBar().trim().isEmpty();
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDto() {
		return dto;
	}

	public void setDto(String dto) {
		this.dto = dto;
	}

	public String getCallBack() {
		return callBack;
	}

	public void setCallBack(String callBack) {
		this.callBack = callBack;
	}

	protected String getJaxPath() {
		return pageContext.getServletContext().getContextPath() + "/leve";
	}

	public String getId() {
		if (id == null || id.isEmpty()) {
			id = "leve_" + UUID.randomUUID().toString();
		}
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
