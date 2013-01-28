package org.leve.vbg.ejb.common;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.leve.ejb.BaseBusinessBean;
import org.leve.ejb.dao.BaseDAO;
import org.leve.vbg.beans.common.Country;
import org.leve.vbg.beans.common.CountryFindDto;

@Stateless
public class CountryBean extends BaseBusinessBean<Country, CountryFindDto> {

	@EJB
	private CountryDAO countryDAO;

	@Override
	protected BaseDAO<Country, CountryFindDto> getDAO() {
		return countryDAO;
	}

}