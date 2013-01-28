
package org.leve.web.ui.controllers;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.beans.User;
import org.leve.beans.UserFindDto;
import org.leve.ejb.UserBean;

@Path("/user")
public class UserController extends BaseController<User, UserFindDto> {

	@EJB
	private UserBean userBean;

	@Override
	protected UserBean getBussinesBean() {
		return userBean;
	}
	
	@Path("/list")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public PaginatedList<User> list(BaseFindDto<UserFindDto> findDto) {
		return super.list(findDto);
	}
	
}
