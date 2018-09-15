package rest.recursos;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import manager.factoryDAO;
import iDAO.*;
import model.Activity;

@Path("/actividades")
public class ActivityResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private iDAOActivity adao = factoryDAO.getActivityDAO();
	private iDAORoute rdao = factoryDAO.getRouteDAO();

	private String mensaje;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Activity> getActividades() {
		return adao.findAll(); // ?
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@PathParam("id") Integer id) {
		Activity act = adao.findId(id);
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
	public Response crear(Activity actividad) {
		Activity aux = adao.findByName(actividad.getName());
		if (aux == null) { // podría validar si ya existe la actividad
			adao.insert(actividad);
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(Activity actividad) {
		Activity act = adao.findId(actividad.getId());
		if (act != null) {
			adao.update(actividad);
			return Response.ok().entity(actividad).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("[]").build();
		}
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrar(@PathParam("id") Integer id) {
		Activity act = adao.findId(id);
		if ((act != null) && (rdao.findByActivity(act).isEmpty())) {
			adao.delete(act);
			return Response.noContent().build();
		} else {
			mensaje = "No existe la actividad con ese id o tiene rutas asociadas";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
	}
}