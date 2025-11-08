package memoire.api.memoire_licence.dto.request;



import jakarta.validation.constraints.*;

import java.io.Serializable;

/**
 * DTO class for "Administrateur"
 *
 * @author Telosys
 *
 */
public class AdministrateurRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;



    @NotBlank(message = "Le mail doit être renseigné")
    @Email(message = "email invalide")
    private String email ;


    @NotBlank(message = "Le mot de passe doit etre renseigner")
    @Size(min=8,message = "Le mot doit etre au moins de 8 caracteres")
    private String motdepasse ;



    /**
     * Constructor
     */
    public AdministrateurRequestDTO() {
		super();
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }


    @Override
	public String toString() { 
		String separator = "|";
		StringBuilder sb = new StringBuilder();
		sb.append("Administrateur[");
		sb.append("]");
		return sb.toString();
	}
}
