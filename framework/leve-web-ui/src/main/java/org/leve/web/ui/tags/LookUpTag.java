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
 * 		<div class="control-group leve-lookup">
			<label class="control-label" for="name">Nome</label>
			<div class="controls input-append">
				<input class="leve-lookup-key" type="text">
				<input type="text" class="leve-uppercase leve-lookup-desc">
				<button type="submit" class="btn"><i class="icon-search"></i></button>
				<button type="submit" class="btn"><i class="icon-file"></i></button>
			</div>
		</div>

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
		String filedAction = getKeyFieldAction();
		
		String firstField = fieldKey;
		if(firstField == null){
			firstField = fieldDesc;
		}
		
		DivHtmlElement root = new DivHtmlElement("control-group leve-lookup", null, getId());
		root.addCustomAttribute("data-cad-url", getBaseCadUrl());
		root.addCustomAttribute("data-find-url", getBaseFindUrl());
		root.addCustomAttribute("data-function-fill", "fill_lookup_"+getAttribute());
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
					"};" +
				"$(function() {" +
					
					
					//evento para o key
					"$('div#"+getId()+" .leve-lookup-key').blur(function() {" +
							"Leve.completeByKey(this, '"+getFormTag().getJaxPath()+"/"+filedAction+"/key/', fill_lookup_"+getAttribute()+" );" +
						"});" +

					"$('div#"+getId()+" .leve-lookup-desc').typeahead({"+
					    "items: 10," +
					    "highlighter : function(item){" +
					      "var itemVal = $.parseJSON(item);"+	
					      "var query = this.query.replace(/[\\-\\[\\]{}()*+?.,\\\\\\^$|#\\s]/g, '\\$&');"+
					      "return ("+getItemLabel(fieldKey, fieldDesc)+").replace(new RegExp('(' + query + ')', 'ig'), function ($1, match) {"+
					      "  return '<strong>' + match + '</strong>';"+
					      "})"+
					    "}," +
					    "matcher: function (item) {return 1;}," +
					    "sorter: function (items) {return items}," +
					    "updater: function (item) { var data = $.parseJSON(item); fill_lookup_"+getAttribute()+"(data); return data."+fieldDesc+" } , " +
					    "source: function (query, callback) {"+
					    "    return $.getJSON('"+getFormTag().getJaxPath()+"/"+filedAction+"/autocomplete?desc='+query, function (data) {" +
					    		"var return_list = [];" +
					    		"$.each(data, function(key, val) {" +
					    			"return_list.push( $.stringifyJSON(val) );"+
					    		"});"+
					    "        return callback(return_list);"+
					    "    });"+
					    "}" +
					"});" +
					
				"});";
		
		root.addChild(new ScriptHtmlElement(script));
		
		root.print(out);
		

		return out.toString();
	}


	private String getItemLabel(String fieldKey, String fieldDesc) {
		if (fieldKey != null){
			return "itemVal."+fieldKey+"+ '-' +itemVal."+fieldDesc;
		} else {
			return "itemVal."+fieldDesc;
		}
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
