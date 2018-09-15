package rest.recursos;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import manager.factoryDAO;
import iDAO.*;
import model.User;
import model.Admin;

@Path("/usuarios")
public class UserResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private iDAOUser udao = factoryDAO.getUserDAO();
	private iDAOAdmin adao = factoryDAO.getAdminDAO();
	private String mensaje;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsuarios() {
		return udao.findAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@PathParam("id") Integer id) {
		User usr = udao.findId(id);
		if (usr != null) {
			return Response.ok().entity(usr).build();
		} else {
			mensaje = "No se encontr� el usuario";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(User usuario) {
		User user = udao.findUsername(usuario.getUserName());
		System.out.println(usuario.getUserName());
		System.out.println(usuario.getPassword());
		if (user != null && user.getPassword().equals(usuario.getPassword()) && user.getIsEnable()) {
			return Response.ok().entity(user).build();
		} else {
			mensaje = "No se encontr� el usuario";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
	}
	
	@POST
	@Path("/loginAdmin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginAdmin(User usuario) {
		Admin user = adao.findUsername(usuario.getUserName());
		System.out.println(usuario.getUserName());
		System.out.println(usuario.getPassword());
		if (user != null && user.getPassword().equals(usuario.getPassword())) {
			return Response.ok().entity(user).build();
		} else {
			mensaje = "No se encontr� el usuario";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(User usuario) {
		User aux = udao.findDni(usuario.getDni());//udao.findByDNI(usuario.getDni());
		if (aux == null) { // podr�a validar si ya existe el usuario
			udao.insert(usuario);
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(User usuario) {
		User usr = udao.findId(usuario.getId());
		if (usr != null) {
			udao.update(usuario);
			return Response.ok().entity(usuario).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("[]").build();
		}
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrar(@PathParam("id") Integer id) {
		User usr = udao.findId(id);
		if (usr != null) {
			udao.delete(usr);
			return Response.noContent().build();
		} else {
			mensaje = "No existe el usuario con ese id";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
	}
}