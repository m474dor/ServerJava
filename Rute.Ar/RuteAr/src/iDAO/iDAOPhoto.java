package iDAO;

import java.util.List;

import model.Photo;

public interface iDAOPhoto {
	public Photo findId(long id);

	public List<Photo> findAll();

	public boolean insert(Photo a);

	public boolean update(Photo a);

	public boolean delete(Photo a);
}