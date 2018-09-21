package rest.recursos;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import manager.factoryDAO;
import iDAO.*;
import model.Route;
import model.User;

@Path("/rutas")
public class RouteResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private iDAORoute rdao = factoryDAO.getRouteDAO();
	private iDAOUser udao = factoryDAO.getUserDAO();

	private String mensaje;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Route> getRutasPublicas() {
		return rdao.findAll();
	}

	@GET
	@Path("myRoutes/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Route> encontrarMisRutas(@PathParam("id") Integer id) {
		return rdao.findByUser(udao.findId(id));
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Route encontrarRuta(@PathParam("id") Integer id) {
		return rdao.findId(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Route ruta) {
		Route aux = rdao.findId(ruta.getId());
		if (aux == null) { 		
			rdao.insert(ruta);
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(Route ruta) {
		Route aux = rdao.findId(ruta.getId());
		if (aux != null) {
			rdao.update(ruta);
			return Response.ok().entity(ruta).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("[]").build();
		}
	}

	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrar(@PathParam("id") Integer id) {
		Route aux = rdao.findId(id);
		User us = aux.getOwner();
		us.getMyRoute().remove(aux);
		if (aux != null && us != null) {
			udao.update(us);
			rdao.delete(aux);
			return Response.noContent().build();
		} else {
			mensaje = "No existe esa ruta";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
	}
}