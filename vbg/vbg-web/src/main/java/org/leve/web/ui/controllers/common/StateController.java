
package org.leve.web.ui.controllers.common;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.vbg.beans.common.State;
import org.leve.vbg.beans.common.StateFindDto;
import org.leve.vbg.ejb.common.StateBean;
import org.leve.web.ui.controllers.BaseController;

@Path("/state")
public class StateController extends BaseController<State, StateFindDto> {

	@EJB
	private StateBean stateBean;

	@Override
	protected StateBean getBussinesBean() {
		return stateBean;
	}
	
	@Path("/list")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public PaginatedList<State> list(BaseFindDto<StateFindDto> findDto) {
		return super.list(findDto);
	}
	

}
