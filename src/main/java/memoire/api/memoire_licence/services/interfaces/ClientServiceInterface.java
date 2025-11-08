package memoire.api.memoire_licence.services.interfaces;

import memoire.api.memoire_licence.dto.request.ClientRequestDTO;
import memoire.api.memoire_licence.dto.response.ClientResponseDTO;

import java.util.List;

public interface ClientServiceInterface {

    List<ClientResponseDTO> findAll();

    ClientResponseDTO findById(int idclient);

    void create(ClientRequestDTO clientDTO);

    void update(int idclient, ClientRequestDTO clientDTO);

    void deleteById(int idclient);

    boolean checkUserEmail(String email);

    ClientResponseDTO getClientByEmail(String email);
}
