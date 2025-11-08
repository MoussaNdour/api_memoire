package memoire.api.memoire_licence.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import memoire.api.memoire_licence.dto.request.ClientRequestDTO;
import memoire.api.memoire_licence.dto.response.ClientResponseDTO;
import memoire.api.memoire_licence.services.classes.ClientService;
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
@RequestMapping(value = "/api/v1/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientRestController {

	@Autowired
	private ClientService service;
	
	private Map<String,String> response=new HashMap<>();
    
	/**
	 * Get ALL
	 *
	 * @return
	 */
	@GetMapping("")
	public ResponseEntity<List<ClientResponseDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
    }
    
    /**
     * Get ONE identified by the given PK
	 * @param idclient
     * @return 200 or 404 
     */
    @GetMapping("/{idclient}")
    public ResponseEntity<?> findById(@PathVariable int idclient) {
    	ClientResponseDTO dto=service.findById(idclient);

		if(dto!=null){
			return ResponseEntity.ok(dto);
		}
		else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client non trouve pour cet id");
		}
    }

    
	/**
 	 * Create if doesn't exist 
	 *
	 * @param clientDTO
	 * @return 201 created or 409 conflict
	 */
	@PostMapping("")
	public ResponseEntity<?> create(@Valid @RequestBody ClientRequestDTO clientDTO) {
		if(service.checkUserEmail(clientDTO.getEmail()))
		{
			response.put("erreur:","Il existe deja un client avec ce mail");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		}
		else{
			service.create(clientDTO);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
	}

	/**
	 * Update or create
	 *
	 * @param idclient
	 * @param clientDTO
	 * @return 200 updated or created
	 */
	@PutMapping("/{idclient}")
	public ResponseEntity<?> update(@PathVariable int idclient, @Valid @RequestBody ClientRequestDTO clientDTO) {
		if(service.findById(idclient)==null){
			response.put("erreur:","Il n'existe aucun utilisateur avec cet id");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		else if(service.checkUserEmail(clientDTO.getEmail())){
			response.put("erreur:","Il existe deja un client avec ce mail");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		}
		else{
			service.update(idclient,clientDTO);
			return ResponseEntity.ok().build();
		}

	}



	/**
	 * Delete by PK 
	 *
	 * @param idclient
	 * @return 204 deleted or 404 not found
	 */
	@DeleteMapping("/{idclient}")
	public ResponseEntity<?> deleteById(@PathVariable int idclient) {
		if(service.findById(idclient)==null){
			response.put("erreur:","Il n'existe aucun utilisateur avec cet id");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		else{
			service.deleteById(idclient);
			return ResponseEntity.status(204).build();
		}
	}

	@GetMapping("/byEmail/{email}")
	public ResponseEntity<?> getClientByEmail(@PathVariable String email){
		ClientResponseDTO client=service.getClientByEmail(email);
		if(client==null){
			response.put("erreur:","Il n'existe aucun client avec ce mail");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		else{
			return ResponseEntity.ok(client);
		}
	}

}
