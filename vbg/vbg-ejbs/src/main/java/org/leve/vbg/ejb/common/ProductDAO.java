package org.leve.vbg.ejb.common;

import javax.ejb.Stateless;

import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.criteria.Criteria;
import org.leve.criteria.Order;
import org.leve.criteria.restriction.Restrictions;
import org.leve.ejb.dao.BaseDAO;
import org.leve.vbg.beans.common.Product;
import org.leve.vbg.beans.common.ProductFindDto;

@Stateless
public class ProductDAO extends BaseDAO<Product, ProductFindDto> {

	@Override
	public PaginatedList<Product> listPaginated(BaseFindDto<ProductFindDto> findDto) {
		ProductFindDto findDtoBean = findDto.getBean();
		Criteria criteria = Criteria.forClass(getPersistentClass());
		criteria.createCriteria("brand");
		criteria.createCriteria("productType");
		
		if(findDtoBean.getKey() != null){
			criteria.add(Restrictions.eq("key", findDtoBean.getKey() ));
		}
		
		if(findDtoBean.getBrand() != null){
			criteria.add(Restrictions.eq("brand.idBrand", findDtoBean.getBrand().getIdBrand() ));
		}
		
		if(findDtoBean.getProductType() != null){
			criteria.add(Restrictions.eq("productType.idProductType", findDtoBean.getProductType().getIdProductType() ));
		}
		
		criteria.addOrder(Order.asc("key"));
		
		return mountPaginatedResponse (criteria, findDto);
	}

}
