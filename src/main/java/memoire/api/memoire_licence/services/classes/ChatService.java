package memoire.api.memoire_licence.services.classes;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import memoire.api.memoire_licence.dto.request.MessageRequestDTO;
import memoire.api.memoire_licence.dto.response.MessageResponseDTO;
import memoire.api.memoire_licence.entities.Message;
import memoire.api.memoire_licence.entities.Utilisateur;
import memoire.api.memoire_licence.mappers.MessageRequestMapper;
import memoire.api.memoire_licence.mappers.MessageResponseMapper;
import memoire.api.memoire_licence.repositories.MessageRepository;
import memoire.api.memoire_licence.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private MessageRepository messageRepo;

    @Autowired
    private UtilisateurRepository userRepo;

    @Autowired
    private FirebaseMessaging fcm;

    @Autowired
    private MessageRequestMapper messageRequestMapper;

    @Autowired
    private MessageResponseMapper messageResponseMapper;

    public MessageResponseDTO envoyerMessage(MessageRequestDTO message) throws FirebaseMessagingException {
        // Sauvegarde en base
        Message saved = messageRepo.save(messageRequestMapper.toEntity(message));

        // Récupération du token du recepteur
        String token = message.getRecepteur();

        if (token != null && !token.isEmpty()) {
            // Construction du message FCM (data-only)
            com.google.firebase.messaging.Message fcmMsg = com.google.firebase.messaging.Message.builder()
                    .setToken(token)
                    .putData("type", "chat_message") // utile pour distinguer différents types de messages
                    .putData("expediteur", message.getExpediteur())
                    .putData("recepteur", message.getRecepteur())
                    .putData("contenu", message.getContenu())
                    .putData("date", saved.getDateEnvoi().toString())
                    .build();

            try {
                fcm.send(fcmMsg);
                System.out.println("✅ Message FCM envoyé à : " + token);
            } catch (FirebaseMessagingException e) {
                System.err.println("❌ Erreur FCM : " + e.getMessage());
            }
            
        } else {
            System.out.println("⚠️ Aucun token Firebase trouvé pour " + message.getRecepteur());
        }

        return messageResponseMapper.toDTO(saved);
    }

    public List<Message> getConversation(String user1, String user2) {
        return messageRepo.getConversation(user1, user2);
    }
}
