package org.leve.vbg.ejb.common;

import javax.ejb.Stateless;

import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.criteria.Criteria;
import org.leve.criteria.Order;
import org.leve.criteria.restriction.Restrictions;
import org.leve.ejb.dao.BaseDAO;
import org.leve.vbg.beans.common.ProductType;
import org.leve.vbg.beans.common.ProductTypeFindDto;

@Stateless
public class ProductTypeDAO extends BaseDAO<ProductType, ProductTypeFindDto> {

	@Override
	public PaginatedList<ProductType> listPaginated(BaseFindDto<ProductTypeFindDto> findDto) {
		ProductTypeFindDto findDtoBean = findDto.getBean();
		Criteria criteria = Criteria.forClass(getPersistentClass());
		if(findDtoBean.getName() != null){
			criteria.add(Restrictions.like("name", findDtoBean.getName()));
		}
		
		criteria.addOrder(Order.asc("name"));
		
		return mountPaginatedResponse (criteria, findDto);
	}

}
