package org.leve.vbg.ejb.common;

import javax.ejb.Stateless;

import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.criteria.Criteria;
import org.leve.criteria.Order;
import org.leve.criteria.restriction.Restrictions;
import org.leve.ejb.dao.BaseDAO;
import org.leve.vbg.beans.common.State;
import org.leve.vbg.beans.common.StateFindDto;

@Stateless
public class StateDAO extends BaseDAO<State, StateFindDto> {

	@Override
	public PaginatedList<State> listPaginated(BaseFindDto<StateFindDto> findDto) {
		StateFindDto findDtoBean = findDto.getBean();
		Criteria criteria = Criteria.forClass(getPersistentClass());
		if(findDtoBean.getAbbreviation() != null){
			criteria.add(Restrictions.eq("abbreviation", findDtoBean.getAbbreviation()));
		}
		
		if(findDtoBean.getName() != null){
			criteria.add(Restrictions.like("name", findDtoBean.getName()));
		}
		
		if(findDtoBean.getCountry() != null){
			criteria.add(Restrictions.eq("country.countryid", findDtoBean.getCountry().getCountryid() ));
		}
		
		criteria.addOrder(Order.asc("country.name"));
		criteria.addOrder(Order.asc("abbreviation"));
		
		return mountPaginatedResponse (criteria, findDto);
	}

}
