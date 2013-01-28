
package org.leve.web.ui.controllers;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.leve.beans.BaseFindDto;
import org.leve.beans.Numbering;
import org.leve.beans.NumberingFindDto;
import org.leve.beans.PaginatedList;
import org.leve.ejb.NumberingBean;

@Path("/numbering")
public class NumberingController extends BaseController<Numbering, NumberingFindDto> {

	@EJB
	private NumberingBean numberingBean;

	@Override
	protected NumberingBean getBussinesBean() {
		return numberingBean;
	}
	
	@Path("/list")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public PaginatedList<Numbering> list(BaseFindDto<NumberingFindDto> findDto) {
		return super.list(findDto);
	}
	
	

}
