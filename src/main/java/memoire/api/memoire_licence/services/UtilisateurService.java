package memoire.api.memoire_licence.services;

import jakarta.transaction.Transactional;
import memoire.api.memoire_licence.dto.response.UtilisateurDTO;
import memoire.api.memoire_licence.entities.Utilisateur;
import memoire.api.memoire_licence.mappers.UtilisateurMapper;
import memoire.api.memoire_licence.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    UtilisateurRepository repos;

    @Autowired
    UtilisateurMapper utilisateurMapper;

    @Autowired
    PasswordEncoder encoder;

    public List<UtilisateurDTO> findAll() {
        ArrayList<UtilisateurDTO> utilisateurs=new ArrayList<>();

        Iterator i=repos.findAll().iterator();
        while(i.hasNext()){
            utilisateurs.add(utilisateurMapper.toDTO((Utilisateur) i.next()));
        }

        return utilisateurs;
    }

    public UtilisateurDTO findByEmail(String email) {
        return utilisateurMapper.toDTO(repos.findByEmail(email).orElse(null));
    }

    public boolean create(UtilisateurDTO utilisateurDTO) {
        Object test=findByEmail(utilisateurDTO.getEmail());
        if(test!=null){
            return false;
        }
        else{
            Utilisateur utilisateur=utilisateurMapper.toEntity(utilisateurDTO);
            utilisateur.setMotdepasse(encoder.encode(utilisateur.getMotdepasse()));
            repos.save(utilisateur);
            return true;
        }
    }



    public boolean update(String email,UtilisateurDTO utilisateurDTO) {
            Utilisateur utilisateur=repos.findByEmail(email).orElse(null);
            if(utilisateur==null){
                return false;
            }
            else {
                repos.save(utilisateurMapper.toEntity(utilisateurDTO));
                return true;
            }
    }

    
    @Transactional
    public boolean deleteById(String email) {
        repos.deleteByEmail(email);
        return true;
    }
}
