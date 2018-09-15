package iDAO;

import java.util.List;

import model.Rating;
import model.Route;
import model.User;

public interface iDAORating {
	public Rating findId(long id);

	public List<Rating> findAll(Route rou);
	
	public Rating findUserRating(Route rou,User us);

	public boolean insert(Rating a);

	public boolean update(Rating a);

	public boolean delete(Rating a);
}
