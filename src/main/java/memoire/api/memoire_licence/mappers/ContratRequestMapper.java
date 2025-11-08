package memoire.api.memoire_licence.mappers;

import memoire.api.memoire_licence.dto.request.ContratRequestDTO;
import memoire.api.memoire_licence.entities.Contrat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContratRequestMapper {

    Contrat toEntity(ContratRequestDTO contratDTO);

    ContratRequestDTO toDTO(Contrat contrat);
}
