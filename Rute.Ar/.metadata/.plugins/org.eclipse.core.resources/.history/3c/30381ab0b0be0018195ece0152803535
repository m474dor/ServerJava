package model;

import javax.persistence.*;


@Entity
public class Photo {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(optional=false)
	@JoinColumn(name="route_id")
	private Route route;
	
	private String file;

	private String name;

	public Photo() {
		
	}
	
	public String getFile() {
		return file;
	}

	public void setFile(String path) {
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