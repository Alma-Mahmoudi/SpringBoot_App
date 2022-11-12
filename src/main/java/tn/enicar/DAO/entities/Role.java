package tn.enicar.DAO.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//@Enumerated(EnumType.STRING)
	//ERole role;
	String role;

	public Role() {}
	public Role( String role) {this.role = role;}
	public String getRole() {return role;}
	public void setRole(String role) {this.role = role;}
	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + "]";
	}
}
