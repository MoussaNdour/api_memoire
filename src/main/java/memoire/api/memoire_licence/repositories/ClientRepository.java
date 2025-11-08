package memoire.api.memoire_licence.repositories;

import memoire.api.memoire_licence.entities.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface ClientRepository extends CrudRepository<Client, Integer> {

    Optional<Client> findByUtilisateurEmail(String email);
	// Insert specific finders here


    //List<Client> findByXxx(String xxx);

	//List<Client> findByXxxStartingWith(String xxx);

	//List<Client> findByXxxContaining(String xxx);

	//List<Client> findByYyy(BigDecimal yyy);

	//List<Client> findByXxxContainingAndYyy(String xxx, BigDecimal yyy);
}
