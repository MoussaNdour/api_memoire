package memoire.api.memoire_licence.mappers;

import memoire.api.memoire_licence.dto.response.ContratResponseDTO;
import memoire.api.memoire_licence.entities.Contrat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContratResponseMapper {



    ContratResponseDTO toDTO(Contrat contrat);
}
