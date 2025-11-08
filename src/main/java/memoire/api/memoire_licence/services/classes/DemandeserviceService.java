package memoire.api.memoire_licence.services.classes;

import jakarta.transaction.Transactional;
import memoire.api.memoire_licence.dto.request.DemandeServiceRequestDTO;
import memoire.api.memoire_licence.dto.response.DemandeServiceResponseDTO;
import memoire.api.memoire_licence.entities.Demandeservice;
import memoire.api.memoire_licence.mappers.DemandeServiceRequestMapper;
import memoire.api.memoire_licence.mappers.DemandeServiceResponseMapper;
import memoire.api.memoire_licence.repositories.*;
import memoire.api.memoire_licence.services.interfaces.DemandeserviceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class DemandeserviceService implements DemandeserviceServiceInterface {

    @Autowired
    DemandeserviceRepository repos;

    @Autowired
    DemandeServiceRequestMapper requestMapper;

    @Autowired
    DemandeServiceResponseMapper responseMapper;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    PrestataireRepository prestataireRepository;

    @Autowired
    ContratRepository contratRepository;

    @Transactional
    @Override
    public void save(DemandeServiceRequestDTO dto) {
        Demandeservice demande = requestMapper.toEntity(dto);

        demande.setClient(clientRepository.findById(dto.getIdclient()).orElse(null));
        demande.setService(serviceRepository.findById(dto.getIdservice()).orElse(null));

        if (dto.getIdprestataire() != null){
            demande.setPrestataire(prestataireRepository.findById(dto.getIdprestataire()).orElse(null));
            demande.setStatut("accepte");
        }


        if (dto.getIdcontrat() != null)
            demande.setContrat(contratRepository.findById(dto.getIdcontrat()).orElse(null));

//        Message msg = Message.builder()
//                .setTopic(topic)
//                .putData("body", "some data")
//                .build();
//        String id = fcm.send(msg);
//
//        Message msg = Message.builder()
//                .setToken(registrationToken)
//                .putData("body", "some data")
//                .build();



        repos.save(demande);
    }

    @Override
    public List<DemandeServiceResponseDTO> findAll() {
        ArrayList<DemandeServiceResponseDTO> demandes=new ArrayList<>();
        Iterator i=repos.findAll().iterator();
        while (i.hasNext()){
            demandes.add(responseMapper.toDTO((Demandeservice) i.next()));
        }

        return demandes;
    }

    @Transactional
    @Override
    public void update(int id, DemandeServiceRequestDTO dto) {
        Demandeservice demande=repos.findById(id).orElse(null);

        Demandeservice updated=new Demandeservice();
        updated=requestMapper.toEntity(dto);
        updated.setIddemande(id);

        repos.save(updated);

    }

    @Override
    public DemandeServiceResponseDTO find(int id) {
        Demandeservice demande=repos.findById(id).orElse(null);
        if(demande==null)
            return null;
        else{
            return responseMapper.toDTO(demande);
        }
    }

    @Transactional
    @Override
    public void delete(int id) {
        repos.deleteById(id);
    }

    @Override
    public List<DemandeServiceResponseDTO> servicesEnAttente() {

        List<DemandeServiceResponseDTO> demandes=new ArrayList<>();

        Iterator i=repos.findDemandesEnAttente().iterator();

        while(i.hasNext()){
            demandes.add(responseMapper.toDTO((Demandeservice) i.next()));
        }

        return demandes;
    }
}
