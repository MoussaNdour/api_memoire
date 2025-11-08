package memoire.api.memoire_licence.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;


@Entity
@Table(name="demandeservice")
public class Demandeservice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iddemande", nullable=false)
    private int  iddemande ;

    @Column(name="date_demande")
    private Date datededemande;

    @Column(name="statut", length=254)
    private String statut="en_attente" ;

    @Column(name="details_demande", length=254)
    private String detailsdemande ;

    @Column(name="daterendezvous")
    private Date daterendezvous;


    @OneToOne
    @JoinColumn(name="idpaiement", referencedColumnName="idpaiement")
    private Paiement   paiement;

    @OneToOne
    @JoinColumn(name="idcontrat", referencedColumnName="idcontrat")
    private Contrat    contrat;

    @OneToOne
    @JoinColumn(name="idclient", referencedColumnName ="idclient" )
    private Client client;

    @OneToOne(optional = true)
    @JoinColumn(name="idprestataire", referencedColumnName ="idprestataire" )
    private Prestataire prestataire;

    @OneToOne
    @JoinColumn(name="idservice", referencedColumnName = "idservice")
    private Service service;



    /**
     * Constructor
     */
    public Demandeservice() {
		super();
    }
    
    public void setIddemande( int iddemande ) {
        this.iddemande = iddemande ;
    }
    public int getIddemande() {
        return this.iddemande;
    }



    public void setDatededemande( Date datededemande ) {
        this.datededemande = datededemande ;
    }
    public Date getDatededemande() {
        return this.datededemande;
    }

    public void setStatut( String statut ) {
        this.statut = statut ;
    }
    public String getStatut() {
        return this.statut;
    }

    public void setDetailsdemande( String detailsdemande ) {
        this.detailsdemande = detailsdemande ;
    }
    public String getDetailsdemande() {
        return this.detailsdemande;
    }

    public Date getDaterendezvous() {
        return daterendezvous;
    }

    public void setDaterendezvous(Date daterendezvous) {
        this.daterendezvous = daterendezvous;
    }

    public Paiement getPaiement() {
        return this.paiement;
    } 

    public Contrat getContrat() {
        return this.contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Prestataire getPrestataire() {
        return prestataire;
    }

    public void setPrestataire(Prestataire prestataire) {
        this.prestataire = prestataire;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }


}
