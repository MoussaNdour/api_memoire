package memoire.api.memoire_licence.services.interfaces;

import memoire.api.memoire_licence.dto.request.DemandeServiceRequestDTO;
import memoire.api.memoire_licence.dto.response.DemandeServiceResponseDTO;

import java.util.List;

public interface DemandeserviceServiceInterface {
    void save(DemandeServiceRequestDTO dto);

    List<DemandeServiceResponseDTO> findAll();

    void update(int id, DemandeServiceRequestDTO dto);

    DemandeServiceResponseDTO find(int id);

    void delete(int id);

    List<DemandeServiceResponseDTO> servicesEnAttente();
}
