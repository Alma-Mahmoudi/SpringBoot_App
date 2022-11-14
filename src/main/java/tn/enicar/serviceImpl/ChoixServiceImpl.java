package tn.enicar.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicar.DAO.entities.choix.ChoixItem;
import tn.enicar.DAO.repository.ChoixItemRepository;
import tn.enicar.exception.ChoixItemAlreadyExistsException;
import tn.enicar.exception.ChoixItemDoesNotExistsException;
import tn.enicar.service.ChoixService;


@Service 
public class ChoixServiceImpl implements ChoixService {

	@Autowired
	private ChoixItemRepository choixRep ; 
	
	public ChoixServiceImpl(ChoixItemRepository choixRep) {
		this.choixRep = choixRep ; 
	}
  //-------------Les services-------------------
	//tous les choix 
	@Override
	public List<ChoixItem> getChoixItems() {return choixRep.findAll(); }
	
	
	//chaque choix
	@Override
	public ChoixItem getChoixItem (Long userId, Long sujetId) {
	        for (ChoixItem item : getChoixItems ()) {
	            if (item.getPk().getUser().getUserId() == userId && item.getPk().getSujet().getIdSujet() == sujetId) {
	                return item;
	            }
	        }
	        throw new ChoixItemDoesNotExistsException(
	                "Choix item w/ user id " + userId + " and sujet id " + sujetId + " does not exist."
	        );
	    }

	// ajouter un choix => creer un choix 
	@Override
	public ChoixItem addChoixItem(ChoixItem choixItem) {
        for (ChoixItem item : getChoixItems()) {
            if (item.equals(choixItem)) {
                throw new ChoixItemAlreadyExistsException(
                        "Choix item w/ user id " + choixItem.getPk().getUser().getUserId() + " and sujet id " +
                         choixItem.getSujet().getIdSujet() + " already exists."
                );
            }
        }
        return this.choixRep.save(choixItem);
    }
    //Mise a jour ( Update le choix ) 
	@Override
    public ChoixItem updateChoixItem(ChoixItem choixItem) {
        for (ChoixItem item : getChoixItems()) {
            if (item.equals(choixItem)) {
                item.setQuantity(choixItem.getQuantity());
                return choixRep.save(item);
            }
        }
        throw new ChoixItemDoesNotExistsException(
                "Cart item w/ user id " + choixItem.getPk().getUser().getUserId() + " and sujet id " +
                		choixItem.getSujet().getIdSujet() + " does not exist."
        );
    }
    //Supprimer le Choix
	@Override
    public void deleteChoixItem (Long userId, Long sujetId) {
        for (ChoixItem item : getChoixItems()) {
            if (item.getPk().getUser().getUserId() == userId && item.getPk().getSujet().getIdSujet() == sujetId) {
                choixRep.delete(item);
                return;
            }
        }
        throw new ChoixItemDoesNotExistsException(
                "Cart item w/ user id " + userId + " and sujet id " +sujetId + " does not exist."
        );
    }
	

}
