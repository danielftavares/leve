package org.leve.vbg.ejb.common;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.leve.ejb.BaseBusinessBean;
import org.leve.ejb.dao.BaseDAO;
import org.leve.vbg.beans.common.Product;
import org.leve.vbg.beans.common.ProductFindDto;

@Stateless
public class ProductBean extends BaseBusinessBean<Product, ProductFindDto> {

	@EJB
	private ProductDAO productDAO;

	@Override
	protected BaseDAO<Product, ProductFindDto> getDAO() {
		return productDAO;
	}

}