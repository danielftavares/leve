package org.leve.bundles;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.inject.Singleton;

import org.leve.ejb.MessageBean;

@Singleton
public class ResourceBundleFactory {

	@EJB
	MessageBean messageBean;
	
	private Map<Locale, ResourceBundle> resourceBundles = new HashMap<Locale, ResourceBundle>();
	
	public ResourceBundle getResourceBundle(Locale locale){
		if(!resourceBundles.containsKey(locale)){
			ResourceBundle resourceBundle = new LeveResourceBundle(messageBean.list(locale));
			resourceBundles.put(locale, resourceBundle);
		}
		return resourceBundles.get(locale);
	}
}
