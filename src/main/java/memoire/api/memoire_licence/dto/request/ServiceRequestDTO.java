package memoire.api.memoire_licence.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class ServiceRequestDTO implements Serializable {

    @NotBlank(message = "Le nom du service doit être renseigné")
    @Size(min = 6,message = "Le nom d'un service doit etre au minimum de 6 caracteres")
    private String nom;

    @NotBlank(message = "La description du service doit être renseigné")
    @Size(min = 6,message = "La description d'un service doit etre au minimum de 6 caracteres")
    private String description;

    @NotNull(message = "Vous devez fournir l'id de la categorie")
    @Min(value = 1,message = "L'id de la categorie doit etre renseigner")
    private Integer idcategorie;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Integer idcategorie) {
        this.idcategorie = idcategorie;
    }
}
