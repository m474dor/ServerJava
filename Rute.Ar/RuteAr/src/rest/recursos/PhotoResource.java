package rest.recursos;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.mysql.jdbc.Blob;

import manager.factoryDAO;
import iDAO.*;
import model.*;

@Path("/fotos")
public class PhotoResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private iDAOPhoto pdao = factoryDAO.getPhotoDAO();
	private iDAORoute rdao = factoryDAO.getRouteDAO();

	private String mensaje;

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Photo> getPhotos(@PathParam("id") Integer id) {
		Route aux = rdao.findId(id);
		return aux.getPhotos();
		//return udao.findDoneBy(aux); 
	}

	@POST
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(List<Photo> photos,@PathParam("id") Integer id) {
		Route aux = rdao.findId(id);
		aux.setPhotos(photos);
		if (aux != null && photos != null) {
			rdao.update(aux);
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}

//	@DELETE
//	@Path("{id}")
//	@Produces(MediaType.TEXT_PLAIN)
//	public Response borrar(@PathParam("id") Integer id) {
//		Photo aux = pdao.findId(id);
//		Route rou = aux.getRoute();
//		List<Photo> list = rou.getPhotos();
//		
//		for(int i = 0; i < list.size(); i++) {
//			if(list.get(i).getId()==aux.getId())
//				list.remove(list.get(i));
//		}
//		//list.remove(aux);
//		rou.setPhotos(list);
//		if (aux != null && rou!= null) {
//			rdao.update(rou);
//			pdao.delete(aux);
//			return Response.noContent().build();
//		} else {
//			mensaje = "No se actualizo el delete de photo";
//			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
//		}
//	}
}