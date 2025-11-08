package memoire.api.memoire_licence.services.interfaces;

import memoire.api.memoire_licence.dto.request.ContratRequestDTO;
import memoire.api.memoire_licence.dto.response.ContratResponseDTO;
import java.util.List;

public interface ContratServiceInterface {

    boolean save(ContratRequestDTO contratDTO);

    List<ContratResponseDTO> findAll();

    ContratResponseDTO find(int id);

    void delete(int id);

    void update(int id, ContratRequestDTO contratDTO);
}
