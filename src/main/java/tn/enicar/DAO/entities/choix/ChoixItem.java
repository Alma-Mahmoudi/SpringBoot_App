package tn.enicar.DAO.entities.choix;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.enicar.DAO.entities.Sujet;
import tn.enicar.DAO.entities.User;


@Entity
@Table(name="T_Choix")
public class ChoixItem {
	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdChoix;*/

	@EmbeddedId
    @JsonIgnore
    private ChoixItemPK pk;
	
	//@Column(nullable = false)
	private String nomEtudiant1 ;
	
    //@Column(nullable = false)
    private int quantity = 1;
    
    public ChoixItem() {}
	public ChoixItem(User user,Sujet sujet, int quantity) {
		pk=new ChoixItemPK() ; 
		pk.setUser(user);
		pk.setSujet(sujet);
		this.quantity = quantity;
	}
	public ChoixItem(User user,Sujet sujet, int quantity, String nomEtudiant1) {
		pk=new ChoixItemPK() ; 
		pk.setUser(user);
		pk.setSujet(sujet);
		this.quantity = quantity;
		this.nomEtudiant1 = nomEtudiant1;
	}
	//-------------
	
	@Transient
	public Sujet getSujet () {return pk.getSujet();}
	
	public ChoixItemPK getPk() { return pk;}
	
	public void setPk(ChoixItemPK pk) {this.pk = pk;}
	
	public String getNomEtudiant1() {return nomEtudiant1;}
	public void setNomEtudiant1(String nomEtudiant1) {this.nomEtudiant1 = nomEtudiant1;}
	public int getQuantity() {return quantity;}
	public void setQuantity(int quantity) {this.quantity = quantity;}
	
	
	 /*@Override
	 public boolean equals(Object o) {
	        if (this == o) return true;

	        if (o == null || getClass() != o.getClass())
	            return false;

	        ChoixItem that = (ChoixItem) o;
	        return Objects.equals(pk.getUser().getUserId(), that.pk.getUser().getUserId()) &&
	                Objects.equals(getSujet().getIdSujet(), that.getSujet().getIdSujet());
	    }*/
}
	

	
//-------------------------------------------------------------------------

   /* private static final long serialVersionUID =1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long IdChoix ;
	
	
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnore
//    @JoinColumn(name = "email", referencedColumnName = "email")
    private User user;
	
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true,
            mappedBy = "choix")
    private Set<Sujet> sujets = new HashSet<>();

	
	public Choix() {}
	public Choix(User user) {this.user=user;}
	
	@Override
    public String toString() {
        return "Choix{" +
                "ChoixId=" + IdChoix +
                ", Sujets=" + sujets +
                '}';
    }*/
	
	
	