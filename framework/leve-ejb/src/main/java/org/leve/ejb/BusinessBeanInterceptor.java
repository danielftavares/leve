package org.leve.ejb;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.PersistenceException;

public class BusinessBeanInterceptor {
	
	 @Resource
	 private EJBContext context;


	@AroundInvoke
	public Object interceptor(InvocationContext ic) throws Exception {
		Object o = null;
		try {
			o = ic.proceed();
		} catch (PersistenceException ex) {
			throw ex;
		}
		return o;
	}
}
