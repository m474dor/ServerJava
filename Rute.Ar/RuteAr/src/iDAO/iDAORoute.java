package iDAO;

import java.util.List;

import model.Activity;
import model.Difficulty;
import model.MapPoint;
import model.Note;
import model.Photo;
import model.Rating;
import model.Route;
import model.User;

public interface iDAORoute {

	public List<Route> getRoutesByDifficulty(Difficulty difficulty);

	public List<Photo> getMyPhotos();

	public List<User> getDoneBy();

	public List<Rating> getMyRatings();

	public List<Note> getMyNotes();

	public List<MapPoint> getMyPoints();

	public Route findId(long id);

	public List<Route> findAll();
	
	public List<Route> findByUser(User id);

	public boolean insert(Route a);

	public boolean update(Route a);

	public boolean delete(Route a);

	List<Route> findByActivity(Activity act);
}
