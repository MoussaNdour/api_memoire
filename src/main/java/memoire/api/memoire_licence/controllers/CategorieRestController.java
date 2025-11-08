package memoire.api.memoire_licence.controllers;

import jakarta.validation.Valid;
import memoire.api.memoire_licence.dto.request.CategorieRequestDTO;
import memoire.api.memoire_licence.dto.response.CategorieResponseDTO;
import memoire.api.memoire_licence.services.interfaces.CategorieServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/categorie")
public class CategorieRestController {

    @Autowired
    CategorieServiceInterface service;

    private Map<String,String> response=new HashMap<>();


    @GetMapping("")
    public ResponseEntity<List<CategorieResponseDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{idcategorie}")
    public ResponseEntity<CategorieResponseDTO> findById(@PathVariable int idcategorie){
        CategorieResponseDTO categorie=service.findById(idcategorie);
        if(categorie==null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(categorie);
    }


    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody CategorieRequestDTO requestDTO){
        if(service.checkCategorieName(requestDTO.getLibelle()))
        {
            response.put("erreur:","Cette categorie existe deja");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        else{
            service.save(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @PutMapping("/{idcategorie}")
    public ResponseEntity<?> update(@PathVariable int idcategorie, @Valid @RequestBody CategorieRequestDTO requestDTO){
        if(service.findById(idcategorie)==null){
            response.put("erreur:","Il n'existe aucune categorie avec cet id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        else if(service.checkCategorieName(requestDTO.getLibelle())){
            response.put("erreur:","Cette categorie existe deja");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        else{
            service.update(idcategorie,requestDTO);
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/{idcategorie}")
    public ResponseEntity<?> delete(@PathVariable int idcategorie){
        if(service.findById(idcategorie)==null){
            response.put("erreur:","Il n'existe aucune categorie avec cet id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        else{
            service.deleteById(idcategorie);
            return ResponseEntity.status(204).build();
        }
    }
}
