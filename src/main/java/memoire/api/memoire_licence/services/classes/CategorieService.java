package memoire.api.memoire_licence.services.classes;

import memoire.api.memoire_licence.dto.request.CategorieRequestDTO;
import memoire.api.memoire_licence.dto.response.CategorieResponseDTO;
import memoire.api.memoire_licence.entities.Categorie;
import memoire.api.memoire_licence.mappers.CategorieRequestMapper;
import memoire.api.memoire_licence.mappers.CategorieResponseMapper;
import memoire.api.memoire_licence.repositories.CategorieRepository;
import memoire.api.memoire_licence.services.interfaces.CategorieServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategorieService implements CategorieServiceInterface {

    @Autowired
    CategorieRepository repos;

    @Autowired
    CategorieRequestMapper requestMapper;

    @Autowired
    CategorieResponseMapper responseMapper;

    @Override
    public List<CategorieResponseDTO> findAll() {
        ArrayList<CategorieResponseDTO> categories=new ArrayList();
        for(Categorie categorie:repos.findAll()){
            categories.add(responseMapper.toDTO(categorie));
        }
        return categories;
    }

    @Override
    public CategorieResponseDTO findById(int idcategorie) {
        Categorie categorie=repos.findById(idcategorie).orElse(null);
        if(categorie==null)
            return null;
        else
            return responseMapper.toDTO(categorie);
    }

    @Override
    public void save(CategorieRequestDTO categorie) {
        categorie.setLibelle(categorie.getLibelle());
        repos.save(requestMapper.toEntity(categorie));
    }

    @Override
    public void update(int idcategorie, CategorieRequestDTO categorie) {

        Categorie updated=new Categorie();
        updated.setIdcategorie(idcategorie);
        updated.setLibelle(categorie.getLibelle());
        repos.save(updated);

    }

    @Override
    public void deleteById(int idcategorie) {
        repos.deleteById(idcategorie);
    }

    @Override
    public boolean checkCategorieName(String libelle) {
        List<CategorieResponseDTO> categories=findAll();
        if(categories.size()==0)
            return false;
        else{
            for(CategorieResponseDTO categorie:categories){
                if(categorie.getLibelle().toLowerCase().equals(libelle.toLowerCase()))
                    return true;
            }
            return false;
        }

    }
}
