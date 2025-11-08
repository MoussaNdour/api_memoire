package memoire.api.memoire_licence.mappers;

import memoire.api.memoire_licence.dto.response.ServiceResponseDTO;
import memoire.api.memoire_licence.entities.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceResponseMapper {

    @Mapping(source = "categorie.libelle", target = "categorie")
    @Mapping(source = "categorie.icone", target = "icone")
    ServiceResponseDTO toDTO(Service service);
}
