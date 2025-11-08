package memoire.api.memoire_licence.entities;

import jakarta.persistence.*;

@Entity
@Table(name="proposer")
public class Proposer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idproposition")
    private int idproposition;

    @Column(name="tarif_horaire")
    private double tarif_horaire;

    @Column(name="description_perso")
    private String description_perso;

    @OneToOne
    @JoinColumn(name="idprestataire",referencedColumnName = "idprestataire")
    Prestataire prestataire;

    @OneToOne
    @JoinColumn(name="idservice",referencedColumnName = "idservice")
    Service service;

    public int getIdproposition() {
        return idproposition;
    }

    public void setIdproposition(int idproposition) {
        this.idproposition = idproposition;
    }

    public double getTarif_horaire() {
        return tarif_horaire;
    }

    public void setTarif_horaire(double tarif_horaire) {
        this.tarif_horaire = tarif_horaire;
    }

    public String getDescription_perso() {
        return description_perso;
    }

    public void setDescription_perso(String description_perso) {
        this.description_perso = description_perso;
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
