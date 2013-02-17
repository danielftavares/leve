package org.leve.ejb;

import java.lang.reflect.Field;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.leve.annotation.LeveDesc;
import org.leve.annotation.LeveKey;
import org.leve.criteria.Criteria;
import org.leve.criteria.Order;
import org.leve.reflections.ReflectionUtil;

@Stateless
public class UiBean {

	@PersistenceContext(name = "levePU")
	protected EntityManager em;
	
	@SuppressWarnings("unchecked")
	public <T> List<T> lsitBean(Class<T> clazz){
		Criteria criteria = Criteria.forClass(clazz);

		String orderAttr = null;
		
		Field key = ReflectionUtil.getFieldWithAnnotation(clazz, LeveKey.class);
		if(key != null){
			orderAttr = key.getName();
		} else {
			Field desc = ReflectionUtil.getFieldWithAnnotation(clazz, LeveDesc.class);
			if (desc != null){
				orderAttr = desc.getName();
			}
		}
		
		if (orderAttr != null){
			criteria.addOrder(Order.asc(orderAttr));
		}
		
		return criteria.list(em);
	}
}
