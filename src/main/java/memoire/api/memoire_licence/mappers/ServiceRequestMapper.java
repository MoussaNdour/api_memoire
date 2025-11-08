package memoire.api.memoire_licence.mappers;

import memoire.api.memoire_licence.dto.request.ServiceRequestDTO;
import memoire.api.memoire_licence.entities.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceRequestMapper {

    @Mapping(source = "idcategorie", target="categorie.idcategorie")
    Service toEntity(ServiceRequestDTO serviceRequestDTO);

}
