package memoire.api.memoire_licence.mappers;


import memoire.api.memoire_licence.dto.response.AdministrateurResponseDTO;
import memoire.api.memoire_licence.entities.Administrateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AdministrateurResponseMapper {


    @Mapping(source = "utilisateur.email",target = "email")
    @Mapping(source = "utilisateur.motdepasse",target = "motdepasse")
    @Mapping(source = "utilisateur.role",target = "role")
    @Mapping(source = "utilisateur.actif",target = "actif")
    AdministrateurResponseDTO toDTO(Administrateur admin);
}
