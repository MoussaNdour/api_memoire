package memoire.api.memoire_licence.mappers;

import memoire.api.memoire_licence.dto.request.PrestataireRequestDTO;
import memoire.api.memoire_licence.dto.response.PrestataireResponseDTO;
import memoire.api.memoire_licence.entities.Prestataire;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrestataireResponseMapper {

    @Mapping(source = "utilisateur.prenom",target = "prenom")
    @Mapping(source = "utilisateur.nom",target = "nom")
    @Mapping(source = "utilisateur.email",target = "email")
    @Mapping(source = "utilisateur.motdepasse",target = "motdepasse")
    @Mapping(source = "utilisateur.telephone",target = "telephone")
    @Mapping(source = "utilisateur.adresse",target = "adresse")
    @Mapping(source = "utilisateur.date_de_naissance",target = "date_de_naissance")
    @Mapping(source = "utilisateur.role", target = "role")
    @Mapping(source = "utilisateur.firebase_token", target = "firebase_token")
    PrestataireResponseDTO toDTO(Prestataire prestataire);


}
