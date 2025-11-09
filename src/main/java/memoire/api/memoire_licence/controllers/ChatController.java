package memoire.api.memoire_licence.controllers;

import com.google.firebase.messaging.FirebaseMessagingException;

import jakarta.validation.Valid;
import memoire.api.memoire_licence.dto.request.MessageRequestDTO;
import memoire.api.memoire_licence.dto.response.MessageResponseDTO;
import memoire.api.memoire_licence.entities.Message;
import memoire.api.memoire_licence.services.classes.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/send")
    public ResponseEntity<MessageResponseDTO> envoyerMessage(@Valid @RequestBody MessageRequestDTO message) throws FirebaseMessagingException {
        return ResponseEntity.ok(chatService.envoyerMessage(message));
    }

    @GetMapping("/conversation/{user1}/{user2}")
    public ResponseEntity<List<Message>> getConversation(@PathVariable String user1, @PathVariable String user2) {
        return ResponseEntity.ok(chatService.getConversation(user1, user2));
    }
}
