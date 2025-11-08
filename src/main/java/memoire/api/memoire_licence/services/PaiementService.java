package memoire.api.memoire_licence.services;

import memoire.api.memoire_licence.dto.response.PaiementDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PaiementService {
    public List<PaiementDTO> findAll() {
        return new ArrayList<>();
    }

    public PaiementDTO findById(int idpaiement) {
        return new PaiementDTO();
    }

    public boolean create(PaiementDTO paiementDTO) {
        return false;
    }

    public void save(int idpaiement, PaiementDTO paiementDTO) {
    }

    public boolean update(PaiementDTO paiementDTO) {
        return false;
    }

    public boolean partialUpdate(int idpaiement, PaiementDTO paiementDTO) {
        return false;
    }

    public boolean deleteById(int idpaiement) {
        return false;
    }
}
