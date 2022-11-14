package tn.enicar.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.enicar.DAO.entities.Role;
import tn.enicar.DAO.entities.User;
import tn.enicar.DAO.repository.RoleRepository;
import tn.enicar.DAO.repository.UserRepository;
import tn.enicar.exception.UserNotFoundException;
import tn.enicar.service.UserService;

@Service @Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private final UserRepository userRepo;
	@Autowired
	private final RoleRepository rolerep ;
	 
	public UserServiceImpl(UserRepository userRepo, RoleRepository rolerep) {
		 this.userRepo = userRepo;
		 this.rolerep = rolerep; }
//-------Les services--------------------------------
	//ajouter un utilisateur => creation (register)
	@Override
	public User addUser(User user) {return this.userRepo.save(user);}
	
	@Override
	public Role saveRole(Role role) {return rolerep.save(role);}
	@Override
	public void addRoleToUser(String username , String roleName) {
			User user=userRepo.findByUsername(username);
			Role role=rolerep.findByRole(roleName);
			//user.getRole().add(role);
	}
	
	//------- get utilisateur by id :
	@Override
	public User getById(long id) {
		//Optional<User>user= 
		return userRepo.findById(id).orElseThrow( () ->
	                    	new UserNotFoundException("User by id " + id + " was not found."));
		//return user.isPresent()?user.get():null;
	}	
	//------- get all users :
	@Override
	public List<User>getAllUser(){
		return this.userRepo.findAll();
	}
	
	//------- Mise a jour User (UPDATE) :
	@Override
	 public User updateUser (Long id, User user) {
	        User oldUser = getById(id);
	        oldUser.setUsername(user.getUsername());
	        oldUser.setEmail(user.getEmail());
	        oldUser.setSpecialite(user.getSpecialite());
	     
	        return userRepo.save(oldUser);
	    }
	//--- supprimer un utilisateur :
	@Override
	public void  deleteUser(Long id) {
		this.userRepo.deleteById(id);
	}
	
	//--------Service de Log-in------------------------
	@Override
	public User getByInfo(String email,String password) {
		return this.userRepo.findByInfo(email,password);
	}
	
	//---- PDF ---- Code for the Service layer :
	@Override 
	 public List<User> listAll() {
	        return userRepo.findAll(Sort.by("email").ascending());
	    }
	
	

}