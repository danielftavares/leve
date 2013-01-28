
package org.leve.web.ui.controllers.stock;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.vbg.beans.stock.Invoice;
import org.leve.vbg.ejb.stock.InvoiceBean;
import org.leve.web.ui.controllers.BaseController;

@Path("/invoice")
public class InvoiceController extends BaseController<Invoice, Invoice> {

	@EJB
	private InvoiceBean invoiceBean;

	@Override
	protected InvoiceBean getBussinesBean() {
		return invoiceBean;
	}
	
	@Path("/list")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public PaginatedList<Invoice> list(BaseFindDto<Invoice> findDto) {
		return super.list(findDto);
	}
	

}
