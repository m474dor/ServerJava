package model;

import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Activity {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	//@LazyCollection(LazyCollectionOption.FALSE)
	//@OneToMany(mappedBy="activity")
	//private List<Route> route;

	public Activity() {

	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public List<Route> getRoute() {
		return this.route;
	}
	
	public void setRoute(List<Route> route) {
		this.route = route;
	}*/
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
}
