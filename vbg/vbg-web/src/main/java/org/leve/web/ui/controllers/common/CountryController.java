
package org.leve.web.ui.controllers.common;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.vbg.beans.common.Country;
import org.leve.vbg.beans.common.CountryFindDto;
import org.leve.vbg.ejb.common.CountryBean;
import org.leve.web.ui.controllers.BaseController;

@Path("/country")
public class CountryController extends BaseController<Country, CountryFindDto> {

	@EJB
	private CountryBean countryBean;

	@Override
	protected CountryBean getBussinesBean() {
		return countryBean;
	}
	
	@Path("/list")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public PaginatedList<Country> list(BaseFindDto<CountryFindDto> findDto) {
		return super.list(findDto);
	}
	

}
