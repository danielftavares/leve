package org.leve.ejb;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.interceptor.Interceptors;
import javax.persistence.Id;

import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.ejb.dao.BaseDAO;
import org.leve.reflections.ReflectionUtil;

@Interceptors ({BusinessBeanInterceptor.class})
public abstract class BaseBusinessBean<T, F>  {
	
	@Resource
    private SessionContext context;
	
	@EJB
	private NumberingBean numberingBean;
	
	protected abstract BaseDAO<T, F> getDAO();

	public T create(T bean) {
		bean = numberingBean.generateKey(bean);
		return getDAO().create(bean);
	}

	public T find(Long pk) {
//		System.out.println(context.getCallerPrincipal().getName());
		return getDAO().find(pk);
	}

	public T update(T bean) {
		return getDAO().update(bean);
	}

	public void delete(T bean) {
		getDAO().delete(bean);
	}

	public T save(T bean) {
		if(ReflectionUtil.getFieldValueWithAnnotation(bean, Id.class) == null){
			return create(bean);
		} else {
			return update(bean);
		}
	}
	
	
	public PaginatedList<T> listPaginated(BaseFindDto<F> findDto){
		return getDAO().listPaginated(findDto);
	}
//	public ListPagination<T> list(FT bean) {
//		return getDAO().list(bean);
//	}

	public void deleteById(Long pk) {
		T bean = find(pk);
		delete(bean);
	}

	public T findByKey(String key) {
		if(key == null || key.isEmpty()){
			return null;
		}
		return getDAO().findByKey(key);
	}

	public List<T> autocomplete(String desc) {
		if(desc == null || desc.isEmpty()){
			return null;
		}
		return getDAO().autocomplete(desc);
	}
}
