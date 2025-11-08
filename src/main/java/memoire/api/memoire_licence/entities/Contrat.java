package memoire.api.memoire_licence.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.*;

/**
 * JPA entity class for "Contrat"
 *
 * @author Telosys
 *
 */
@Entity
@Table(name="contrat", schema="public", catalog="memoire_database" )
public class Contrat implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- PRIMARY KEY 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcontrat", nullable=false)
    private int idcontrat ;

    //--- OTHER DATA FIELDS 
    @Column(name="horaires", length=254)
    private String horaires ;

    @Column(name="jours_travail", length=254)
    private String jours ;

    @Column(name="montant_salaire")
    private BigDecimal montantSalaire ;

    @Column(name="duree_jours")
    private int duree ;

    @Column(name="statut")
    private String statut="actif";

    @Column(name="type_contrat")
    private String type_contrat="ponctuel";

    /**
     * Constructor
     */
    public Contrat() {
		super();
    }
    
    public void setIdcontrat( int idcontrat ) {
        this.idcontrat = idcontrat ;
    }
    public int getIdcontrat() {
        return this.idcontrat;
    }

    public void setHoraires( String horaires ) {
        this.horaires = horaires ;
    }
    public String getHoraires() {
        return this.horaires;
    }

    public void setJours( String jours ) {
        this.jours = jours ;
    }
    public String getJours() {
        return this.jours;
    }

    public void setMontantSalaire( BigDecimal montantSalaire ) {
        this.montantSalaire = montantSalaire ;
    }
    public BigDecimal getMontantSalaire() {
        return this.montantSalaire;
    }

    public void setDuree( int duree ) {
        this.duree = duree ;
    }
    public int getDuree() {
        return this.duree;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getType_contrat() {
        return type_contrat;
    }

    public void setType_contrat(String type_contrat) {
        this.type_contrat = type_contrat;
    }

    @Override
	public String toString() { 
		String separator = "|";
		StringBuilder sb = new StringBuilder();
		sb.append("Contrat[");
		sb.append("horaires=").append(horaires);
		sb.append(separator).append("jours=").append(jours);
		sb.append(separator).append("montantSalaire=").append(montantSalaire);
		sb.append(separator).append("duree=").append(duree);
		sb.append(separator).append("idcontrat=").append(idcontrat);
		sb.append("]");
		return sb.toString();
	}

}
