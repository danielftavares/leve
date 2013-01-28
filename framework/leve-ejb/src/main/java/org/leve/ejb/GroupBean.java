package org.leve.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.leve.beans.Group;
import org.leve.beans.GroupFindDto;

@Stateless
public class GroupBean extends BaseBusinessBean<Group, GroupFindDto> {

	@EJB
	private GroupDAO groupDAO;

	@Override
	protected GroupDAO getDAO() {
		return groupDAO;
	}


}