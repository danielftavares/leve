package org.leve.web.ui.tags.html;

public class LabelHtmlElement extends AbstractHtmlElement {

	private String forAtt;
	private String label;

	public LabelHtmlElement(String styleClass, String style, String id, String forAtt, String label) {
		super(styleClass, style, id);
		this.forAtt = forAtt;
		this.label = label;
	}
	
	public LabelHtmlElement(String styleClass, String style) {
		super(styleClass, style, null);
	}
	
	public LabelHtmlElement(String styleClass) {
		super(styleClass, null, null);
	}
	
	public LabelHtmlElement() {
		super();
	}

	protected static final String HTML_TAG_LABEL = "label";
	
	@Override
	protected String getElementName() {
		return HTML_TAG_LABEL;
	}
	
	@Override
	public void print(StringBuilder sb) {
		sb.append('<').append(getElementName());
		printAttribute(sb, "id", getId());
		printAttribute(sb, "class", getStyleClass());
		printAttribute(sb, "style", getStyle());
		printAttribute(sb, "for", getForAtt());
		sb.append('>');
		sb.append(getLabel());
		for (AbstractHtmlElement child : children) {
			child.print(sb);
		}
		sb.append("</").append(getElementName()).append('>');
	}
	
	public String getForAtt() {
		return forAtt;
	}

	public void setForAtt(String forAtt) {
		this.forAtt = forAtt;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
