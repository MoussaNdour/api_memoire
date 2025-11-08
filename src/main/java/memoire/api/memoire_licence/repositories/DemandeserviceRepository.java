package memoire.api.memoire_licence.repositories;

import memoire.api.memoire_licence.entities.Demandeservice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DemandeserviceRepository extends CrudRepository<Demandeservice, Integer> {

    @Query("SELECT d FROM Demandeservice d WHERE d.prestataire IS NULL AND d.statut = 'en_attente'")
    List<Demandeservice> findDemandesEnAttente();

    // 🔹 1. Récupérer toutes les demandes d’un prestataire via son email utilisateur
    @Query("SELECT d FROM Demandeservice d WHERE d.prestataire.utilisateur.email = :email")
    List<Demandeservice> findAllByPrestataireEmail(@Param("email") String email);

    // 🔹 2. Récupérer toutes les demandes d’un client via son email utilisateur
    @Query("SELECT d FROM Demandeservice d WHERE d.client.utilisateur.email = :email")
    List<Demandeservice> findAllByClientEmail(@Param("email") String email);

    // Insert specific finders here

	//List<Demandeservice> findByXxx(String xxx);

	//List<Demandeservice> findByXxxStartingWith(String xxx);

	//List<Demandeservice> findByXxxContaining(String xxx);

	//List<Demandeservice> findByYyy(BigDecimal yyy);

	//List<Demandeservice> findByXxxContainingAndYyy(String xxx, BigDecimal yyy);
}
