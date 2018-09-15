package iDAO;

import java.util.List;

import model.Activity;

public interface iDAOActivity {

	public Activity findByName(String name);

	public Activity findId(long id);

	public List<Activity> findAll();

	public boolean insert(Activity a);

	public boolean update(Activity a);

	public boolean delete(Activity a);

}