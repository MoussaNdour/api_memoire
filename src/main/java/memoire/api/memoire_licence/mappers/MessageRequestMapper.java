package memoire.api.memoire_licence.mappers;


import memoire.api.memoire_licence.entities.Message;
import memoire.api.memoire_licence.dto.request.MessageRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageRequestMapper {

    Message toEntity(MessageRequestDTO message);
}
