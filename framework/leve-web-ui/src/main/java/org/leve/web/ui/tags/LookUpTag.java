package org.leve.web.ui.tags;

import javax.validation.constraints.Size;

import org.leve.reflections.ReflectionUtil;
import org.leve.web.ui.tags.html.AbstractHtmlElement;
import org.leve.web.ui.tags.html.ButtonHtmlElement;
import org.leve.web.ui.tags.html.DivHtmlElement;
import org.leve.web.ui.tags.html.IHtmlElement;
import org.leve.web.ui.tags.html.InputHtmlElement;
import org.leve.web.ui.tags.html.LabelHtmlElement;
import org.leve.web.ui.tags.html.ScriptHtmlElement;

/**
 * 
 * @author daniel
 *
 */
@SuppressWarnings("serial")
public class LookUpTag extends InputTag {

	@Override
	protected String writeBeforeBody() {
		StringBuilder out = new StringBuilder();

		String fieldKey = getKeyManyToOned();
		String fieldDesc = getDescManyToOne();
		String fieldId = getIdFieldManyToOne();
		
		String firstField = fieldKey;
		if(firstField == null){
			firstField = fieldDesc;
		}
		
		DivHtmlElement root = new DivHtmlElement(getFormCellClass() + " leve-lookup", null, getId());
		root.addCustomAttribute("data-cad-url", getBaseCadUrl());
		root.addCustomAttribute("data-find-url", getBaseFindUrl());
		root.addCustomAttribute("data-find-key-url", getFindByKeyUrl());
		root.addCustomAttribute("data-autocomplete-url", getAutocompleteUrl());
		root.addCustomAttribute("data-function-fill", "fill_lookup_"+getAttribute());
		root.addCustomAttribute("data-field-desc", fieldDesc);
		if (fieldKey != null){
			root.addCustomAttribute("data-field-key", fieldKey);
		}
		
		root.addChild(new InputHtmlElement("leve-lookup-id", null, getAttribute()+"."+fieldId, "hidden"));
		
		root.addChild(new LabelHtmlElement("control-label", null, null,  getAttribute()+"."+firstField, getMessage(resolveLabel())));
		AbstractHtmlElement divInputs = root.addChild(new DivHtmlElement("controls input-append"));
		
		if(fieldKey != null && !fieldKey.equals(fieldDesc)){
			divInputs.addChild(new InputHtmlElement("leve-uppercase leve-lookup-key", null, getAttribute()+"."+fieldKey).addCustomAttribute("maxlength", getKeySize()));
		}
		
		if(fieldDesc != null){
			divInputs.addChild(new InputHtmlElement("leve-uppercase leve-lookup-desc " + (fieldKey == null || fieldKey.equals(fieldDesc) ? " leve-field-alone":"" ), null, getAttribute()+"."+fieldDesc).addCustomAttribute("maxlength", getDescSize()));
		}
		divInputs.addChild(new ButtonHtmlElement("btn leve-lookup-find-btn")).addChild(new IHtmlElement("icon-search"));
		divInputs.addChild(new ButtonHtmlElement("btn leve-lookup-cad-btn")).addChild(new IHtmlElement("icon-file"));
		divInputs.addChild(getRequeiredElement());
		
		String script = "function fill_lookup_"+getAttribute()+"(data){" +
						"if(data){" +
							(fieldKey != null? "$('div#"+getId()+" .leve-lookup-key').val(data."+fieldKey+");" : "")+
							(fieldDesc != null? "$('div#"+getId()+" .leve-lookup-desc').val(data."+fieldDesc+");" : "")+
							"$('div#"+getId()+" .leve-lookup-id').val(data."+fieldId+");" +
						"} else {" +
							"$('div#"+getId()+" .leve-lookup-key').val('');" +
							"$('div#"+getId()+" .leve-lookup-desc').val('');" +
							"$('div#"+getId()+" .leve-lookup-id').val('');" +
						"}"+
					"};";
		
		root.addChild(new ScriptHtmlElement(script));
		
		root.print(out);
		

		return out.toString();
	}


	private String getFindByKeyUrl() {
		return getFormTag().getJaxPath()+"/"+getKeyFieldAction()+"/key/";
	}
	
	private String getAutocompleteUrl() {
		return getFormTag().getJaxPath()+"/"+getKeyFieldAction()+"/autocomplete";
	}


	protected String getBaseFindUrl() {
		return getBaseLookupUrl().replace("{action}", "Find");
	}
	
	protected String getBaseCadUrl() {
		return getBaseLookupUrl().replace("{action}", "Cad");
	}
	
	protected String getBaseLookupUrl() {
		return getAttrPackge() +"/"+ getKeyFieldAction()+"/"+ getKeyFieldAction()+"{action}.jsp";
	}

	
	private String getAttrPackge() {
		String pack = getFieldAttribute().getType().getPackage().getName();
		return pack.substring(pack.lastIndexOf('.') + 1);
	}


	private String getKeySize() {
		return getLookUpSize(getKeyManyToOned());
	}
	
	private String getDescSize() {
		return getLookUpSize(getDescManyToOne());
	}

	private String getLookUpSize(String field) {
		Size s = ReflectionUtil.getAnnotationField(getFieldAttribute(), Size.class);
		if(s!=null){
			return String.valueOf(s.max());
		}
		return null;
	}

	@Override
	protected String writeAfterBody() {
		return NULL_RETURN;
	}
}
