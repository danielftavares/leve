package org.leve.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.leve.beans.Domain;
import org.leve.beans.DomainFindDto;

@Stateless
public class DomainBean extends BaseBusinessBean<Domain, DomainFindDto> {

	@EJB
	private DomainDAO domainDAO;

	@Override
	protected DomainDAO getDAO() {
		return domainDAO;
	}

}