package org.leve.web.ui.tags;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.leve.annotation.LeveLabel;
import org.leve.reflections.ReflectionUtil;
import org.leve.web.ui.tags.html.AbstractHtmlElement;
import org.leve.web.ui.tags.html.DivHtmlElement;
import org.leve.web.ui.tags.html.IHtmlElement;
import org.leve.web.ui.tags.html.InputHtmlElement;
import org.leve.web.ui.tags.html.LabelHtmlElement;
import org.leve.web.ui.tags.html.PHtmlElement;
import org.leve.web.ui.tags.html.PureTextHtmlElement;
import org.leve.web.ui.tags.html.ScriptHtmlElement;
import org.leve.web.ui.tags.html.SpanHtmlElement;

@SuppressWarnings("serial")
public class InputTag extends LeveBaseTag {

	private String attribute;
	private String label;
	private boolean uppercase = true;
	private boolean disabled = false;

	@Override
	protected String writeBeforeBody() {
		StringBuilder out = new StringBuilder();

		AbstractHtmlElement root = new DivHtmlElement("control-group");

		root.addChild(getLabelElement());

		root.addChild(getInputElement());

		if (getAttributeType().equals(BigDecimal.class)) {
			root.addChild(getScriptBigDecimal());
		}

		root.print(out);

		return out.toString();
	}

	private AbstractHtmlElement getScriptBigDecimal() {
		Column column = getFieldAnnotation(Column.class);

		String script = "$(function(){$('#" + getAttribute()
				+ "').maskMoney({precision: " + column.scale() + ", decimal: locale_decimal "
				+ ", thousands: locale_thousands});});";

		return new ScriptHtmlElement(script);
	}

	@Override
	protected String writeAfterBody() {
		return NULL_RETURN;
	}

	private AbstractHtmlElement getLabelElement() {
		return new LabelHtmlElement("control-label", null, null, attribute,
				getMessage(resolveLabel()));
	}

	protected String resolveLabel() {
		if (getLabel() != null && !getLabel().isEmpty()) {
			return getLabel();
		} else {
			LeveLabel leveLabel = getFieldAnnotation(LeveLabel.class);
			if (leveLabel != null) {
				return leveLabel.value();
			} else {
				return getAttribute();
			}
		}
	}
	
	protected <T extends Annotation> T  getFieldAnnotation(Class<T> annotation){
		FormTag p = getFormTag();
		return  ReflectionUtil.getAnnotationField(p.getDto(),
				getAttribute(), annotation);
	}

	protected FormTag getFormTag() {
		FormTag p = (FormTag) getParent();
		return p;
	}

	protected AbstractHtmlElement getInputElement() {
		DivHtmlElement root = new DivHtmlElement("controls");

		String styleClass = "";
		Class<?> attributeType = getAttributeType();
		if (attributeType.equals(Calendar.class)) {
			root.setStyleClass("controls input-append date datepicker");
			root.addCustomAttribute("data-date-language",
					getRequestLocaleString());
			root.addCustomAttribute("data-date-format",
					getDateFormatRequestLocale());
		} else {
			if (attributeType.equals(BigDecimal.class)) {
				styleClass += " leve-decimal";
			}
			if (isUppercase()) {
				styleClass += " leve-uppercase";
			}
		}

		InputHtmlElement input = new InputHtmlElement(styleClass, null,
				getAttribute(), getFieldType());
		input.addCustomAttribute("required", getRequired());
		input.addCustomAttribute("maxlength", getSize());
		input.addCustomAttribute("readonly", getReadOnly());
		
		root.addChild(input);

		if (attributeType.equals(Calendar.class)) {
			SpanHtmlElement span = new SpanHtmlElement("add-on");
			span.addChild(new IHtmlElement("icon-th"));
			root.addChild(span);
		}

		root.addChild(getRequeiredElement());

		return root;
	}

	protected AbstractHtmlElement getRequeiredElement() {
		PHtmlElement root = null;
		if (isRequired()) {
			root = new PHtmlElement("help-inline text-warning");
			root.addChild(new PureTextHtmlElement("*"));
		}
		return root;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	private String getFieldType() {
		Class<?> attributeType = getAttributeType();

		if (getAttribute().toLowerCase().contains("password")) {
			return "password";
		} else if (attributeType.equals(Long.class)
				|| attributeType.equals(Integer.class)) {
			return "number";
		} else if (attributeType.equals(Boolean.class)) {
			return "checkbox";
		}
		// else if (attributeType.equals(Calendar.class)) {
		// return "date";
		// }
		return "text";
	}

	private Class<?> getAttributeType() {
		return getFieldAttribute().getType();
	}

	private Field getFieldAttribute() {
		FormTag p = getFormTag();
		return ReflectionUtil.getField(p.getDto(), getAttribute());
	}

	private String getSize() {
		Size size = getFieldAttribute().getAnnotation(Size.class);
		if (size != null) {
			return size.max() + "";
		} else {
			if (getAttributeType().equals(Calendar.class)) {
				return String.valueOf(getDateFormatRequestLocale().length());
			} else if (getAttributeType().equals(BigDecimal.class)) {
				Column column = getFieldAttribute().getAnnotation(Column.class);
				if (column != null) {
					// FIXME esse calcula esta errado
					return String.valueOf(column.precision()
							+ roundUp(column.precision(), 3));
				}
			}
		}
		return null;
	}

	public static int roundUp(int dividend, int divisor) {
		return (dividend + divisor - 1) / divisor;
	}

	private String getReadOnly() {
		return isDisabled() ? "readonly" : "";
	}

	private String getRequired() {
		return isRequired() ? "required" : "";
	}

	public boolean isRequired() {
		NotNull noNull = getFieldAttribute().getAnnotation(NotNull.class);
		return noNull != null;
	}

	public boolean isUppercase() {
		return uppercase;
	}

	public void setUppercase(boolean uppercase) {
		this.uppercase = uppercase;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
}
