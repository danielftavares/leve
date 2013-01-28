package org.leve.ejb;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.leve.beans.DomainValue;
import org.leve.beans.DomainValueFindDto;

@Stateless
public class DomainValueBean extends BaseBusinessBean<DomainValue, DomainValueFindDto> {

	@EJB
	private DomainValueDAO domainValueDAO;

	@Override
	protected DomainValueDAO getDAO() {
		return domainValueDAO;
	}
	
	public Collection<DomainValue> listByDomain(String domainName){
		return getDAO().listByDomain(domainName);
	}

}