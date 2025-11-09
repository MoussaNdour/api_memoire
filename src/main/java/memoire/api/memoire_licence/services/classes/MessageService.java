package memoire.api.memoire_licence.services.classes;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;


import com.google.firebase.messaging.Notification;

import memoire.api.memoire_licence.entities.Message;
import memoire.api.memoire_licence.entities.Utilisateur;
import memoire.api.memoire_licence.repositories.MessageRepository;
import memoire.api.memoire_licence.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private FirebaseMessaging firebaseMessaging;

    @Autowired
    private MessageRepository messageRepo;

    @Autowired
    private UtilisateurRepository utilisateurRepo;

    public Message envoyerMessage(Message message) throws FirebaseMessagingException {
        // 1️⃣ Sauvegarder le message
        Message saved = messageRepo.save(message);

        // 2️⃣ Récupérer le token du destinataire
        String token = utilisateurRepo.findByEmail(message.getRecepteur())
                .map(Utilisateur::getFirebase_token)
                .orElse(null);

        if (token != null) {
            // 3️⃣ Construire la notification
            Notification notification = Notification.builder()
                    .setTitle("Nouveau message de " + message.getExpediteur())
                    .setBody(message.getContenu())
                    .build();

            com.google.firebase.messaging.Message firebaseMsg = com.google.firebase.messaging.Message.builder()
                    .setToken(token)
                    .setNotification(notification)
                    .putData("expediteur", message.getExpediteur())
                    .putData("contenu", message.getContenu())
                    .build();

            // 4️⃣ Envoyer via Firebase
            firebaseMessaging.send(firebaseMsg);
        }

        return saved;
    }
}

