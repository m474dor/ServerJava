package iDAO;

import java.util.List;

import model.Note;
import model.Rating;
import model.Route;
import model.User;

public interface iDAOUser {

	public List<Route> getMyRoutes();

	public List<Route> getDoneRoutes();

	public List<Rating> getMyRatings();

	public List<Note> getMyNotes();

	public User findId(long id);

	public List<User> findAll();

	public boolean insert(User a);

	public boolean update(User a);

	public boolean delete(User a);

	public User findDni(Integer dni);

	public User findUsername(String username);

	public List<User> findDoneBy(Route aux);
}
