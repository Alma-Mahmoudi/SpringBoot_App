package tn.enicar.DAO.entities;

import java.util.Objects;

import javax.persistence.*;

import tn.enicar.DAO.entities.enume.Departement;

@Entity  
@Table(name="T_ENSEIGNANT") //Renommer le table  
public class Enseignant {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long idEns;
	//Renommer les champs
	@Column(name="Nom_PrénomEns",length=30, nullable=false)  
	private String name ;
	
	@Column(name="E_mailEns",length=30, nullable=false)
	private String emailEns ;

	@Enumerated(EnumType.STRING)
	Departement Departement ;

	//Constructeur
	public Enseignant() {}
	public Enseignant(String name, String emailEns,Departement departement) {
		super();
		this.name = name;
		this.emailEns = emailEns;
		Departement = departement;
	}
	
    //Getters & setters
	public long getIdEns() {return idEns;}
	public void setIdEns(long idEns) {this.idEns = idEns;}

	public String getName() {return name;}

	public void setName(String name) {this.name = name;}
	public String getEmailEns() {return emailEns;}

	public void setEmailEns(String emailEns) {this.emailEns = emailEns;}

	public Departement getDepartement() {return Departement;}
	public void setDepartement(Departement departement) {
		Departement = departement;
	}
	//tostring => methode pour afficher le contenue d'enseignant 
	@Override
	public String toString() {
		return "Enseignant [idEns=" + idEns + ", name=" + name + ", emailEns=" + emailEns + ", Departement="
				+ Departement + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(Departement, emailEns, idEns, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enseignant other = (Enseignant) obj;
		return Departement == other.Departement && Objects.equals(emailEns, other.emailEns) && idEns == other.idEns
				&& Objects.equals(name, other.name);
	}
	
}
