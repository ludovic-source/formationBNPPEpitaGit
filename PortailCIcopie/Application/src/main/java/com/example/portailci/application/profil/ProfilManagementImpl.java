package com.example.portailci.application.profil;

import com.example.portailci.domain.exception.AlreadyExistsException;
import com.example.portailci.domain.exception.NotFoundException;
import com.example.portailci.domain.profil.IRepositoryProfil;
import com.example.portailci.domain.profil.ProfilEntity;
import com.example.portailci.domain.utilisateur.IRepositoryUtilisateur;
import com.example.portailci.domain.utilisateur.UtilisateurEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ProfilManagementImpl implements IProfilManagement {

    @Autowired
    private IRepositoryProfil  repositoryProfil;

    @Autowired
    private IRepositoryUtilisateur repositoryUtilisateur;

    private static final Logger logger  = LoggerFactory.getLogger(ProfilManagementImpl.class);

    @Override
    public ProfilEntity create(ProfilEntity profil) {

        boolean profilAlreadyExists = checkIfProfilWithSimilarNameAlreadyExists(profil);
        if( profilAlreadyExists) {
            throw new AlreadyExistsException("Un profil ayant le nom \"" + profil.getNom() + "\" existe déjà.");
        } else {
            return repositoryProfil.create(profil);
        }
    }

    @Override
    public ProfilEntity findByID(Long id) {
        ProfilEntity profilEntity = repositoryProfil.findById(id);
        if(profilEntity != null) return profilEntity;
        else throw  new NotFoundException("Aucun profil ayant l'id : " + id + " n'a été trouvé.");
    }

    @Override
    public ProfilEntity findByNom(String nom) {

        Optional<ProfilEntity> profil = repositoryProfil.findByNom(nom);

        return profil.orElseThrow(() -> new NotFoundException("Aucun profil ayant le nom : " + nom + " n'a été trouvé."));

    }

    @Override
    public ProfilEntity findProfilOfConnectedUser() {

        // On récupère le contexte Spring Security
        SecurityContext context = SecurityContextHolder.getContext();

        // On récupère l'objet de type Authentication qui contient le nom d'utilisateur (éventuellement le mot de passe, ici non inséré) et les authorities (ici, les droits)
        Authentication authentication = context.getAuthentication();

        // On contrôle que l'utilisateur est bien authentifié
        if(authentication.isAuthenticated()) {

            // On récupère l'objet principal
            Object principal = authentication.getPrincipal();

            // On récupère le username (ici l'uid servant d'identifiant de connexion)
            final String uid = ((UserDetails) principal).getUsername();

            // On va rechercher l'utilisateur en base
            UtilisateurEntity utilisateur = repositoryUtilisateur.findByUid(uid);
            ProfilEntity profil = utilisateur.getProfil();

            return profil;
        }
        else throw new RuntimeException("Utilisateur non connecté");

    }

    @Override
    public void delete(Long id) {
        ProfilEntity profilASupprimer = repositoryProfil.findById(id);
        if(profilASupprimer != null) {
            if(profilASupprimer.getNom().toLowerCase() != "Ad")
            repositoryProfil.delete(profilASupprimer);
        }
        else throw new NotFoundException("Aucun profil ayant l'id : " + id + " n'a été trouvé.");
    }

    @Override
    public ProfilEntity update(ProfilEntity profil) {
        boolean profilAlreadyExists = checkIfProfilWithSimilarNameAlreadyExists(profil);
        if (profilAlreadyExists) {
            throw new AlreadyExistsException("Un profil ayant le nom \"" + profil.getNom() + "\" existe déjà.");
        } else {
            return repositoryProfil.update(profil);
        }
    }

    @Override
    public Set<ProfilEntity> findAll() {
        return repositoryProfil.findAll();
    }

    private boolean checkIfProfilWithSimilarNameAlreadyExists(ProfilEntity profil) {
        // On cherche si un profil existe déjà avec le même nom
        Optional<ProfilEntity> profilExistant = repositoryProfil.findByNom(profil.getNom());
        // si l'Optional contient un profil, on lève une exception
        if(profilExistant.isPresent()) {
            // si il s'agit du même id on return false pour ne pas lever l'exception
            if(profilExistant.get().getId() == profil.getId()) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
