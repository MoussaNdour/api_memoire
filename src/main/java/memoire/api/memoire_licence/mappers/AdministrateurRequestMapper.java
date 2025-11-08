package memoire.api.memoire_licence.mappers;

import memoire.api.memoire_licence.dto.request.AdministrateurRequestDTO;
import memoire.api.memoire_licence.entities.Administrateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AdministrateurRequestMapper {


    @Mapping(source = "email",target = "utilisateur.email")
    @Mapping(source = "motdepasse",target = "utilisateur.motdepasse")
    Administrateur toEntity(AdministrateurRequestDTO adminDTO);



}
