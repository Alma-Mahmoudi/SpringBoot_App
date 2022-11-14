package tn.enicar.service;

import java.util.List;

import tn.enicar.DAO.entities.choix.ChoixItem;

public interface ChoixService {
	
	List<ChoixItem> getChoixItems();
	ChoixItem getChoixItem (Long userId, Long sujetId);
	ChoixItem addChoixItem(ChoixItem choixItem);
	ChoixItem updateChoixItem(ChoixItem choixItem);
	void deleteChoixItem (Long userId, Long sujetId);
	
	/*Choix getCart(User user);

    void mergeLocalCart(Collection<Sujet> sujetInOrders, User user);

    void delete(String  IdChoix , User user);

    void checkout(User user);*/

}
