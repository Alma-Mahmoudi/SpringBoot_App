package tn.enicar.DAO.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.enicar.DAO.entities.Sujet;

@Repository 
public interface SujetRepository extends JpaRepository<Sujet,Long> {

	Sujet findByTitre(String tire);

    Optional<Sujet> findById (Long id);
	
	void deleteById(Long id);
}


