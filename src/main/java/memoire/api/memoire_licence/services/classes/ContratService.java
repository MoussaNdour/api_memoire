package memoire.api.memoire_licence.services.classes;

import memoire.api.memoire_licence.dto.request.ContratRequestDTO;
import memoire.api.memoire_licence.dto.response.ContratResponseDTO;
import memoire.api.memoire_licence.entities.Contrat;
import memoire.api.memoire_licence.mappers.ContratRequestMapper;
import memoire.api.memoire_licence.mappers.ContratResponseMapper;
import memoire.api.memoire_licence.repositories.ContratRepository;
import memoire.api.memoire_licence.services.interfaces.ContratServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ContratService implements ContratServiceInterface {

    @Autowired
    ContratRepository repos;

    @Autowired
    ContratRequestMapper requestMapper;

    @Autowired
    ContratResponseMapper responseMapper;


    @Override
    public boolean save(ContratRequestDTO contratDTO) {
       Contrat contrat=repos.save(requestMapper.toEntity(contratDTO));

       return true;
    }

    @Override
    public List<ContratResponseDTO> findAll() {
        ArrayList<ContratResponseDTO> contrats=new ArrayList<>();
        Iterator i=repos.findAll().iterator();
        while(i.hasNext()){
            contrats.add(responseMapper.toDTO((Contrat) i.next()));
        }

        return contrats;
    }

    @Override
    public ContratResponseDTO find(int id) {
        Contrat contrat=repos.findById(id).orElse(null);

        if(contrat!=null){
            return responseMapper.toDTO(contrat);
        }
        else{
            return null;
        }
    }

    @Override
    public void delete(int id) {
        repos.deleteById(id);
    }

    @Override
    public void update(int id, ContratRequestDTO contratDTO) {

        Contrat contrat=repos.findById(id).orElse(null);
        Contrat updated=new Contrat();
        updated.setIdcontrat(contrat.getIdcontrat());
        updated.setDuree(contratDTO.getDuree());
        updated.setHoraires(contratDTO.getHoraires());
        updated.setJours(contratDTO.getJours());
        updated.setType_contrat(contrat.getType_contrat());
        updated.setMontantSalaire(contratDTO.getMontantSalaire());

        repos.save(updated);

    }
}
