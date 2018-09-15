package model;

import javax.persistence.*;

@Entity
public class MapPoint {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="route_id")
	private Route route;
	
	private Float lat;

	private Float lon;

	public MapPoint() {

	}
	
	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLong() {
		return lon;
	}

	public void setLong(Float lon) {
		this.lon = lon;
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