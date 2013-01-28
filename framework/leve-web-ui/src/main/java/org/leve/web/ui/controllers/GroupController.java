
package org.leve.web.ui.controllers;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.leve.beans.BaseFindDto;
import org.leve.beans.Group;
import org.leve.beans.GroupFindDto;
import org.leve.beans.PaginatedList;
import org.leve.ejb.GroupBean;

@Path("/group")
public class GroupController extends BaseController<Group, GroupFindDto> {

	@EJB
	private GroupBean groupBean;

	@Override
	protected GroupBean getBussinesBean() {
		return groupBean;
	}
	
	@Path("/list")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public PaginatedList<Group> list(BaseFindDto<GroupFindDto> findDto) {
		return super.list(findDto);
	}
	
}
