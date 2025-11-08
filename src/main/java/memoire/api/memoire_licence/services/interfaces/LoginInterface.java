package memoire.api.memoire_licence.services.interfaces;


import memoire.api.memoire_licence.dto.request.LoginUtilisateurDTO;
import memoire.api.memoire_licence.entities.Utilisateur;

public interface LoginInterface {
    Utilisateur getUser(LoginUtilisateurDTO loginDTO);
}
