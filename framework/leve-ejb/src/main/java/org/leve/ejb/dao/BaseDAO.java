package org.leve.ejb.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.leve.annotation.LeveDesc;
import org.leve.annotation.LeveKey;
import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.criteria.Criteria;
import org.leve.criteria.Order;
import org.leve.criteria.projection.Projections;
import org.leve.criteria.restriction.Restrictions;
import org.leve.exception.LeveException;
import org.leve.reflections.ReflectionUtil;
public abstract class BaseDAO<T, F> {

	Class<T> clazz;
	
	@SuppressWarnings(value = "unchecked")
	protected Class<T> getPersistentClass(){
		if(clazz == null){
		    Type[] types = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
		    if (types[0] instanceof ParameterizedType) {
		    	ParameterizedType type = (ParameterizedType) types[0];
		    	clazz = (Class<T>) type.getRawType();
		    } else {
		    	clazz = (Class<T>) types[0];
		    }
		}
		return clazz;
	}

	@PersistenceContext(name = "levePU")
	protected EntityManager em;

	@Inject
	Validator validator;

	public T create(T bean) {
		validate(bean);
		em.persist(bean);
		return bean;
	}

	public T find(Long pk) {
		return (T) this.em.find(getPersistentClass(), pk);
	}

	public T update(T bean) {
		validate(bean);
		return this.em.merge(bean);
	}

	public void delete(T bean) {
		this.em.remove(bean);
	}

	public PaginatedList<T> listPaginated(BaseFindDto<F> findDto){
		validate(findDto.getBean());
		Criteria criteria = getCriteriaByExample(findDto.getBean());
		
		return mountPaginatedResponse(criteria, findDto);
	}
	
	
	@SuppressWarnings("unchecked")
	protected PaginatedList<T> mountPaginatedResponse(Criteria criteria, BaseFindDto<?> findDto) {
		PaginatedList<T> paginatedList = new PaginatedList<T>();
		
		criteria.setFirstResult(findDto.getPageSize() * findDto.getPage());
		criteria.setMaxResults(findDto.getPageSize());
		
		paginatedList.setData(criteria.list(em));
		
		criteria.removeFirstResult();
		criteria.removeMaxResults(findDto.getPageSize());
		
		criteria.setProjection(Projections.rowCount());
		criteria.removeOrder();
		paginatedList.setRowCount( (Long)criteria.uniqueResult(em));
		
		return paginatedList;
	}
	
	@SuppressWarnings(value = "unchecked")
	public List<T> findByExample(T example) {
		Criteria criteria = getCriteriaByExample(example);

		return (List<T>) findByCriteria(criteria);
	}

	private Criteria getCriteriaByExample(Object example) {
		Criteria criteria = Criteria.forClass(getPersistentClass());

		//TODO encapsular reflection
		Field[] fields = example.getClass().getDeclaredFields();

		for (Field field : fields) {

			Object value = null;

			try {
				field.setAccessible(true);
				value = field.get(example);
			} catch (IllegalArgumentException e) {
				continue;
			} catch (IllegalAccessException e) {
				continue;
			}

			if (value == null) {
				continue;
			}

			criteria.add(Restrictions.eq(field.getName(), value));
		}
		return criteria;
	}

	  @SuppressWarnings(value = { "rawtypes" })
	  protected List findByCriteria(Criteria criteria) {
	    return criteria.list(em);
	  }
	
	// protected ListPagination<T> mountPagination(LeveQueryBuilder<T>
	// queryBuilder, FT vo) {
	// ListPagination<T> list = new ListPagination<T>();
	// list.setList(queryBuilder.list(vo) );
	// list.setTotalRowsCount(queryBuilder.count());
	// return list;
	// }

	protected void validate(Object bean) {
		Set<ConstraintViolation<Object>> violations = validator.validate(bean);

		LeveException exception = new LeveException();
		for (ConstraintViolation<Object> violation : violations) {
			violation.getPropertyPath().toString();
			violation.getMessage();
			violation.getMessageTemplate();
			exception.addError(violation);
		}

		exception.checkThrowErrorsList();

	}

	@SuppressWarnings("unchecked")
	public T findByKey(String key) {
		Criteria criteria = Criteria.forClass(getPersistentClass());
		
		String keyField = ReflectionUtil.getFieldWithAnnotation(getPersistentClass(), LeveKey.class).getName();
		criteria.add(Restrictions.eq(keyField, key));
		List<T> l = criteria.list(em);
		if(!l.isEmpty()){
			return l.get(0);
		} else {
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<T> autocomplete(String desc) {
		Criteria criteria = Criteria.forClass(getPersistentClass());
		
		String descField = ReflectionUtil.getFieldWithAnnotation(getPersistentClass(), LeveDesc.class).getName();
		criteria.add(Restrictions.like(descField, desc+"%"));
		criteria.setMaxResults(10);
		criteria.addOrder(Order.asc(descField));
		return criteria.list(em);
	}

	@SuppressWarnings("unchecked")
	public List<T> list() {
		Criteria criteria = Criteria.forClass(getPersistentClass());
		
		String orderAttr = ReflectionUtil.getFieldWithAnnotation(getPersistentClass(), LeveKey.class).getName();
		if(orderAttr == null){
			orderAttr = ReflectionUtil.getFieldWithAnnotation(getPersistentClass(), LeveDesc.class).getName();
		}
		if (orderAttr != null){
			criteria.addOrder(Order.asc(orderAttr));
		}
		return criteria.list(em);
	}

}
