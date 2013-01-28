package org.leve.ejb;

import javax.ejb.Stateless;

import org.leve.beans.BaseFindDto;
import org.leve.beans.Domain;
import org.leve.beans.DomainFindDto;
import org.leve.beans.PaginatedList;
import org.leve.criteria.Criteria;
import org.leve.criteria.Order;
import org.leve.criteria.restriction.Restrictions;
import org.leve.ejb.dao.BaseDAO;

@Stateless
public class DomainDAO extends BaseDAO<Domain, DomainFindDto> {

	
	@Override
	public PaginatedList<Domain> listPaginated(BaseFindDto<DomainFindDto> findDto) {
		DomainFindDto findDtoBean = findDto.getBean();
		Criteria criteria = Criteria.forClass(getPersistentClass());
		
		if(findDtoBean.getName() != null){
			criteria.add(Restrictions.like("name", findDtoBean.getName()));
		}
		
		criteria.addOrder(Order.asc("name"));
		
		return mountPaginatedResponse (criteria, findDto);
	}
}
