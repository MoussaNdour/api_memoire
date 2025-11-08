package memoire.api.memoire_licence.services.classes;

import jakarta.transaction.Transactional;
import memoire.api.memoire_licence.dto.request.PrestataireRequestDTO;
import memoire.api.memoire_licence.dto.response.PrestataireResponseDTO;
import memoire.api.memoire_licence.entities.Prestataire;
import memoire.api.memoire_licence.entities.Utilisateur;
import memoire.api.memoire_licence.mappers.PrestataireRequestMapper;
import memoire.api.memoire_licence.mappers.PrestataireResponseMapper;
import memoire.api.memoire_licence.repositories.PrestataireRepository;
import memoire.api.memoire_licence.repositories.UtilisateurRepository;
import memoire.api.memoire_licence.services.interfaces.PrestataireServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PrestataireService implements PrestataireServiceInterface {

    @Autowired
    PrestataireRepository repos;

    @Autowired
    PrestataireRequestMapper requestMapper;

    @Autowired
    PrestataireResponseMapper responseMapper;

    @Autowired
    UtilisateurRepository utilisateurRepos;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public List<PrestataireResponseDTO> findAll() {
        ArrayList<PrestataireResponseDTO> prestataires=new ArrayList<>();
        Iterator i=repos.findAll().iterator();
        while (i.hasNext()){
            prestataires.add(responseMapper.toDTO((Prestataire) i.next()));
        }

        return prestataires;
    }

    @Override
    public PrestataireResponseDTO findById(int idpresta) {
        Prestataire presta=repos.findById(idpresta).orElse(null);
        if(presta==null){
            return null;
        }
        else
            return responseMapper.toDTO(presta);

    }

    @Transactional
    @Override
    public void create(PrestataireRequestDTO prestataireDTO) {

        Prestataire prestataire=requestMapper.toEntity(prestataireDTO);
        Utilisateur user=prestataire.getUtilisateur();
        user.setRole("PRESTATAIRE");
        user.setMotdepasse(encoder.encode(user.getMotdepasse()));
        utilisateurRepos.save(user);
        repos.save(prestataire);

    }

    @Transactional
    @Override
    public void update(int idpresta, PrestataireRequestDTO prestataireDTO) {
        Prestataire test=repos.findById(idpresta).orElse(null);

        Prestataire prestataire=requestMapper.toEntity(prestataireDTO);
        prestataire.setIdprestataire(idpresta);


        Utilisateur user=prestataire.getUtilisateur();
        user.setIdutilisateur(test.getUtilisateur().getIdutilisateur());
        user.setMotdepasse(encoder.encode(prestataire.getUtilisateur().getMotdepasse()));
        user.setRole("PRESTATAIRE");
        prestataire.setUtilisateur(user);

        utilisateurRepos.save(user);
        repos.save(prestataire);
    }

    @Transactional
    @Override
    public void deleteById(int idpresta) {
        Prestataire prestataire=repos.findById(idpresta).orElse(null);
        repos.deleteById(idpresta);
        utilisateurRepos.deleteByEmail(prestataire.getUtilisateur().getEmail());
    }

    @Override
    public boolean checkUserEmail(String email) {
        Utilisateur user=utilisateurRepos.findByEmail(email).orElse(null);
        if(user!=null)
            return true;
        else
            return false;
    }

    @Override
    public PrestataireResponseDTO getPrestataireByEmail(String email) {
        Prestataire prestataire=repos.findByUtilisateurEmail(email).orElse(null);

        if(prestataire==null)
            return null;
        else{
            return responseMapper.toDTO(prestataire);
        }

    }
}
