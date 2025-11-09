package memoire.api.memoire_licence.dto.request;

import jakarta.validation.constraints.NotBlank;

public class MessageRequestDTO {

    @NotBlank
    private String contenu;

    @NotBlank
    private String expediteur;

    @NotBlank
    private String recepteur;

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
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
