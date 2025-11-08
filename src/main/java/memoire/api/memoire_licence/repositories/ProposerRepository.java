package memoire.api.memoire_licence.repositories;

import memoire.api.memoire_licence.entities.Prestataire;
import memoire.api.memoire_licence.entities.Proposer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProposerRepository extends CrudRepository<Proposer,Integer> {

    @Query("SELECT p.prestataire FROM Proposer p WHERE p.service.idservice = :idService")
    List<Prestataire> findPrestatairesByServiceId(@Param("idService") int idService);

    @Query("SELECT p FROM Proposer p WHERE p.prestataire.utilisateur.email = :email")
    List<Proposer> findAllByPrestataireEmail(@Param("email") String email);
}
