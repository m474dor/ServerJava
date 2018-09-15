package test;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dAO.*;
import iDAO.*;
import model.*;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Activity act = new Activity();
		Activity act1 = new Activity();
		Activity act2 = new Activity();
		Admin admin = new Admin();
		Difficulty diff = new Difficulty();
		Difficulty diff1 = new Difficulty();
		Difficulty diff2 = new Difficulty();
		Difficulty diff3 = new Difficulty();
		Difficulty diff4 = new Difficulty();
		User us = new User();
		dAOActivity actDAO = new dAOActivity();
		dAOAdmin adminDAO = new dAOAdmin();
		dAODifficulty diffDAO = new dAODifficulty();
		dAOUser usDAO = new dAOUser();

		us.setUserName("user");
		us.setPassword("user");
		us.setIsEnable(true);
		usDAO.insert(us);
		admin.setUserName("admin");
		admin.setPassword("admin");
		adminDAO.insert(admin);
		act.setName("Caminata");
		actDAO.insert(act);
		act1.setName("Cabalgata");
		actDAO.insert(act1);
		act2.setName("Bicicleta");
		actDAO.insert(act2);
		diff.setDifficulty(DifficultyType.Facil);
		diffDAO.insert(diff);
		diff1.setDifficulty(DifficultyType.Moderado);
		diffDAO.insert(diff1);
		diff2.setDifficulty(DifficultyType.Dificil);
		diffDAO.insert(diff2);
		diff3.setDifficulty(DifficultyType.MuyDificil);
		diffDAO.insert(diff3);
		diff4.setDifficulty(DifficultyType.Expertos);
		diffDAO.insert(diff4);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
