package rest.recursos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Request;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;

import manager.factoryDAO;
import iDAO.*;
import model.*;

@Path("/kml")
public class KMLResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private iDAORoute rdao = factoryDAO.getRouteDAO();
	private iDAOMapPoint mdao = factoryDAO.getMapPointDAO();
	
	private static final String UPLOAD_FOLDER = "uploadedKML/";


	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)	
	public List<MapPoint> getPhotos(@PathParam("id") Integer id) {
		Route aux = rdao.findId(id);
		return aux.getPoints();
	}
	
	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrar(@PathParam("id") Integer id) {
		MapPoint aux = mdao.findId(id);
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
			mdao.delete(aux);
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
			final Kml kml = Kml.unmarshal(new File(uploadedFileLocation));
			final Placemark doc = (Placemark) kml.getFeature();
			Point point = (Point) doc.getGeometry();
			List<Coordinate> coordinates = point.getCoordinates();
			for (Coordinate coordinate : coordinates) {
			    System.out.println(coordinate.getLatitude());
			    System.out.println(coordinate.getLongitude());
			    System.out.println(coordinate.getAltitude());
			    MapPoint ma = new MapPoint();
			    ma.setLat(coordinate.getLatitude());
			    ma.setLong(coordinate.getLongitude());
			    ma.setRoute(aux);
			    mdao.insert(ma);
			}
//			List<MapPoint> list = aux.getPoints();
//			for(MapPoint map: list) {
//				
//			}
//			
//			aux.setPoints(list);
//			rdao.update(aux);
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