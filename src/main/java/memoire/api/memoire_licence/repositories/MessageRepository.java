package memoire.api.memoire_licence.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import memoire.api.memoire_licence.entities.Message;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message,Integer> {

    @Query("SELECT m FROM Message m WHERE (m.expediteur = :user1 AND m.recepteur = :user2) " +
            "OR (m.expediteur = :user2 AND m.recepteur = :user1) ORDER BY m.dateEnvoi ASC")
    List<Message> getConversation(@Param("user1") String user1, @Param("user2") String user2);
}
