package iDAO;

import java.util.List;

import model.Admin;

public interface iDAOAdmin {
	public Admin findId(long id);

	public List<Admin> findAll();

	public boolean insert(Admin a);

	public boolean update(Admin a);

	public boolean delete(Admin a);

	public Admin findUsername(String username);

}
