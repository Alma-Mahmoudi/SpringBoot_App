package tn.enicar.DAO.entities.choix;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import tn.enicar.DAO.entities.Sujet;
import tn.enicar.DAO.entities.User;

@Embeddable
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ChoixItemPK implements Serializable{
	
	private static final long serialVersionUID =1L;

	@JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sujet_id")
    private Sujet sujet;
    
    
    public ChoixItemPK() {}
	public ChoixItemPK(User user, Sujet sujet) {
		this.user = user;
		this.sujet = sujet;
	}
	//getters & setters 
	public User getUser() {return user;}
	public void setUser(User user) {this.user = user;}
	public Sujet getSujet() {return sujet;}
	public void setSujet(Sujet sujet) {this.sujet = sujet;}
	
	@Override
	public int hashCode() {
		return Objects.hash(sujet, user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChoixItemPK other = (ChoixItemPK) obj;
		return Objects.equals(sujet, other.sujet) && Objects.equals(user, other.user);
	}
	
}