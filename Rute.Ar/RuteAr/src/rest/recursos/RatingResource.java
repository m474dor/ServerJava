package rest.recursos;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import manager.factoryDAO;
import iDAO.*;
import model.Rating;
import model.Route;
import model.User;

@Path("/puntajes")
public class RatingResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private iDAORating adao = factoryDAO.getRatingDAO();
	private iDAORoute rdao = factoryDAO.getRouteDAO();
	private iDAOUser udao = factoryDAO.getUserDAO();

	private String mensaje;

	@GET
	@Path("todos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Rating> getPuntajes(@PathParam("id") Integer id) {
		Route rou = rdao.findId(id);
		return adao.findAll(rou);
	}

	@GET
	@Path("{id}/{user}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@PathParam("id") Integer id,@PathParam("user") Integer user) {
		Route rou = rdao.findId(id);
		User us = udao.findId(user);
		Rating aux = adao.findUserRating(rou, us);
		if (aux != null) {
			return Response.ok().entity(aux).build();
		} else {
			mensaje = "No se encontró el puntaje";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}//change mensaje por "[]"
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Rating rating) {
		Rating aux = adao.findId(rating.getId());
		if (aux == null) {
			adao.insert(rating);
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(Rating rating) {
		Rating aux = adao.findId(rating.getId());
		if (aux != null) {
			adao.update(rating);
			return Response.ok().entity(rating).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("[]").build();
		}
	}

	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrar(@PathParam("id") Integer id) {
		Rating aux = adao.findId(id);
		if (aux != null) {
			adao.delete(aux);
			return Response.noContent().build();
		} else {
			mensaje = "No existe el puntaje con ese id";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
	}
}