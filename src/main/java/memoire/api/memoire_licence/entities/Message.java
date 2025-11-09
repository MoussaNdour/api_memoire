package memoire.api.memoire_licence.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idmessage;

    @Column(name="contenu")
    private String contenu;

    @Column(name="dateEnvoi")
    private Date dateEnvoi = new Date();

    @Column(name="expediteur")
    private String expediteur;

    @Column(name="recepteur")
    private String recepteur;



    // Getters/setters


    public Integer getId() {
        return idmessage;
    }

    public void setId(Integer id) {
        this.idmessage = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }

    public String getRecepteur() {
        return recepteur;
    }

    public void setRecepteur(String recepteur) {
        this.recepteur = recepteur;
    }
}

