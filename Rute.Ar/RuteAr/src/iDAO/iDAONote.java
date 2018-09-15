package iDAO;

import java.util.List;

import model.Note;
import model.Route;

public interface iDAONote {
	public Note findId(long id);

	public List<Note> findAll(Route id);

	public boolean insert(Note a);

	public boolean update(Note a);

	public boolean delete(Note a);
}
