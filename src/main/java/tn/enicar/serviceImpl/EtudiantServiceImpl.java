package tn.enicar.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import tn.enicar.DAO.repository.EtudiantRepository;
import tn.enicar.service.EtudiantService;



public class EtudiantServiceImpl implements EtudiantService {
	
	@Autowired
	private final EtudiantRepository etdRepo;
	
	public EtudiantServiceImpl(EtudiantRepository etdRepo) {
		this.etdRepo=etdRepo ;
		}
	
	//-----------Les services -------------------

}
