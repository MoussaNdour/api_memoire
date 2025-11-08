package memoire.api.memoire_licence.services.interfaces;

import memoire.api.memoire_licence.dto.request.CategorieRequestDTO;
import memoire.api.memoire_licence.dto.response.CategorieResponseDTO;
import memoire.api.memoire_licence.entities.Categorie;

import java.util.List;

public interface CategorieServiceInterface {

    List<CategorieResponseDTO> findAll();

    CategorieResponseDTO findById(int idcategorie);

    void save(CategorieRequestDTO categorie);

    void update(int idcategorie, CategorieRequestDTO categorie);

    void deleteById(int idcategorie);

    boolean checkCategorieName(String libelle);
}
