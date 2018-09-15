package model;

import javax.persistence.*;

@Entity
public class Rating {

	@Id
	@GeneratedValue
	private long id;
	
	private Integer rating; 

	@ManyToOne(optional=false)
	@JoinColumn(name="rateBy_id")
	private User rateBy;
	@ManyToOne(optional=false)
	@JoinColumn(name="route_id")
	private Route route;

	public Rating() {
		
	}
	
	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public User getRateBy() {
		return this.rateBy;
	}
	
	public void setRateBy(User rateBy) {
		this.rateBy = rateBy;
	}

	public Route getRoute() {
		return this.route;
	}
	
	public void setRoute(Route route) {
		this.route = route;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
}