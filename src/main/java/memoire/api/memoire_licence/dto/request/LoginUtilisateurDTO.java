package memoire.api.memoire_licence.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginUtilisateurDTO {

    @NotBlank(message = "L'email doit etre renseigner")
    @Email(message = "Le mail est invalide")
    private String email;

    @NotNull(message = "Le mot de passe doit etre renseigner")
    private String mot_de_passe;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
}
