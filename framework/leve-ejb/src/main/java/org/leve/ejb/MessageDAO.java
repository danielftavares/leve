package org.leve.ejb;

import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.leve.beans.BaseFindDto;
import org.leve.beans.Message;
import org.leve.beans.MessageFindDto;
import org.leve.beans.PaginatedList;
import org.leve.criteria.Criteria;
import org.leve.criteria.Order;
import org.leve.criteria.restriction.Restrictions;
import org.leve.ejb.dao.BaseDAO;

@Stateless
public class MessageDAO extends BaseDAO<Message, MessageFindDto> {


	@SuppressWarnings("unchecked")
	public List<Message> list(Locale locale) {
		Query q = em.createQuery(
				"SELECT m FROM " + Message.class.getName()
						+ " m WHERE m.locale = :locale", Message.class)
				.setParameter("locale", locale);
		return q.getResultList();
	}
	
	@Override
	public PaginatedList<Message> listPaginated(BaseFindDto<MessageFindDto> findDto) {
		MessageFindDto findDtoBean = findDto.getBean();
		Criteria criteria = Criteria.forClass(getPersistentClass());
		
		if(findDtoBean.getKey() != null){
			criteria.add(Restrictions.like("key", findDtoBean.getKey()));
		}
		
		if(findDtoBean.getLocale() != null){
			criteria.add(Restrictions.like("locale", findDtoBean.getLocale()));
		}
		
		if(findDtoBean.getMessage() != null){
			criteria.add(Restrictions.like("message", findDtoBean.getMessage()));
		}
		
		criteria.addOrder(Order.asc("locale"));
		criteria.addOrder(Order.asc("key"));
		
		return mountPaginatedResponse (criteria, findDto);
	}

}
