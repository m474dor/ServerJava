package manager;


import iDAO.iDAOActivity;
import iDAO.iDAOAdmin;
import iDAO.iDAODifficulty;
import iDAO.iDAOHuman;
import iDAO.iDAOMapPoint;
import iDAO.iDAONote;
import iDAO.iDAOPhoto;
import iDAO.iDAORating;
import iDAO.iDAORoute;
import iDAO.iDAOUser;
import model.Activity;
import model.Admin;
import model.Difficulty;
import model.Human;
import model.MapPoint;
import model.Note;
import model.Photo;
import model.Rating;
import model.Route;
import model.User;
import dAO.dAOActivity;
import dAO.dAOAdmin;
import dAO.dAODifficulty;
import dAO.dAOHuman;
import dAO.dAOMapPoint;
import dAO.dAONote;
import dAO.dAOPhoto;
import dAO.dAORating;
import dAO.dAORoute;
import dAO.dAOUser;

public class factoryDAO {

	public static iDAOActivity getActivityDAO(){
		return new dAOActivity();
	}

	public static iDAOAdmin getAdminDAO(){
		return new dAOAdmin();
	}

	public static iDAODifficulty getDifficultyDAO(){
		return new dAODifficulty();
	}

	public static iDAOHuman getHumanDAO(){
		return new dAOHuman();
	}

	public static iDAOMapPoint getMapPointDAO(){
		return new dAOMapPoint();
	}

	public static iDAONote getNoteDAO(){
		return new dAONote();
	}

	public static iDAOPhoto getPhotoDAO(){
		return new dAOPhoto();
	}

	public static iDAORating getRatingDAO(){
		return new dAORating();
	}

	public static iDAORoute getRouteDAO(){
		return new dAORoute();
	}

	public static iDAOUser getUserDAO(){
		return new dAOUser();
	}
}
