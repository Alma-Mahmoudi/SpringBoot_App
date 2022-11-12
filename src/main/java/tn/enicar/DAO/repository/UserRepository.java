package tn.enicar.DAO.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.enicar.DAO.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
	
	Optional<User> findById(Long id); 
	
	void deleteById(Long id);	
	
	//Requete pour le login pour recuperer les informations de login (username+password)
	@Query("SELECT u FROM User u WHERE u.email= :email AND u.password= :password ")
	public User findByInfo(@Param(value = "email") String email,@Param(value = "password") String password);
	
	

}
