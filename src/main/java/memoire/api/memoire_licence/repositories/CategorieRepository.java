package memoire.api.memoire_licence.repositories;

import memoire.api.memoire_licence.entities.Categorie;
import org.springframework.data.repository.CrudRepository;

public interface CategorieRepository extends CrudRepository<Categorie,Integer> {
}
