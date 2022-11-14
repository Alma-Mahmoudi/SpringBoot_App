package tn.enicar.service;

import java.util.List;

import tn.enicar.DAO.entities.Role;
import tn.enicar.DAO.entities.User;

public interface UserService {
	
	//Partie CRUD 
	
	public User addUser(User user);
	
	public User getById(long id);
	public List<User>getAllUser();
	
	User updateUser (Long id, User user);
	public void  deleteUser(Long id);
	
	Role saveRole(Role role);
	void addRoleToUser(String username , String roleName);
	
	//Login 
	public User getByInfo(String email,String password);
     
    //PDF
	public List<User> listAll();

}
