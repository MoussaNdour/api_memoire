package memoire.api.memoire_licence.services.classes;


import memoire.api.memoire_licence.dto.request.LoginUtilisateurDTO;
import memoire.api.memoire_licence.entities.Utilisateur;
import memoire.api.memoire_licence.repositories.UtilisateurRepository;
import memoire.api.memoire_licence.services.interfaces.LoginInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Login implements LoginInterface {

    @Autowired
    UtilisateurRepository repos;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public Utilisateur getUser(LoginUtilisateurDTO loginDTO) {
        Utilisateur utilisateur=repos.findByEmail(loginDTO.getEmail()).orElse(null);
        if(utilisateur==null)
            return null;
        else if (encoder.matches(loginDTO.getMot_de_passe(),utilisateur.getMotdepasse())) {
            return utilisateur;
        }
        else {
            return null;
        }
    }
}
