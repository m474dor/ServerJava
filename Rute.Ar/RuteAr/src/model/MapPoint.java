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
	
	private double lat;

	private double lon;

	public MapPoint() {

	}
	
	public double getLat() {
		return lat;
	}

	public void setLat(double d) {
		this.lat = d;
	}

	public double getLong() {
		return lon;
	}

	public void setLong(double lon) {
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