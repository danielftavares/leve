package org.leve.vbg.ejb.stock;

import javax.ejb.Stateless;

import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.criteria.Criteria;
import org.leve.ejb.dao.BaseDAO;
import org.leve.vbg.beans.stock.Invoice;

@Stateless
public class InvoiceDAO extends BaseDAO<Invoice, Invoice> {

	@Override
	public PaginatedList<Invoice> listPaginated(BaseFindDto<Invoice> findDto) {
		Invoice findDtoBean = findDto.getBean();
		Criteria criteria = Criteria.forClass(getPersistentClass());
		
		return mountPaginatedResponse (criteria, findDto);
	}

}
