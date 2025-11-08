package memoire.api.memoire_licence.mappers;


import memoire.api.memoire_licence.dto.request.ClientRequestDTO;
import memoire.api.memoire_licence.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientRequestMapper {

    @Mapping(source = "prenom",target = "utilisateur.prenom")
    @Mapping(source = "nom",target = "utilisateur.nom")
    @Mapping(source = "email",target = "utilisateur.email")
    @Mapping(source = "motdepasse",target = "utilisateur.motdepasse")
    @Mapping(source = "telephone",target = "utilisateur.telephone")
    @Mapping(source = "adresse",target = "utilisateur.adresse")
    @Mapping(source="date_de_naissance",target = "utilisateur.date_de_naissance")
    Client toEntity(ClientRequestDTO clientRequestDTO);
}
