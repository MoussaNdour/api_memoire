package memoire.api.memoire_licence.mappers;

import memoire.api.memoire_licence.dto.request.DemandeServiceRequestDTO;
import memoire.api.memoire_licence.entities.Demandeservice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DemandeServiceRequestMapper {

    @Mapping(target = "iddemande", ignore = true)
    @Mapping(target = "statut", ignore = true)
    @Mapping(target = "paiement", ignore = true)
    @Mapping(target = "contrat", ignore = true)
    @Mapping(target = "prestataire", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "service", ignore = true)
    @Mapping(source = "daterendezvous", target = "daterendezvous")
    Demandeservice toEntity(DemandeServiceRequestDTO dto);

}
