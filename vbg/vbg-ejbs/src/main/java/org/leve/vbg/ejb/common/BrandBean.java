package org.leve.vbg.ejb.common;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.leve.ejb.BaseBusinessBean;
import org.leve.ejb.dao.BaseDAO;
import org.leve.vbg.beans.common.Brand;
import org.leve.vbg.beans.common.BrandFindDto;

@Stateless
public class BrandBean extends BaseBusinessBean<Brand, BrandFindDto> {

	@EJB
	private BrandDAO brandDAO;

	@Override
	protected BaseDAO<Brand, BrandFindDto> getDAO() {
		return brandDAO;
	}

}