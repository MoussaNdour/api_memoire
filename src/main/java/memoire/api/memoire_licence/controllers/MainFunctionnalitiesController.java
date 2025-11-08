package memoire.api.memoire_licence.controllers;


import memoire.api.memoire_licence.dto.response.PrestataireResponseDTO;
import memoire.api.memoire_licence.dto.response.ServiceResponseDTO;
import memoire.api.memoire_licence.entities.Prestataire;
import memoire.api.memoire_licence.entities.Service;
import memoire.api.memoire_licence.repositories.PrestataireRepository;
import memoire.api.memoire_licence.repositories.ServiceRepository;
import memoire.api.memoire_licence.repositories.UtilisateurRepository;
import memoire.api.memoire_licence.services.interfaces.MainServicesInterface;
import memoire.api.memoire_licence.services.interfaces.PrestataireServiceInterface;
import memoire.api.memoire_licence.services.interfaces.ServiceForServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/main")
public class MainFunctionnalitiesController {

    @Autowired
    MainServicesInterface service;

    @Autowired
    ServiceForServiceInterface serviceForService;

    @Autowired
    PrestataireServiceInterface prestataireService;

    @Autowired
    PrestataireRepository prestataireRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    UtilisateurRepository userRepos;

    @PostMapping("/proposerService/{idprestataire}/{idservice}/{description}")
    public ResponseEntity<?> proposerService(@PathVariable int idprestataire, @PathVariable int idservice, @PathVariable String description){
        Prestataire prestataire=prestataireRepository.findById(idprestataire).orElse(null);
        Service service1=serviceRepository.findById(idservice).orElse(null);

        Map<String,String> response=new HashMap<>();

        if(prestataire==null)
        {
            response.put("erreur","L'id du prestataire est invalide");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        if(service1==null){
            response.put("erreur","L'id du service est incorrecte");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }


        boolean test=service.proposerService(idprestataire,idservice,description);

        return ResponseEntity.ok().build();

    }

    @GetMapping("/servicesProposees")
    public ResponseEntity<List<Service>> servicesProposees(){
        return ResponseEntity.ok(service.servicesProposees());
    }

    @GetMapping("/services")
    public ResponseEntity<List<ServiceResponseDTO>> getServices(){
        return ResponseEntity.ok(serviceForService.findAll());
    }

    @GetMapping("/prestataires")
    public ResponseEntity<List<PrestataireResponseDTO>> getPrestataires(){
        return ResponseEntity.ok(prestataireService.findAll());
    }

    @GetMapping("/prestataireByService/{idservice}")
    public ResponseEntity<?> getPrestataireByService(@PathVariable int idservice){
        return ResponseEntity.ok(service.findPrestatairesByService(idservice));
    }

    @GetMapping("/rechercheService/{nom}")
    public ResponseEntity<?> rechercheService(@PathVariable String nom){
        return ResponseEntity.ok(service.rechercheParNom(nom));
    }

    @PostMapping("/firebaseToken/{email}/{token}")
    public ResponseEntity<?> setUserFirebaseToken(@PathVariable String email, @PathVariable String token){

        if(userRepos.findByEmail(email).orElse(null)==null){
            return ResponseEntity.notFound().build();
        }
        else if(token.isEmpty() || token.isBlank()){
            return ResponseEntity.badRequest().build();
        }
        else{
            service.setUserFirebaseToken(email,token);
            return ResponseEntity.ok("Token Firebase mis a jour");
        }
    }

    @GetMapping("/getServicesByPresta/{email}")
    public ResponseEntity<?> getServicesByPrestataire(@PathVariable String email){
        if(prestataireRepository.findByUtilisateurEmail(email).orElse(null)==null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(service.getServicesByPresta(email));
        }
    }

    @GetMapping("getDemandesByPresta/{email}")
    public ResponseEntity<?> getDemandesPresta(@PathVariable String email){
        if(prestataireRepository.findByUtilisateurEmail(email).orElse(null)==null){
            return ResponseEntity.notFound().build();
        }
        else{

            return ResponseEntity.ok(service.getDemandeByPresta(email));
        }
    }

//    public ResponseEntity<?> getDemandesClient(@PathVariable String emai){
//
//    }
}
