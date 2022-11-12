package tn.enicar.DAO.entities;

import java.io.Serializable;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import tn.enicar.DAO.entities.enume.Specialite;


@Entity
@Table(name="T_SUJET") //Renommer le table 
public class Sujet implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long idSujet ;
			
	
	@Column(name="Titre",length=100, nullable=false)
	private String titre ;
	
	@Column(name="description",length=200, nullable=false)
	private String description ;
			
					
	@Enumerated(EnumType.STRING)
	Specialite specialite ;

	@Column(name="encadrant",length=50, nullable=false)
	private String encadrant ;
	
	@Column(name="email_encadrant",length=50, nullable=false)
	private String email_encadrant ;
	
	
	/*@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
   //  @JoinColumn(name = "choix_id")
    @JsonIgnore
     private ChoixItem choix;*/
	
	//Constructeur
	public Sujet() {  }
	public Sujet(String titre, String description, Specialite specialite, String encadrant, String email_encadrant) {
		super();
		this.titre = titre;
		this.description = description;
		this.specialite = specialite;
		this.encadrant = encadrant;
		this.email_encadrant = email_encadrant;
	}

   //getters & setters 
    public long getIdSujet() {return idSujet;}
	public void setIdSujet(long idSujet) {this.idSujet = idSujet;}
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public Specialite getSpecialite() {return specialite;}
	public void setSpecialite(Specialite specialite) {this.specialite = specialite;}
	public String getEncadrant() {return encadrant;}
	public void setEncadrant(String encadrant) {this.encadrant = encadrant;}	
	public String getEmail_encadrant() {return email_encadrant;}
	public void setEmail_encadrant(String email_encadrant) {this.email_encadrant = email_encadrant;}
	
	
	/*public ChoixItem getChoix() {
		return choix;
	}
	public void setChoix(ChoixItem choix) {
		this.choix = choix;
	}*/
	
	//tostring => methode pour afficher le contenue de Sujet
	@Override
	public String toString() {
		return "Sujet [idSujet=" + idSujet + ", titre=" + titre + ", description=" + description + ", specialite="
				+ specialite + ", encadrant=" + encadrant + ", email_encadrant=" + email_encadrant + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(description, email_encadrant, encadrant, idSujet, specialite, titre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sujet other = (Sujet) obj;
		return Objects.equals(description, other.description) && Objects.equals(email_encadrant, other.email_encadrant)
				&& Objects.equals(encadrant, other.encadrant) && idSujet == other.idSujet
				&& specialite == other.specialite && Objects.equals(titre, other.titre);
	}
					 					
}
