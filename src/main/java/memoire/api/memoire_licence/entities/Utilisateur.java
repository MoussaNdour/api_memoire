package memoire.api.memoire_licence.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * JPA entity class for "Utilisateur"
 *
 * @author Telosys
 *
 */
@Entity
@Table(name="utilisateur", schema="public", catalog="memoire_database" )
public class Utilisateur implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    //--- PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idutilisateur", nullable=false)
    private int        idutilisateur ;

    //--- OTHER DATA FIELDS
    @Column(name="nom", length=254)
    private String     nom ;

    @Column(name="prenom", length=254)
    private String     prenom ;

    @Column(name="telephone")
    private Long       telephone ;

    @Column(name="email", length=254)
    private String     email ;

    @Column(name="adresse", length=254)
    private String     adresse ;

    @Column(name="motdepasse", length=254)
    private String     motdepasse ;

    @Column(name="role")
    private String role;

    @Column(name="date_de_naissance")
    private Date date_de_naissance;

    @Column(name="actif")
    private boolean actif=true;

    @Column(name="firebase_token")
    private String firebase_token;

    /**
     * Constructor
     */
    public Utilisateur() {
		super();
    }

    public void setIdutilisateur( int idutilisateur ) {
        this.idutilisateur = idutilisateur ;
    }
    public int getIdutilisateur() {
        return this.idutilisateur;
    }

    public void setNom( String nom ) {
        this.nom = nom ;
    }
    public String getNom() {
        return this.nom;
    }

    public void setPrenom( String prenom ) {
        this.prenom = prenom ;
    }
    public String getPrenom() {
        return this.prenom;
    }

    public void setTelephone( Long telephone ) {
        this.telephone = telephone ;
    }
    public Long getTelephone() {
        return this.telephone;
    }

    public void setEmail( String email ) {
        this.email = email ;
    }
    public String getEmail() {
        return this.email;
    }

    public void setAdresse( String adresse ) {
        this.adresse = adresse ;
    }
    public String getAdresse() {
        return this.adresse;
    }

    public void setMotdepasse( String motdepasse ) {
        this.motdepasse = motdepasse ;
    }
    public String getMotdepasse() {
        return this.motdepasse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(Date date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public String getFirebase_token() {
        return firebase_token;
    }

    public void setFirebase_token(String firebase_token) {
        this.firebase_token = firebase_token;
    }

    @Override
	public String toString() {
		String separator = "|";
		StringBuilder sb = new StringBuilder();
		sb.append("Utilisateur[");
		sb.append("idutilisateur=").append(idutilisateur);
		sb.append(separator).append("nom=").append(nom);
		sb.append(separator).append("prenom=").append(prenom);
		sb.append(separator).append("telephone=").append(telephone);
		sb.append(separator).append("email=").append(email);
		sb.append(separator).append("adresse=").append(adresse);
		sb.append(separator).append("motdepasse=").append(motdepasse);
		sb.append("]");
		return sb.toString();
	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return motdepasse;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonLocked() {
        return actif;
    }
}
