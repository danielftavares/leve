package org.leve.vbg.ejb.common;

import javax.ejb.Stateless;

import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.criteria.Criteria;
import org.leve.criteria.Order;
import org.leve.criteria.restriction.Restrictions;
import org.leve.ejb.dao.BaseDAO;
import org.leve.vbg.beans.common.City;
import org.leve.vbg.beans.common.CityFindDto;

@Stateless
public class CityDAO extends BaseDAO<City, CityFindDto> {

	@Override
	public PaginatedList<City> listPaginated(BaseFindDto<CityFindDto> findDto) {
		CityFindDto findDtoBean = findDto.getBean();
		Criteria criteria = Criteria.forClass(getPersistentClass());
		criteria.createCriteria("state");
		criteria.createCriteria("state.country");
		
		if(findDtoBean.getName() != null){
			criteria.add(Restrictions.like("name", findDtoBean.getName()));
		}
		
		if(findDtoBean.getState() != null){
			criteria.add(Restrictions.eq("state.stateid", findDtoBean.getState().getStateid() ));
		}
		
		criteria.addOrder(Order.asc("state.country.name"));
		criteria.addOrder(Order.asc("state.name"));
		criteria.addOrder(Order.asc("name"));
		
		return mountPaginatedResponse (criteria, findDto);
	}

}
