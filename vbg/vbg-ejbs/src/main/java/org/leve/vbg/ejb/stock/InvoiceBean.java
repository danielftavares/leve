package org.leve.vbg.ejb.stock;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.leve.ejb.BaseBusinessBean;
import org.leve.ejb.dao.BaseDAO;
import org.leve.vbg.beans.common.Brand;
import org.leve.vbg.beans.common.BrandFindDto;
import org.leve.vbg.beans.stock.Invoice;

@Stateless
public class InvoiceBean extends BaseBusinessBean<Invoice, Invoice> {

	@EJB
	private InvoiceDAO invoiceDAO;

	@Override
	protected BaseDAO<Invoice, Invoice> getDAO() {
		return invoiceDAO;
	}

}