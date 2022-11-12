package tn.enicar.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.enicar.DAO.entities.Role;

@Repository 
public interface RoleRepository  extends JpaRepository<Role,Long>{
	
	Role findByRole(String role);

	
	
}