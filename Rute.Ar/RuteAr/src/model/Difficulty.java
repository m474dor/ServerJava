package model;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Difficulty {

	@Id
	@GeneratedValue
	private long id;
	
	private DifficultyType difficulty;
	//@LazyCollection(LazyCollectionOption.FALSE)
	//@OneToMany(mappedBy="difficulty")
	//private List<Route> route;

	public Difficulty() {

	}
	
	public DifficultyType getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(DifficultyType difficulty) {
		this.difficulty = difficulty;
	}
	
	/*public List<Route> getRoute() {
		return route;
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