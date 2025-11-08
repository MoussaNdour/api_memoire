package memoire.api.memoire_licence.services.classes;

import jakarta.transaction.Transactional;
import memoire.api.memoire_licence.dto.request.RegisterUtilisateurDTO;
import memoire.api.memoire_licence.dto.response.UtilisateurDTO;
import memoire.api.memoire_licence.entities.Administrateur;
import memoire.api.memoire_licence.entities.Client;
import memoire.api.memoire_licence.entities.Prestataire;
import memoire.api.memoire_licence.entities.Utilisateur;
import memoire.api.memoire_licence.mappers.*;
import memoire.api.memoire_licence.repositories.AdministrateurRepository;
import memoire.api.memoire_licence.repositories.ClientRepository;
import memoire.api.memoire_licence.repositories.PrestataireRepository;
import memoire.api.memoire_licence.repositories.UtilisateurRepository;
import memoire.api.memoire_licence.services.interfaces.RegistrationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Registration implements RegistrationInterface {

    @Autowired
    UtilisateurRepository utilisateurRepos;
    
    @Autowired
    ClientRepository clientRepos;

    @Autowired
    AdministrateurRepository adminrepos;

    @Autowired
    PrestataireRepository prestataireRepos;

    @Autowired
    UtilisateurMapper utilisateurMapper;

    
    @Autowired
    RegisterUtilisateurMapper registermapper;

    @Autowired
    PasswordEncoder encoder;
    

    @Override
    @Transactional
    public UtilisateurDTO registerClient(RegisterUtilisateurDTO dto) {
        Utilisateur test=utilisateurRepos.findByEmail(dto.getEmail()).orElse(null);
        if(test!=null){
            return null;
        }
        else{
            Utilisateur utilisateur=registermapper.toEntity(dto);
            utilisateur.setRole("CLIENT");
            utilisateur.setMotdepasse(encoder.encode(utilisateur.getMotdepasse()));
            Utilisateur saved=utilisateurRepos.save(utilisateur);
            Client client=new Client();
            client.setUtilisateur(saved);

            clientRepos.save(client);


            return utilisateurMapper.toDTO(utilisateur);
        }

    }

    @Override
    @Transactional
    public UtilisateurDTO registerAdmin(RegisterUtilisateurDTO dto) {
        Utilisateur test=utilisateurRepos.findByEmail(dto.getEmail()).orElse(null);
        if(test!=null){
            return null;
        }
        else{
            Utilisateur utilisateur=registermapper.toEntity(dto);
            utilisateur.setRole("ADMIN");
            utilisateur.setMotdepasse(encoder.encode(utilisateur.getMotdepasse()));
            Utilisateur saved=utilisateurRepos.save(utilisateur);

            Administrateur admin=new Administrateur();
            admin.setUtilisateur(saved);
            adminrepos.save(admin);

            return utilisateurMapper.toDTO(utilisateur);
        }

    }

    @Override
    @Transactional
    public UtilisateurDTO registerPrestataire(RegisterUtilisateurDTO dto) {
        Utilisateur test=utilisateurRepos.findByEmail(dto.getEmail()).orElse(null);
        if(test!=null){
            return null;
        }
        else{
            Utilisateur utilisateur=registermapper.toEntity(dto);
            utilisateur.setRole("PRESTATAIRE");
            utilisateur.setMotdepasse(encoder.encode(utilisateur.getMotdepasse()));
            Utilisateur saved=utilisateurRepos.save(utilisateur);

            Prestataire prestataire=new Prestataire();
            prestataire.setUtilisateur(saved);

            prestataireRepos.save(prestataire);

            return utilisateurMapper.toDTO(utilisateur);
        }

    }
}
