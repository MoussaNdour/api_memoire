package memoire.api.memoire_licence.dto.request;

import jakarta.validation.constraints.*;

public class RegisterUtilisateurDTO {

    @NotBlank(message="Le nom de l'utilisateur ne peux pas etre nul")
    @Size(min = 2,message = "Le nom doit au moins etre de 2 characteres")
    private String nom ;

    @NotBlank(message = "Le prenom de l'utilisateur ne peux pas etre nul")
    @Size(min = 4,message = "Le prenom doit au moins etre de 6 characteres")
    private String prenom ;

    @Min(value = 700000000,message = "Le numero de telephone est invalide")
    @Digits(integer = 9,fraction = 0,message = "Le numero de telephone est invalide")
    private Long telephone ;

    @NotBlank(message = "Le mail doit etre renseigner")
    @Email(message = "Le mail est invalide")
    private String email ;

    @NotBlank(message = "L'adresse de l'utilisateur ne peux pas etre nul")
    @Size(min = 4,message = "L'adresse doit au moins etre de 6 characteres")
    private String adresse ;

    @NotBlank(message = "Le mot de passe doit etre renseigner")
    @Size(min = 8,message = "Le mot de passe doit au moins etre de 8 characteres")
    private String motdepasse ;


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
}
