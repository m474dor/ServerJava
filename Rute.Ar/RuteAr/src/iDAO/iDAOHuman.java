package iDAO;

import java.util.List;

import model.Human;

public interface iDAOHuman {
	public Human findId(long id);

	public List<Human> findAll();

	public boolean insert(Human a);

	public boolean update(Human a);

	public boolean delete(Human a);
}
