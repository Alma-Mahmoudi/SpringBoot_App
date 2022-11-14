package tn.enicar.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tn.enicar.DAO.entities.Sujet;
import tn.enicar.DAO.repository.SujetRepository;
import tn.enicar.exception.SujetNotFoundException;
import tn.enicar.service.SujetService;

@Service
public class SujetServiceImpl implements SujetService {
	
	@Autowired
	private final SujetRepository sujetRepo;
	
	//private final UserRepository userRepo;
	
	public SujetServiceImpl(SujetRepository sujetRepo) {
		this.sujetRepo=sujetRepo ;
		}
	//------------------------------------------------------
	@Override
	public Sujet addSujet(Sujet sujet) {return this.sujetRepo.save(sujet);}

	@Override
	public void deleteSujet(Long id) {
		this.sujetRepo.deleteById(id);
	}
	@Override
	public Sujet getById(long id) {	
		return sujetRepo.findById(id).orElseThrow(() ->
                             new SujetNotFoundException("Sujet by id" + id +"was not found"));
		//Optional<Sujet>course= this.sujetRepo.findById(id);
		//return course.isPresent()?course.get():null;
	}

	@Override
	public List<Sujet> getAllSujets() {
		return this.sujetRepo.findAll();
	}

	@Override
	public Sujet updateSujet(Long id, Sujet sujet) {
		Sujet oldSujet = getById(id);
		 oldSujet.setTitre(sujet.getTitre());
		 oldSujet.setDescription(sujet.getDescription());
		 oldSujet.setSpecialite(sujet.getSpecialite());
		 oldSujet.setEncadrant(sujet.getEncadrant());
		 oldSujet.setEmail_encadrant(sujet.getEmail_encadrant());
		 
		 return sujetRepo.save(oldSujet);
	}
	
	//---- PDF ---- Code for the Service layer :
	@Override 
	public List<Sujet> listAll() {
		   return sujetRepo.findAll(Sort.by("titre").ascending());
	}

    
	//Inscription a ce cours 
	/*@Override
	public Sujet abon(Sujet sujet, Long id) {
		Optional<User> e = userRepo.findById(id);
		List<Groupe>l=new ArrayList<>();
		
		l=sujet.getGoupe();
		l.add((Groupe)e.get());
		return this.sujetRepo.save(sujet) ;
	}*/

}
