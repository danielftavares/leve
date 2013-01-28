package org.leve.ejb;

import javax.ejb.Stateless;

import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.beans.User;
import org.leve.beans.UserFindDto;
import org.leve.criteria.Criteria;
import org.leve.criteria.Order;
import org.leve.criteria.restriction.Restrictions;
import org.leve.ejb.dao.BaseDAO;

@Stateless
public class UserDAO extends BaseDAO<User, UserFindDto> {

	
	@Override
	public PaginatedList<User> listPaginated(BaseFindDto<UserFindDto> findDto) {
		UserFindDto findDtoBean = findDto.getBean();
		Criteria criteria = Criteria.forClass(getPersistentClass());
		if(findDtoBean.getKey() != null){
			criteria.add(Restrictions.like("key", findDtoBean.getKey()));
		}
		
		if(findDtoBean.getName() != null){
			criteria.add(Restrictions.like("name", findDtoBean.getName()));
		}
		
		criteria.addOrder(Order.asc("name"));
		
		return mountPaginatedResponse (criteria, findDto);
	}
}
