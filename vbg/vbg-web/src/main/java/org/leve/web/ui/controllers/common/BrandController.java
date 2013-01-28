
package org.leve.web.ui.controllers.common;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.vbg.beans.common.Brand;
import org.leve.vbg.beans.common.BrandFindDto;
import org.leve.vbg.ejb.common.BrandBean;
import org.leve.web.ui.controllers.BaseController;

@Path("/brand")
public class BrandController extends BaseController<Brand, BrandFindDto> {

	@EJB
	private BrandBean brandBean;

	@Override
	protected BrandBean getBussinesBean() {
		return brandBean;
	}
	
	@Path("/list")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public PaginatedList<Brand> list(BaseFindDto<BrandFindDto> findDto) {
		return super.list(findDto);
	}
	

}
