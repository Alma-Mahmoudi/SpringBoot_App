package tn.enicar.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.enicar.DAO.entities.Enseignant;


@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant,Long>{

}
