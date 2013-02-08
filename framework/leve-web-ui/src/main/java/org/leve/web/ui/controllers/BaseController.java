package org.leve.web.ui.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.leve.beans.BaseFindDto;
import org.leve.beans.PaginatedList;
import org.leve.ejb.BaseBusinessBean;

public abstract class BaseController<T, F> {
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public T save(T bean) {
		return getBussinesBean().save(bean);
	}
	
	@POST
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public PaginatedList<T> list(BaseFindDto<F> findDto) {
		return getBussinesBean().listPaginated(findDto);
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<T> list() {
		return getBussinesBean().list();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public T load(@PathParam("id") Long id){
		return getBussinesBean().find(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete/{id}")
	public void delete(@PathParam("id") Long id){
		getBussinesBean().deleteById(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/key/{key}")
	public T loadByKey(@PathParam("key") String key){
		return getBussinesBean().findByKey(key);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/autocomplete")
	public List<T> autocomplete(@QueryParam("desc") String desc){
		return getBussinesBean().autocomplete(desc);
	}

	protected abstract BaseBusinessBean<T,F> getBussinesBean();
}
