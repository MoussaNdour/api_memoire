package memoire.api.memoire_licence.services.interfaces;

import memoire.api.memoire_licence.dto.request.ServiceRequestDTO;
import memoire.api.memoire_licence.dto.response.ServiceResponseDTO;
import memoire.api.memoire_licence.entities.Service;

import java.util.List;

public interface ServiceForServiceInterface {

    void create(ServiceRequestDTO serviceDTO);
    void delete(int id);
    List<ServiceResponseDTO> findAll();
    ServiceResponseDTO findById(int id);
    void update(int id, ServiceRequestDTO serviceDTO);
}
