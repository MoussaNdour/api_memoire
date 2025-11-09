package memoire.api.memoire_licence.mappers;


import memoire.api.memoire_licence.dto.response.MessageResponseDTO;
import memoire.api.memoire_licence.entities.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageResponseMapper {

    MessageResponseDTO toDTO(Message message);
}
