package rest.recursos;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import manager.factoryDAO;
import iDAO.*;
import model.Difficulty;

@Path("/dificultades")
public class DifficultyResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private iDAODifficulty adao = factoryDAO.getDifficultyDAO();
	private String mensaje;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Difficulty> getDificultades() {
		return adao.findAll(); // ?
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@PathParam("id") Integer id) {
		Difficulty act = adao.findId(id);
		if (act != null) {
			return Response.ok().entity(act).build();
		} else {
			mensaje = "No se encontró la actividad";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Difficulty dificultad) {
		Difficulty aux = adao.findId(dificultad.getId());
		if (aux == null) {
			adao.insert(dificultad);
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(Difficulty dificultad) {
		Difficulty act = adao.findId(dificultad.getId());
		if (act != null) {
			adao.update(dificultad);
			return Response.ok().entity(dificultad).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("[]").build();
		}
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrar(@PathParam("id") Integer id) {
		Difficulty act = adao.findId(id);
		if (act != null) {
			adao.delete(act);
			return Response.noContent().build();
		} else {
			mensaje = "No existe la dificultad con ese id";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
	}
}