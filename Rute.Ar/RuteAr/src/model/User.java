package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("User")
public class User extends Human {
	
	private Boolean isEnable;
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	@JoinColumn(name="done_id")
	private List<Route> done;
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="owner", orphanRemoval = true, cascade = CascadeType.REMOVE)
	private  List<Route> myRoute;
	
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@OneToMany(mappedBy="rateBy", orphanRemoval = true, cascade = CascadeType.ALL)
//	private List<Rating> ratings;
//	
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@OneToMany(mappedBy="user", orphanRemoval = true, cascade = CascadeType.ALL)
//	private List<Note> note; 

	public User() {
		super();
		done = new ArrayList<Route>();
		myRoute = new ArrayList<Route>();
	}
	
	public Boolean getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public void setRating(Route route) {
		
	}

	public void setDoneByMe(Route route) {
		done.add(route);
	}

	public void newTrail() {
	}
	
	public List<Route> getDone() {
		return this.done;
	}
	
	public void setDone(List<Route> done) {
		this.done = done;
	}
	
	public List<Route> getMyRoute() {
		return this.myRoute;
	}
	
	public void setMyRoute(List<Route> myRoute) {
		this.myRoute = myRoute;
	}

//	public List<Rating> getRatings() {
//		return this.ratings;
//	}
//	
//	public void setRatings(List<Rating> ratings) {
//		this.ratings = ratings;
//	}
//	
//	public List<Note> getNote() {
//		return this.note;
//	}
//	
//	public void setNote(List<Note> note) {
//		this.note = note;
//	}

}