package org.leve.vbg.ejb.common;

import javax.ejb.Stateless;

import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.criteria.Criteria;
import org.leve.criteria.Order;
import org.leve.criteria.restriction.Restrictions;
import org.leve.ejb.dao.BaseDAO;
import org.leve.vbg.beans.common.Brand;
import org.leve.vbg.beans.common.BrandFindDto;

@Stateless
public class BrandDAO extends BaseDAO<Brand, BrandFindDto> {

	@Override
	public PaginatedList<Brand> listPaginated(BaseFindDto<BrandFindDto> findDto) {
		BrandFindDto findDtoBean = findDto.getBean();
		Criteria criteria = Criteria.forClass(getPersistentClass());
		if(findDtoBean.getName() != null){
			criteria.add(Restrictions.like("name", findDtoBean.getName()));
		}
		
		criteria.addOrder(Order.asc("name"));
		
		return mountPaginatedResponse (criteria, findDto);
	}

}
