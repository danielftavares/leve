package org.leve.vbg.ejb.common;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.leve.ejb.BaseBusinessBean;
import org.leve.ejb.dao.BaseDAO;
import org.leve.vbg.beans.common.City;
import org.leve.vbg.beans.common.CityFindDto;

@Stateless
public class CityBean extends BaseBusinessBean<City, CityFindDto> {

	@EJB
	private CityDAO cityDAO;

	@Override
	protected BaseDAO<City, CityFindDto> getDAO() {
		return cityDAO;
	}

}