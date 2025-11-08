package memoire.api.memoire_licence.mappers;

import memoire.api.memoire_licence.dto.request.RegisterUtilisateurDTO;
import memoire.api.memoire_licence.dto.response.UtilisateurDTO;
import memoire.api.memoire_licence.entities.Utilisateur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterUtilisateurMapper {
    UtilisateurDTO toDTO(Utilisateur utilisateur);

    Utilisateur toEntity(RegisterUtilisateurDTO registerUtilisateurDTO);
}
