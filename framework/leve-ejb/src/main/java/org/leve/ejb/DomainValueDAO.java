package org.leve.ejb;

import java.util.Collection;

import javax.ejb.Stateless;

import org.leve.beans.BaseFindDto;
import org.leve.beans.DomainValue;
import org.leve.beans.DomainValueFindDto;
import org.leve.beans.PaginatedList;
import org.leve.criteria.Criteria;
import org.leve.criteria.Order;
import org.leve.criteria.restriction.Restrictions;
import org.leve.ejb.dao.BaseDAO;

@Stateless
public class DomainValueDAO extends BaseDAO<DomainValue, DomainValueFindDto> {

	
	@Override
	public PaginatedList<DomainValue> listPaginated(BaseFindDto<DomainValueFindDto> findDto) {
		DomainValueFindDto findDtoBean = findDto.getBean();
		Criteria criteria = Criteria.forClass(getPersistentClass());
		
		if(findDtoBean.getDescription() != null){
			criteria.add(Restrictions.like("description", findDtoBean.getDescription()));
		}
		
		if(findDtoBean.getDomain() != null){
			criteria.add(Restrictions.eq("domain.idDomain", findDtoBean.getDomain().getIdDomain()));
		}
		
		if(findDtoBean.getValue() != null){
			criteria.add(Restrictions.like("value", findDtoBean.getValue()));
		}
		
		criteria.addOrder(Order.asc("domain.name"));
		criteria.addOrder(Order.asc("index"));
		
		return mountPaginatedResponse (criteria, findDto);
	}

	public Collection<DomainValue> listByDomain(String domainName) {
		
		Criteria criteria = Criteria.forClass(getPersistentClass());
		criteria.add(Restrictions.eq("domain.name", domainName));
		
		return criteria.list(em);
	}
}
