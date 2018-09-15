package rest.recursos;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import manager.factoryDAO;
import iDAO.*;
import model.Note;

@Path("/notas")
public class NoteResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private iDAONote adao = factoryDAO.getNoteDAO();
	private iDAORoute rdao = factoryDAO.getRouteDAO();

	private String mensaje;

	@GET
	@Path("ruta/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Note> getNotas(@PathParam("id") Integer id) {
		return adao.findAll(rdao.findId(id)); 
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@PathParam("id") Integer id) {
		Note act = adao.findId(id);
		if (act != null) {
			return Response.ok().entity(act).build();
		} else {
			mensaje = "No se encontró la nota";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Note nota) {
		Note aux = adao.findId(nota.getId());
		if (aux == null) { // podría validar si ya existe la actividad
			adao.insert(nota);
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(Note nota) {
		Note act = adao.findId(nota.getId());
		if (act != null) {
			adao.update(nota);
			return Response.ok().entity(nota).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("[]").build();
		}
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrar(@PathParam("id") Integer id) {
		Note act = adao.findId(id);
		if (act != null) {
			adao.delete(act);
			return Response.noContent().build();
		} else {
			mensaje = "No existe la actividad con ese id";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
	}
}