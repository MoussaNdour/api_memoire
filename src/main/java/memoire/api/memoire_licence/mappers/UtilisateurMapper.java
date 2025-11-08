package memoire.api.memoire_licence.mappers;

import memoire.api.memoire_licence.dto.response.UtilisateurDTO;
import memoire.api.memoire_licence.entities.Utilisateur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    UtilisateurDTO toDTO(Utilisateur utilisateur);

    Utilisateur toEntity(UtilisateurDTO utilisateurDTO);
}
