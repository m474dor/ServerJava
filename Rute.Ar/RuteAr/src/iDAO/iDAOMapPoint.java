package iDAO;

import java.util.List;

import model.MapPoint;

public interface iDAOMapPoint {
	public MapPoint findId(long id);

	public List<MapPoint> findAll();

	public boolean insert(MapPoint a);

	public boolean update(MapPoint a);

	public boolean delete(MapPoint a);
}
