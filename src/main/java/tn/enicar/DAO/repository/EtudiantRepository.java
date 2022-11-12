package tn.enicar.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.enicar.DAO.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long>{
	
}