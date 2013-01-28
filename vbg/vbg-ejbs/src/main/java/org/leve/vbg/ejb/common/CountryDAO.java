package org.leve.vbg.ejb.common;

import javax.ejb.Stateless;

import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.criteria.Criteria;
import org.leve.criteria.Order;
import org.leve.criteria.restriction.Restrictions;
import org.leve.ejb.dao.BaseDAO;
import org.leve.vbg.beans.common.Country;
import org.leve.vbg.beans.common.CountryFindDto;

@Stateless
public class CountryDAO extends BaseDAO<Country, CountryFindDto> {

	@Override
	protected Class<Country> getPersistentClass() {
		return Country.class;
	}

	@Override
	public PaginatedList<Country> listPaginated(BaseFindDto<CountryFindDto> findDto) {
		CountryFindDto countryFind = findDto.getBean();
		Criteria criteria = Criteria.forClass(getPersistentClass());
		if(countryFind.getAbbreviation() != null){
			criteria.add(Restrictions.eq("abbreviation", countryFind.getAbbreviation()));
		}
		
		if(countryFind.getName() != null){
			criteria.add(Restrictions.like("name", countryFind.getName()));
		}
		
		criteria.addOrder(Order.asc("abbreviation"));
		
		return mountPaginatedResponse (criteria, findDto);
	}

}
