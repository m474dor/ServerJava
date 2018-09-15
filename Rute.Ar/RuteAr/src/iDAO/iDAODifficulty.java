package iDAO;

import java.util.List;

import model.Difficulty;

public interface iDAODifficulty {
	public Difficulty findId(long id);

	public List<Difficulty> findAll();

	public boolean insert(Difficulty a);

	public boolean update(Difficulty a);

	public boolean delete(Difficulty a);
}
