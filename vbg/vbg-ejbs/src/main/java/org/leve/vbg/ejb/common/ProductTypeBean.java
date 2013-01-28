package org.leve.vbg.ejb.common;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.leve.ejb.BaseBusinessBean;
import org.leve.ejb.dao.BaseDAO;
import org.leve.vbg.beans.common.ProductType;
import org.leve.vbg.beans.common.ProductTypeFindDto;

@Stateless
public class ProductTypeBean extends BaseBusinessBean<ProductType, ProductTypeFindDto> {

	@EJB
	private ProductTypeDAO productTypeDAO;

	@Override
	protected BaseDAO<ProductType, ProductTypeFindDto> getDAO() {
		return productTypeDAO;
	}

}