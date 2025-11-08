package memoire.api.memoire_licence.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class CategorieRequestDTO implements Serializable {

    @NotBlank(message = "la categorie ne peut pas etre nulle")
    @Size(min = 6,message = "la taille du libelle doit etre d'au moins 6 caracteres")
    private String libelle;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
