package memoire.api.memoire_licence.services.interfaces;

import memoire.api.memoire_licence.dto.request.RegisterUtilisateurDTO;
import memoire.api.memoire_licence.dto.response.UtilisateurDTO;


public interface RegistrationInterface {

    UtilisateurDTO registerClient(RegisterUtilisateurDTO dto);

    UtilisateurDTO registerAdmin(RegisterUtilisateurDTO dto);

    UtilisateurDTO registerPrestataire(RegisterUtilisateurDTO dto);
}
