package memoire.api.memoire_licence.controllers;

import java.util.HashMap;
import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import memoire.api.memoire_licence.dto.request.DemandeServiceRequestDTO;
import memoire.api.memoire_licence.dto.response.DemandeServiceResponseDTO;
import memoire.api.memoire_licence.entities.Client;
import memoire.api.memoire_licence.entities.Contrat;
import memoire.api.memoire_licence.entities.Prestataire;
import memoire.api.memoire_licence.entities.Service;
import memoire.api.memoire_licence.repositories.ClientRepository;
import memoire.api.memoire_licence.repositories.ContratRepository;
import memoire.api.memoire_licence.repositories.PrestataireRepository;
import memoire.api.memoire_licence.repositories.ServiceRepository;
import memoire.api.memoire_licence.services.classes.DemandeserviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/api/v1/demandeservice", produces = MediaType.APPLICATION_JSON_VALUE)
@SecurityRequirement(name = "bearerAuth")
public class DemandeserviceRestController {


	@Autowired
	private DemandeserviceService service ;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private ContratRepository contratRepository;

	@Autowired
	private PrestataireRepository prestataireRepository;
	

	/**
	 * Get ALL
	 *
	 * @return
	 */
	@GetMapping("")
	public ResponseEntity<List<DemandeServiceResponseDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
    }
    
    /**
     * Get ONE identified by the given PK
	 *
	 * @param iddemande
     * @return 200 or 404 
     */
    @GetMapping("/{iddemande}")
    public ResponseEntity<?> findById(@PathVariable int iddemande) {
		DemandeServiceResponseDTO demande=service.find(iddemande);

		if(demande==null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else{
			return ResponseEntity.ok(demande);
		}
    }


	@PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody DemandeServiceRequestDTO dto) {

        HashMap<String, String> response = new HashMap<>();

        // Vérifications de base (communes à tous les cas)
        Client client = clientRepository.findById(dto.getIdclient()).orElse(null);
        if (client == null) {
            response.put("error", "L'id du client est incorrect");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Service service1 = serviceRepository.findById(dto.getIdservice()).orElse(null);
        if (service1 == null) {
            response.put("error", "L'id du service est incorrect");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Prestataire prestataire = null;
        if (dto.getIdprestataire() != null && dto.getIdprestataire() != 0) {
            prestataire = prestataireRepository.findById(dto.getIdprestataire()).orElse(null);
            if (prestataire == null) {
                response.put("error", "L'id du prestataire est incorrect");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }

        Contrat contrat = null;
        if (dto.getIdcontrat() != null && dto.getIdcontrat() != 0) {
            contrat = contratRepository.findById(dto.getIdcontrat()).orElse(null);
            if (contrat == null) {
                response.put("error", "L'id du contrat est incorrect");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }


        service.save(dto);

        response.put("message", "Demande de service créée avec succès");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    /**
	 * Update or create
	 *
	 * @param iddemande
	 * @param demandeserviceDTO
	 * @return 200 updated or created
	 */
	@PutMapping("/{iddemande}")
	public ResponseEntity<?> update(@PathVariable int iddemande, @Valid @RequestBody DemandeServiceRequestDTO demandeserviceDTO) {
		Client  client =clientRepository.findById(demandeserviceDTO.getIdclient()).orElse(null);
		Prestataire prestataire=prestataireRepository.findById(demandeserviceDTO.getIdprestataire()).orElse(null);
		Contrat contrat=contratRepository.findById(demandeserviceDTO.getIdcontrat()).orElse(null);
		Service service1=serviceRepository.findById(demandeserviceDTO.getIdservice()).orElse(null);

		HashMap<String,String> response=new HashMap<>();

		if(service.find(iddemande)==null){
			response.put("erreur:","Il n'existe aucune demande avec cet id");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		else{
			if(client==null){
				response.put("error","L'id du client est incorrect");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			if(prestataire==null){
				response.put("error","L'id du prestataire est incorrecte");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			if(contrat==null){
				response.put("error","L'id du contrat incorrecte");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			if(service1==null){
				response.put("error","L'id du service est incorrecte");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}

			service.update(iddemande,demandeserviceDTO);

			return ResponseEntity.ok().build();
		}
	}



	/**
	 * Delete by PK 
	 *
	 * @param iddemande
	 * @return 204 deleted or 404 not found
	 */
	@DeleteMapping("/{iddemande}")
	public ResponseEntity<?> deleteById(@PathVariable int iddemande) {
		HashMap<String,String> response=new HashMap<>();

		if(service.find(iddemande)==null){
			response.put("erreur:","Il n'existe aucune demande avec cet id");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		else{
			service.delete(iddemande);

			return ResponseEntity.ok().build();
		}
	}

	@GetMapping("/demandesEntAttente")
	public ResponseEntity<?> demandesEnAttente(){
		return ResponseEntity.ok(service.servicesEnAttente());
	}

}
