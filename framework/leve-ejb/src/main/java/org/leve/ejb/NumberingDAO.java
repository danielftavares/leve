package org.leve.ejb;

import javax.ejb.Stateless;

import org.leve.beans.BaseFindDto;
import org.leve.beans.Numbering;
import org.leve.beans.NumberingFindDto;
import org.leve.beans.PaginatedList;
import org.leve.criteria.Criteria;
import org.leve.criteria.Order;
import org.leve.criteria.restriction.Restrictions;
import org.leve.ejb.dao.BaseDAO;

@Stateless
public class NumberingDAO extends BaseDAO<Numbering, NumberingFindDto> {


	@Override
	public PaginatedList<Numbering> listPaginated(BaseFindDto<NumberingFindDto> findDto) {
		NumberingFindDto findDtoBean = findDto.getBean();
		Criteria criteria = Criteria.forClass(getPersistentClass());
		if(findDtoBean.getClazz() != null){
			criteria.add(Restrictions.like("clazz", findDtoBean.getClazz()));
		}
		
		criteria.addOrder(Order.asc("clazz"));
		
		return mountPaginatedResponse (criteria, findDto);
	}
}
