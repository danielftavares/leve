
package org.leve.web.ui.controllers.common;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.vbg.beans.common.ProductType;
import org.leve.vbg.beans.common.ProductTypeFindDto;
import org.leve.vbg.ejb.common.ProductTypeBean;
import org.leve.web.ui.controllers.BaseController;

@Path("/productType")
public class ProductTypeController extends BaseController<ProductType, ProductTypeFindDto> {

	@EJB
	private ProductTypeBean productTypeBean;

	@Override
	protected ProductTypeBean getBussinesBean() {
		return productTypeBean;
	}
	
	@Path("/list")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public PaginatedList<ProductType> list(BaseFindDto<ProductTypeFindDto> findDto) {
		return super.list(findDto);
	}
	

}
