
package org.leve.web.ui.controllers;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.leve.beans.BaseFindDto;
import org.leve.beans.Domain;
import org.leve.beans.DomainFindDto;
import org.leve.beans.PaginatedList;
import org.leve.ejb.DomainBean;

@Path("/domain")
public class DomainController extends BaseController<Domain, DomainFindDto> {

	@EJB
	private DomainBean domainBean;

	@Override
	protected DomainBean getBussinesBean() {
		return domainBean;
	}
	
	@Path("/list")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public PaginatedList<Domain> list(BaseFindDto<DomainFindDto> findDto) {
		return super.list(findDto);
	}
	
	

}
