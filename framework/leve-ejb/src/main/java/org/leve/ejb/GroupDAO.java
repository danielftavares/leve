package org.leve.ejb;

import javax.ejb.Stateless;

import org.leve.beans.BaseFindDto;
import org.leve.beans.Group;
import org.leve.beans.GroupFindDto;
import org.leve.beans.PaginatedList;
import org.leve.criteria.Criteria;
import org.leve.criteria.Order;
import org.leve.criteria.restriction.Restrictions;
import org.leve.ejb.dao.BaseDAO;

@Stateless
public class GroupDAO extends BaseDAO<Group, GroupFindDto> {

	
	@Override
	public PaginatedList<Group> listPaginated(BaseFindDto<GroupFindDto> findDto) {
		GroupFindDto findDtoBean = findDto.getBean();
		Criteria criteria = Criteria.forClass(getPersistentClass());
		
		if(findDtoBean.getName() != null){
			criteria.add(Restrictions.like("name", findDtoBean.getName()));
		}
		
		criteria.addOrder(Order.asc("name"));
		
		return mountPaginatedResponse (criteria, findDto);
	}
}
