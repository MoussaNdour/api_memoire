package memoire.api.memoire_licence.services.interfaces;

import memoire.api.memoire_licence.dto.request.AdministrateurRequestDTO;
import memoire.api.memoire_licence.dto.response.AdministrateurResponseDTO;

import java.util.List;

public interface AdministrateurServiceInterface {

    List<AdministrateurResponseDTO> findAll();

    AdministrateurResponseDTO findById(int idadmin);

    void save(AdministrateurRequestDTO administrateurDTO);

    void update(int id,AdministrateurRequestDTO administrateurDTO);

    void deleteById(int idadmin);

    boolean checkUserEmail(String email);
}
