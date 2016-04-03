package controller;

import persistance.DataDAO;

public class ControllerLists {
	
	private DataDAO dDAO = new DataDAO();
	
	public ControllerLists() {
		try {
			dDAO.populaEscola();
			dDAO.populaJurado();
			dDAO.populaQuesito();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
