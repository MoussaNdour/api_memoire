package memoire.api.memoire_licence.services.interfaces;

import memoire.api.memoire_licence.dto.request.PrestataireRequestDTO;
import memoire.api.memoire_licence.dto.response.PrestataireResponseDTO;

import java.util.List;

public interface PrestataireServiceInterface
{

    List<PrestataireResponseDTO> findAll();

    PrestataireResponseDTO findById(int idpresta);

    void create(PrestataireRequestDTO prestataireDTO);

    void update(int idpresta, PrestataireRequestDTO prestataireDTO);

    void deleteById(int idpresta);

    boolean checkUserEmail(String email);

    PrestataireResponseDTO getPrestataireByEmail(String email);
}
