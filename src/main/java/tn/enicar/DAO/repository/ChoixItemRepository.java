package tn.enicar.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.enicar.DAO.entities.choix.ChoixItem;
import tn.enicar.DAO.entities.choix.ChoixItemPK;

@Repository
public interface ChoixItemRepository extends JpaRepository <ChoixItem, ChoixItemPK> {

}
