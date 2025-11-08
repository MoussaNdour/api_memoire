package memoire.api.memoire_licence.mappers;

import memoire.api.memoire_licence.dto.request.CategorieRequestDTO;
import memoire.api.memoire_licence.entities.Categorie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategorieRequestMapper {

    Categorie toEntity(CategorieRequestDTO dto);
}
