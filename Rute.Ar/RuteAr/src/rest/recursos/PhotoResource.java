package rest.recursos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

//import com.sun.jersey.core.header.FormDataContentDisposition;
//import com.sun.jersey.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.sun.org.apache.xerces.internal.util.Status;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

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
	private static final String UPLOAD_FOLDER = "uploadedFiles/";

	@GET
	@Path("list/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Photo> getPhotos(@PathParam("id") Integer id) {
		Route aux = rdao.findId(id);
		return aux.getPhotos();
		// return udao.findDoneBy(aux);
	}
	
	@GET
	@Path("{id}")
	@Produces("image/jpg")	
	public Response getPhotosFiles(@PathParam("id") Integer id) {
		Photo aux = pdao.findId(id);
		File file = new File(aux.getFile());
		return Response.ok(file, "image/jpg").header("Inline", "filename=\"" + file.getName() + "\"").build();

//		Route aux = rdao.findId(id);
//		List<File> list = new ArrayList<File>();
//		List<Photo> photos = pdao.findAll(aux);
//		if(photos!=null)
//			for(int i = 0;i<photos.size();i++) {
//				list.add(new File(photos.get(i).getFile()));
//			}
//		GenericEntity<List<File>> entity = new GenericEntity<List<File>>(list) {};
////		ResponseBuilder response = Response.ok((Object) list);
////		response.header("Content-Disposition", "attachment; size=\"" + list.size() + "\"");
////		return response.build();
//		return Response.ok(entity).build();
	}
	
	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrar(@PathParam("id") Integer id) {
		Photo aux = pdao.findId(id);
//		User us = aux.getOwner();
//		List<Route> list = us.getMyRoute();
//		for(int i=0;i<list.size();i++) {
//			if(list.get(i).getId()==aux.getId()) {
//				list.remove(i);
//				break;
//			}
//		}
//		us.setMyRoute(list);
		if (aux != null) {// && us != null) {
			//udao.update(us);
			pdao.delete(aux);
			return Response.ok().build();
		} else {
			String mensaje = "No se entro a eliminar";
			return Response.status(Response.Status.NOT_FOUND).entity(mensaje).build();
		}
	}

	@POST
	@Path("{id}/{fileDetail}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(
			@PathParam("id") Integer id,
			@PathParam("fileDetail") String fileDetail,
			InputStream uploadedInputStream) {
		Route aux = rdao.findId(id);
		// check if all form parameters are provided
		if (uploadedInputStream == null || fileDetail == null)
			return Response.status(400).entity("Invalid form data").build();
		// create our destination folder, if it not exists
		try {
			createFolderIfNotExists(UPLOAD_FOLDER);
		} catch (SecurityException se) {
			return Response.status(500).entity("Can not create destination folder on server").build();
		}
		String uploadedFileLocation = UPLOAD_FOLDER + fileDetail;
		try {
			saveToFile(uploadedInputStream, uploadedFileLocation);
			Photo p = new Photo();
			p.setRoute(aux);
			p.setFile(uploadedFileLocation);
			p.setName(fileDetail);
			pdao.insert(p);
		} catch (IOException e) {
			return Response.status(500).entity("Can not save file").build();
		}
		return Response.status(200).entity("File saved to " + uploadedFileLocation).build();
	}

	/**
	 * Utility method to save InputStream data to target location/file
	 * 
	 * @param inStream
	 *            - InputStream to be saved
	 * @param target
	 *            - full path to destination file
	 */
	private void saveToFile(InputStream inStream, String target) throws IOException {
		OutputStream out = null;
		int read = 0;
		byte[] bytes = new byte[1024];
		out = new FileOutputStream(new File(target));
		while ((read = inStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
	}

	/**
	 * Creates a folder to desired location if it not already exists
	 * 
	 * @param dirName
	 *            - full path to the folder
	 * @throws SecurityException
	 *             - in case you don't have permission to create the folder
	 */
	private void createFolderIfNotExists(String dirName) throws SecurityException {
		File theDir = new File(dirName);
		if (!theDir.exists()) {
			theDir.mkdir();
		}
	}
}