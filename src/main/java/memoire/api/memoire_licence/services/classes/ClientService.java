package memoire.api.memoire_licence.services.classes;


import jakarta.transaction.Transactional;
import memoire.api.memoire_licence.dto.request.ClientRequestDTO;
import memoire.api.memoire_licence.dto.response.ClientResponseDTO;
import memoire.api.memoire_licence.entities.Client;
import memoire.api.memoire_licence.entities.Utilisateur;
import memoire.api.memoire_licence.mappers.ClientRequestMapper;
import memoire.api.memoire_licence.mappers.ClientResponseMapper;
import memoire.api.memoire_licence.repositories.ClientRepository;
import memoire.api.memoire_licence.repositories.UtilisateurRepository;
import memoire.api.memoire_licence.services.interfaces.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ClientService implements ClientServiceInterface {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ClientRepository repos;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    ClientRequestMapper requestMapper;

    @Autowired
    ClientResponseMapper responseMapper;


    @Override
    public List<ClientResponseDTO> findAll() {
        ArrayList<ClientResponseDTO> clients=new ArrayList<>();
        Iterator i=repos.findAll().iterator();
        while(i.hasNext()){
            clients.add(responseMapper.toDTO((Client)i.next()));
        }

        return clients;
    }

    @Override
    public ClientResponseDTO findById(int idclient) {
        Client client=repos.findById(idclient).orElse(null);
        if(client!=null){
            return responseMapper.toDTO(client);
        }
        else{
            return null;
        }
    }

    @Transactional
    @Override
    public void create(ClientRequestDTO clientDTO) {

        Client client=requestMapper.toEntity(clientDTO);
        Utilisateur user=client.getUtilisateur();
        user.setRole("CLIENT");
        user.setMotdepasse(encoder.encode(user.getMotdepasse()));
        utilisateurRepository.save(user);
        repos.save(client);
    }

    @Transactional
    @Override
    public void update(int idclient, ClientRequestDTO clientDTO) {
        Client client=repos.findById(idclient).orElse(null);

        Client updated=requestMapper.toEntity(clientDTO);
        updated.setIdclient(idclient);

        Utilisateur userUpdated=updated.getUtilisateur();
        System.out.println(userUpdated.toString());
        userUpdated.setMotdepasse(encoder.encode(userUpdated.getMotdepasse()));
        userUpdated.setRole("CLIENT");
        userUpdated.setIdutilisateur(client.getUtilisateur().getIdutilisateur());
        updated.setUtilisateur(userUpdated);

        utilisateurRepository.save(userUpdated);
        repos.save(updated);
    }

    @Transactional
    @Override
    public void deleteById(int idclient) {
        Client client=repos.findById(idclient).orElse(null);
        repos.deleteById(idclient);
        utilisateurRepository.deleteByEmail(client.getUtilisateur().getEmail());
    }

    @Override
    public boolean checkUserEmail(String email) {
        if(utilisateurRepository.findByEmail(email).orElse(null)==null)
            return false;
        else
            return true;

    }

    @Override
    public ClientResponseDTO getClientByEmail(String email) {
        Client client=repos.findByUtilisateurEmail(email).orElse(null);

        if(client==null){
            return null;
        }
        else
        {
            return responseMapper.toDTO(client);
        }
    }
}
