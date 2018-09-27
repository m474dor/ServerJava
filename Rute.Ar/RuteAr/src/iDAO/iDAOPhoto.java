package iDAO;

import java.util.List;

import model.Photo;
import model.Route;

public interface iDAOPhoto {
	public Photo findId(long id);

	public List<Photo> findAll(Route id);

	public boolean insert(Photo a);

	public boolean update(Photo a);

	public boolean delete(Photo a);
}
