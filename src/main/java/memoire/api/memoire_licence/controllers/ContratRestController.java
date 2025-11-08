package memoire.api.memoire_licence.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import memoire.api.memoire_licence.dto.request.ContratRequestDTO;
import memoire.api.memoire_licence.services.classes.ContratService;
import memoire.api.memoire_licence.dto.response.ContratResponseDTO;
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
@RequestMapping(value = "/api/v1/contrat", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContratRestController {


	@Autowired
	private ContratService service;

	private Map<String,String> response=new HashMap<>();

    
	/**
	 * Get ALL
	 *
	 * @return
	 */
	@GetMapping("")
	public ResponseEntity<List<ContratResponseDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
    }
    
    /**
     * Get ONE identified by the given PK
	 *
	 * @param idcontrat
     * @return 200 or 404 
     */
    @GetMapping("/{idcontrat}")
    public ResponseEntity<?> findById(@PathVariable int idcontrat) {
		ContratResponseDTO contrat=service.find(idcontrat);

		if(contrat==null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contrat non trouver pour cet id");
		}
		else{
			return ResponseEntity.ok(contrat);
		}
    }

    
	/**
 	 * Create if doesn't exist 
	 *
	 * @param contratDTO
	 * @return 201 created or 409 conflict
	 */
	@PostMapping("")
	public ResponseEntity<Void> create(@Valid @RequestBody ContratRequestDTO contratDTO) {
		service.save(contratDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	/**
	 * Update or create
	 *
	 * @param idcontrat
	 * @param contratDTO
	 * @return 200 updated or created
	 */
	@PutMapping("/{idcontrat}")
	public ResponseEntity<?> update(@PathVariable int idcontrat, @Valid @RequestBody ContratRequestDTO contratDTO) {
		if(service.find(idcontrat)==null){
			response.put("erreur:","Il n'existe pas de contrat avec cet id");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		else{
			service.update(idcontrat,contratDTO);
			return ResponseEntity.ok().build();
		}
	}



	/**
	 * Delete by PK 
	 *
	 * @param idcontrat
	 * @return 204 deleted or 404 not found
	 */
	@DeleteMapping("/{idcontrat}")
	public ResponseEntity<?> deleteById(@PathVariable int idcontrat) {
		if(service.find(idcontrat)==null){
			response.put("erreur:","Il n'existe pas de contrat avec cet id");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		else{
			service.delete(idcontrat);
			return ResponseEntity.status(204).build();
		}

	}

}
