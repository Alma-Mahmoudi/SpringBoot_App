package tn.enicar.service;

import java.util.List;

import tn.enicar.DAO.entities.Sujet;

public interface SujetService {
	
	public Sujet addSujet(Sujet sujet);
	
	
	public Sujet getById(long id);
	public List<Sujet>getAllSujets();
	
	Sujet updateSujet(Long id, Sujet sujet);
	
	public void  deleteSujet(Long id);
	
	//public Sujet abon(Sujet sujet ,Long id);
	
	//PDF
	public List<Sujet> listAll();

	

}
