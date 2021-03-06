package model;

import javax.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Route {

	@Id
	@GeneratedValue
	private long id;

	private String name;

	private String description;

	private Boolean isPublic;

	private Integer doneByCount;

	private Float length;

	private Integer duration;

	private Date date;

	private Boolean isCircular;

	private Float rateAvg;
	
	private String act;
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="route", cascade=CascadeType.REMOVE)
	private List<Photo> photos;
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(mappedBy="done", cascade=CascadeType.REMOVE) //fetch = FetchType.EAGER
	private List<User> doneBy;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="owner_id")
	private User owner;
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@OneToMany(mappedBy="route", orphanRemoval = true, cascade = CascadeType.ALL)
//	private List<Rating> ratings;
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@OneToMany(mappedBy="route", orphanRemoval = true, cascade = CascadeType.ALL)
//	private List<Note> note;

	@ManyToOne(optional = false)
	@JoinColumn(name="activity_id")
	private Activity activity;

	@ManyToOne(optional = false)
	@JoinColumn(name="difficulty_id")
	private Difficulty difficulty;
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="route", cascade=CascadeType.REMOVE)
    private List<MapPoint> points;
	
	
	public Route() {
		super();
		doneBy = new ArrayList<User>();
		photos = new ArrayList<Photo>();
		points = new ArrayList<MapPoint>();
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Float getLength() {
		return this.length;
	}

	public void setLength(Float length) {
		this.length = length;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getIsCircular() {
		return this.isCircular;
	}

	public void setIsCircular(Boolean isCircular) {
		this.isCircular = isCircular;
	}

	public Float getRateAvg() {
		return this.rateAvg;
	}

	public void setRateAvg(Float rateAvg) {
		this.rateAvg = rateAvg;
	}

	public Integer getDoneByCount() {
		return this.doneByCount;
	}

	public void setDoneByCount(Integer doneByCount) {
		this.doneByCount = doneByCount;
	}

	public List<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public List<User> getDoneBy() {
		return this.doneBy;
	}

	public void setDoneBy(List<User> doneBy) {
		this.doneBy = doneBy;
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
	
	public List<MapPoint> getPoints() {
		return this.points;
	}

	public void setPoints(List<MapPoint> points) {
		this.points = points;
	}
	
	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	public Difficulty getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	public User getOwner() {
		return this.owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}
}