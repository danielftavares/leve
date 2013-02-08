
package org.leve.web.ui.controllers;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.beans.User;
import org.leve.beans.UserFindDto;
import org.leve.ejb.UserBean;
import org.leve.exception.LeveException;

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
	
	@Path("/logout")
	@POST
	public void logout(@javax.ws.rs.core.Context HttpServletRequest req, @javax.ws.rs.core.Context HttpServletResponse resp) {
		try {
			req.logout();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/");
		    dispatcher.forward(req, resp);
		} catch (Exception e) {
			throw new LeveException(e);
		}
	}
	
}
