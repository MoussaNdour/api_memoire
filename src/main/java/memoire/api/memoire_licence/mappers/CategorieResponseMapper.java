package memoire.api.memoire_licence.mappers;

import memoire.api.memoire_licence.dto.response.CategorieResponseDTO;
import memoire.api.memoire_licence.entities.Categorie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategorieResponseMapper {

    CategorieResponseDTO toDTO(Categorie categorie);
}
