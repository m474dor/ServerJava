package rest.recursos;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import manager.factoryDAO;
import iDAO.*;
import model.*;

@Path("/realizadas")
public class DoneByResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private iDAOUser udao = factoryDAO.getUserDAO();
	private iDAORoute rdao = factoryDAO.getRouteDAO();

	private String mensaje;

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers(@PathParam("id") Integer id) {
		Route aux = rdao.findId(id);
		return aux.getDoneBy();
		//return udao.findDoneBy(aux); 
	}

	@POST
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(User user,@PathParam("id") Integer id) {
		Route aux = rdao.findId(id);
		List<User> listUser = aux.getDoneBy();
		listUser.add(user);
		aux.setDoneBy(listUser);
		List<Route> list = user.getDone();
		list.add(aux);
		user.setDone(list);
		if (aux != null && user != null) {
			udao.update(user);
			rdao.update(aux);
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}

	@DELETE
	@Path("{id}/{us}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrar(@PathParam("id") Integer id,@PathParam("us") Integer us) {
		Route aux = rdao.findId(id);
		User user = udao.findId(us);
		List<Route> list = user.getDone();
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId()==aux.getId())
				list.remove(list.get(i));
		}
		//list.remove(aux);
		user.setDone(list);
		List<User> listUser = aux.getDoneBy();
		for(int i = 0; i < listUser.size(); i++) {
			if(listUser.get(i).getId()==user.getId())
				listUser.remove(listUser.get(i));
		}
		//listUser.remove(user);
		aux.setDoneBy(listUser);
		if (aux != null && user != null) {
			udao.update(user);
			rdao.update(aux);
			return Response.noContent().build();
		} else {
			mensaje = "No se actualizo el delete de doneBy";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
	}
}