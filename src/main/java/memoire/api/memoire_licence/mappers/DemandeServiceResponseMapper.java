package memoire.api.memoire_licence.mappers;

import memoire.api.memoire_licence.dto.response.DemandeServiceResponseDTO;
import memoire.api.memoire_licence.entities.Demandeservice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DemandeServiceResponseMapper {


    @Mapping(source = "service.nom", target = "nomService")
    @Mapping(source = "client.utilisateur.prenom", target = "prenomClient")
    @Mapping(source = "client.utilisateur.nom", target = "nomClient")
    @Mapping(source = "paiement.statutpaiement", target = "statutpaiement")
    @Mapping(source = "prestataire.utilisateur.prenom", target = "prenomPrestataire")
    @Mapping(source = "prestataire.utilisateur.nom", target = "nomPrestataire")
    @Mapping(source = "contrat.duree", target = "duree")
    @Mapping(source = "client.metier", target = "metierClient")
    @Mapping(source = "prestataire.description", target = "descriptionPrestataire")
    @Mapping(source = "client.utilisateur.adresse", target = "adresseClient")
    DemandeServiceResponseDTO toDTO(Demandeservice demande);
}
