package tn.enicar.DAO.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//nahla Excel 

@Entity 
public class Etudiant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nom;
	private String prenom;
	private String adr;
	private String specialite;
	private float score;
	
	
	//Constructeur
	public Etudiant() {}
	public Etudiant(long id, String nom, String prenom, String adr, String specialite, float score) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adr = adr;
		this.specialite = specialite;
		this.score = score;
	}
	//Getters & setters 
	public long getId() {return id;}
	public void setId(long id) {this.id = id;}
	
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	
	public String getAdr() {return adr;}
	public void setAdr(String adr) {this.adr = adr;}
	
	public String getSpecialite() {return specialite;}
	public void setSpecialite(String specialite) {this.specialite = specialite;}
	
	public float getScore() {return score;}
	public void setScore(float score) {this.score = score;}
	
	
	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adr=" + adr + ", specialite="
				+ specialite + ", score=" + score + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(adr, id, nom, prenom, score, specialite);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etudiant other = (Etudiant) obj;
		return Objects.equals(adr, other.adr) && id == other.id && Objects.equals(nom, other.nom)
				&& Objects.equals(prenom, other.prenom)
				&& Float.floatToIntBits(score) == Float.floatToIntBits(other.score)
				&& Objects.equals(specialite, other.specialite);
	}
 
}
