
package org.leve.web.ui.controllers.common;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.vbg.beans.common.City;
import org.leve.vbg.beans.common.CityFindDto;
import org.leve.vbg.ejb.common.CityBean;
import org.leve.web.ui.controllers.BaseController;

@Path("/city")
public class CityController extends BaseController<City, CityFindDto> {

	@EJB
	private CityBean stateBean;

	@Override
	protected CityBean getBussinesBean() {
		return stateBean;
	}
	
	@Path("/list")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public PaginatedList<City> list(BaseFindDto<CityFindDto> findDto) {
		return super.list(findDto);
	}
	

}
