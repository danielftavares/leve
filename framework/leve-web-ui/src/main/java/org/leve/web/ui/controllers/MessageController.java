
package org.leve.web.ui.controllers;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.leve.beans.BaseFindDto;
import org.leve.beans.Message;
import org.leve.beans.MessageFindDto;
import org.leve.beans.PaginatedList;
import org.leve.ejb.MessageBean;

@Path("/message")
public class MessageController extends BaseController<Message, MessageFindDto> {

	@EJB
	private MessageBean messageBean;

	@Override
	protected MessageBean getBussinesBean() {
		return messageBean;
	}
	
	@Path("/list")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public PaginatedList<Message> list(BaseFindDto<MessageFindDto> findDto) {
		return super.list(findDto);
	}
	
}
