package tn.enicar.DAO.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import lombok.Data;
import tn.enicar.DAO.entities.choix.ChoixItem;
import tn.enicar.DAO.entities.enume.Specialite;

@Data
@Entity
@Table(name="T_USER")
public class User  implements Serializable{
	
    private static final long serialVersionUID =1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId ;
	
	@Column(name="username",length=40, nullable=false)
	private String username;
	
	@Column(name="email",length=90,nullable=false)
	private String email;
	
	@Column(name="password",length=100, nullable=false)
	private String password ;
	
	@Enumerated(EnumType.STRING)
	Specialite specialite ;

	@Column(name="role",length=100, nullable=false)
	private String role ;
	
	/*@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles ;
	*/
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_etudiants", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "etudiant_id"))
	private List<Etudiant> etudiants ; 
	
	
	//@JsonManagedReference
    @OneToMany(mappedBy = "pk.user", cascade = CascadeType.ALL)
    private List<ChoixItem> choixItems = new ArrayList<>();
	
	//Constructure 
	public User() {}
	public User(String username, String email, String password, Specialite specialite) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.specialite = specialite;	
		this.choixItems = new ArrayList<>();
	}
	/*public User(String username, String email, String password, Specialite specialite,ChoixItem choix) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.specialite = specialite;
		this.choix=choix ; 	
	}*/

	//getters & setters
	public long getUserId() {return userId;}
	public void setUserId(long userId) {this.userId = userId;}
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public Specialite getSpecialite() {return specialite;}
	public void setSpecialite(Specialite specialite) {this.specialite = specialite;}
	
	public List<ChoixItem> getChoixItems() {return choixItems;}
	public void setChoixItems(List<ChoixItem> choixItems) {this.choixItems = choixItems;}
	
	public List<Etudiant> getEtudiants() {return etudiants;}
	public void setEtudiants(List<Etudiant> etudiants) {this.etudiants = etudiants;}
	
	public String getRole() { return role;}
	public void setRole(String role) { this.role = role; }
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", specialite=" + specialite + ", roles=" + role + ", cartItems=" +  "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, password, role, specialite, userId, username);
	}
	@Override
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password)
				&& Objects.equals(role, other.role) && specialite == other.specialite && userId == other.userId
				&& Objects.equals(username, other.username);
	}

}