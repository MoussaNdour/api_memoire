package memoire.api.memoire_licence.services.classes;

import memoire.api.memoire_licence.dto.response.DemandeServiceResponseDTO;
import memoire.api.memoire_licence.dto.response.PrestataireResponseDTO;
import memoire.api.memoire_licence.dto.response.ServiceResponseDTO;
import memoire.api.memoire_licence.entities.Demandeservice;
import memoire.api.memoire_licence.entities.Prestataire;
import memoire.api.memoire_licence.entities.Proposer;
import memoire.api.memoire_licence.entities.Utilisateur;
import memoire.api.memoire_licence.mappers.DemandeServiceResponseMapper;
import memoire.api.memoire_licence.mappers.PrestataireResponseMapper;
import memoire.api.memoire_licence.mappers.ServiceResponseMapper;
import memoire.api.memoire_licence.repositories.*;
import memoire.api.memoire_licence.services.interfaces.MainServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class MainServices implements MainServicesInterface {

    @Autowired
    DemandeserviceRepository demanderepos;

    @Autowired
    DemandeServiceResponseMapper demandeResponseMapper;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    PrestataireRepository prestataireRepository;

    @Autowired
    PrestataireResponseMapper prestataireResponseMapper;

    @Autowired
    ProposerRepository proposerRepository;

    @Autowired
    ServiceResponseMapper serviceResponseMapper;

    @Autowired
    UtilisateurRepository userRepos;

    @Override
    public boolean proposerService(int idprestataire, int idservice, String description) {
        Prestataire prestataire=prestataireRepository.findById(idprestataire).orElse(null);
        memoire.api.memoire_licence.entities.Service service=serviceRepository.findById(idservice).orElse(null);

        if(prestataire !=null && service!=null){
            Proposer proposition=new Proposer();
            proposition.setPrestataire(prestataire);
            proposition.setService(service);
            proposition.setDescription_perso(description);


            proposerRepository.save(proposition);

            return true;
        }
        else
            return false;
    }

    @Override
    public List<memoire.api.memoire_licence.entities.Service> servicesProposees() {
        ArrayList<Proposer> propositions=new ArrayList<>();

        Iterator i=proposerRepository.findAll().iterator();

        while(i.hasNext()){
            propositions.add((Proposer) i.next());
        }

        ArrayList<memoire.api.memoire_licence.entities.Service> services=new ArrayList<>();

        for(Proposer proposition:propositions){
            memoire.api.memoire_licence.entities.Service service=proposition.getService();
            if(service!=null)
                services.add(service);
        }

        return services ;
    }

    @Override
    public List<PrestataireResponseDTO> findPrestatairesByService(int id) {
        List<PrestataireResponseDTO> prestataires=new ArrayList<>();

        for(Prestataire prestataire:proposerRepository.findPrestatairesByServiceId(id)){
            prestataires.add(prestataireResponseMapper.toDTO(prestataire));
        }

        return prestataires;
    }

    @Override
    public List<ServiceResponseDTO> rechercheParNom(String nom) {
        List<ServiceResponseDTO> services=new ArrayList<>();
        Iterator i=serviceRepository.findByNomContainingIgnoreCase(nom).iterator();
        while(i.hasNext()){
            services.add(serviceResponseMapper.toDTO((memoire.api.memoire_licence.entities.Service) i.next()));
        }
        return services;
    }

    @Override
    public void setUserFirebaseToken(String email, String token) {
        Utilisateur user=userRepos.findByEmail(email).orElse(null);

        user.setFirebase_token(token);

        userRepos.save(user);
    }

    @Override
    public List<ServiceResponseDTO> getServicesByPresta(String email) {
        List<Proposer> propositions=proposerRepository.findAllByPrestataireEmail(email);
        ArrayList<ServiceResponseDTO> services=new ArrayList<>();
        for(Proposer proposition:propositions){
            services.add(serviceResponseMapper.toDTO(proposition.getService()));
        }
        return services;
    }

    @Override
    public List<DemandeServiceResponseDTO> getDemandeByPresta(String email) {
        ArrayList<DemandeServiceResponseDTO> demandes=new ArrayList<>();
        for(Demandeservice demande:demanderepos.findAllByPrestataireEmail(email)){
            demandes.add(demandeResponseMapper.toDTO(demande));
        }
        return demandes;
    }
}
