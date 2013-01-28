
package org.leve.web.ui.controllers;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.leve.beans.BaseFindDto;
import org.leve.beans.DomainValue;
import org.leve.beans.DomainValueFindDto;
import org.leve.beans.PaginatedList;
import org.leve.ejb.DomainValueBean;

@Path("/domainValue")
public class DomainValueController extends BaseController<DomainValue, DomainValueFindDto> {

	@EJB
	private DomainValueBean domainValueBean;

	@Override
	protected DomainValueBean getBussinesBean() {
		return domainValueBean;
	}
	
	@Path("/list")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public PaginatedList<DomainValue> list(BaseFindDto<DomainValueFindDto> findDto) {
		return super.list(findDto);
	}
	
	

}
