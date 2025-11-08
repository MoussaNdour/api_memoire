package memoire.api.memoire_licence.controllers;



import jakarta.validation.Valid;
import memoire.api.memoire_licence.dto.request.LoginUtilisateurDTO;
import memoire.api.memoire_licence.dto.request.RegisterUtilisateurDTO;
import memoire.api.memoire_licence.dto.response.UtilisateurDTO;
import memoire.api.memoire_licence.entities.Utilisateur;
import memoire.api.memoire_licence.mappers.UtilisateurMapper;
import memoire.api.memoire_licence.services.JWTService;
import memoire.api.memoire_licence.services.interfaces.LoginInterface;
import memoire.api.memoire_licence.services.interfaces.RegistrationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("api/v1/public")
public class LoginAndRegisterController {

    @Autowired
    LoginInterface loginservice;

    @Autowired
    RegistrationInterface registrationservice;

    @Autowired
    JWTService jwtService;

    @Autowired
    UtilisateurMapper userMapper;

    @PostMapping("/register/client")
    public ResponseEntity<?> registerClient(@Valid @RequestBody RegisterUtilisateurDTO registerUser){
        UtilisateurDTO user=registrationservice.registerClient(registerUser);

        if(user==null)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Il existe deja un utilisateur avec ce mail");
        else
            return ResponseEntity.ok(user);

    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody RegisterUtilisateurDTO registerUser){
        UtilisateurDTO user=registrationservice.registerClient(registerUser);

        if(user==null)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Il existe deja un utilisateur avec ce mail");
        else
            return ResponseEntity.ok(user);

    }

    @PostMapping("/register/prestataire")
    public ResponseEntity<?> registerPrestataire(@Valid @RequestBody RegisterUtilisateurDTO registerUser){
        UtilisateurDTO user=registrationservice.registerPrestataire(registerUser);

        if(user==null)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Il existe deja un utilisateur avec ce mail");
        else
            return ResponseEntity.ok(user);
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> Login(@Valid @RequestBody LoginUtilisateurDTO user){
        Utilisateur utilisateur=loginservice.getUser(user);

        if (utilisateur==null){
            Map map=new HashMap();
            map.put("erreur:","Le nom d'utilisateur ou le mot de passe est incorrecte");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }
        else{
            UtilisateurDTO util=userMapper.toDTO(utilisateur);
            String token=jwtService.generateToken(utilisateur.getEmail());
            Map map=new HashMap();
            map.put("Token",token);
            map.put("utilisateur",util);

            return ResponseEntity.ok(map);
        }

    }

}