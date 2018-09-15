package model;

import java.sql.Blob;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;


@Entity
public class Photo {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(optional=false)
	@JoinColumn(name="route_id")
	private Route route;
	
	private Blob file;

	private String name;

	public Photo() {
		
	}
	
	public Blob getFile() {
		return file;
	}

	public void setFile(Blob path) {
		this.file = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Route getRoute() {
		return route;
	}
	
	public void setRoute(Route route) {
		this.route = route;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
}