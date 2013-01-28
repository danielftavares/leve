
package org.leve.web.ui.controllers.common;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.vbg.beans.common.Product;
import org.leve.vbg.beans.common.ProductFindDto;
import org.leve.vbg.ejb.common.ProductBean;
import org.leve.web.ui.controllers.BaseController;

@Path("/product")
public class ProductController extends BaseController<Product, ProductFindDto> {

	@EJB
	private ProductBean productBean;

	@Override
	protected ProductBean getBussinesBean() {
		return productBean;
	}
	
	@Path("/list")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public PaginatedList<Product> list(BaseFindDto<ProductFindDto> findDto) {
		return super.list(findDto);
	}
	

}
