package com.example.portailci.application.utilisateur;

import com.example.portailci.domain.exception.AlreadyExistsException;
import com.example.portailci.domain.exception.NotFoundException;
import com.example.portailci.domain.utilisateur.IRepositoryUtilisateur;
import com.example.portailci.domain.utilisateur.IUtilisateurRefogRepository;
import com.example.portailci.domain.utilisateur.UtilisateurEntity;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class UtilisateurManagementImpl implements IUtilisateurManagement {

    @Autowired
    private IRepositoryUtilisateur repositoryUtilisateur;

    @Autowired
    private IUtilisateurRefogRepository utilisateurRefogRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UtilisateurManagementImpl.class);

    @Override
    public UtilisateurEntity create(UtilisateurEntity utilisateurACreer) {

        try {

            UtilisateurEntity utilisateurDejaExistantEnBase;

            //On recherche si l'IUD existe déjà en base
            utilisateurDejaExistantEnBase = repositoryUtilisateur.findByUid(utilisateurACreer.getUid());

            // Si on reçoit un null, l'UID n'existe pas encore en base, on peut donc procéder à la création
            if( utilisateurDejaExistantEnBase == null) {
                // Le mot de passe est créé en y affectant la valeur de l'UID par défaut
                // On encode le mot de passe de l'utilisateurACreer et on lui affecte
                utilisateurACreer.setMotDePasse(passwordEncoder.encode(utilisateurACreer.getUid()));

                return repositoryUtilisateur.create(utilisateurACreer);
            }
            // Si l'UID existe, un utilisateurACreer nous est retourné, on lève alors une AlreadyExistsException
            else throw new Exception();
        }
        catch (Exception e) {
            logger.debug(" Exception levée dans la méthode create : " + e.getMessage());
            throw new AlreadyExistsException("L'IUD " + utilisateurACreer.getUid() + " existe déjà, merci de bien vouloir modifier l'utiliseur existant ou de changer l'UID à créer.");
        }
    }

    @Override
    public UtilisateurEntity findByUid(String utilisateurUid, boolean fromRefog) {

        logger.debug("Méthode findByUID - String utilisateurUid = " + utilisateurUid + " - boolean fromRefog = " + fromRefog );
        if(fromRefog == false){

            UtilisateurEntity utilisateurFromDatabase = repositoryUtilisateur.findByUid( utilisateurUid);
            logger.debug("Retour de la méthode findByUID du repositoryUtilisateur FROM DATABASE = " + utilisateurFromDatabase);

            if(utilisateurFromDatabase != null) return utilisateurFromDatabase;

            else throw new NotFoundException("Aucun utilisateur portant l'UID : " + utilisateurUid + " n'a été trouvé." );

        } else{
            UtilisateurEntity utilisateurFromRefog = utilisateurRefogRepository.getUtilisateurRefogVOByUid( utilisateurUid );
            logger.debug("Retour de la méthode findByUID du repositoryUtilisateur FROM REFOG = " + utilisateurFromRefog);
            if(utilisateurFromRefog != null) return utilisateurFromRefog;

            else throw new NotFoundException("Aucun collaborateur portant l'UID : " + utilisateurUid + " n'a été trouvé dans le REFOG." );
        }
    }

    @Override
    public UtilisateurEntity findByID(Long id) {
        return repositoryUtilisateur.findById(id);
    }

    @Override
    public void delete(Long id) {
        try{
            UtilisateurEntity utilisateurEntity = repositoryUtilisateur.findById(id);
            repositoryUtilisateur.delete(utilisateurEntity);
        }
        catch(Exception e) {
            throw new NotFoundException("Aucun utilisateur portant l'id : " + id + " n'a été trouvé. Suppression impossible");
        }
    }

    @Override
    public UtilisateurEntity update(UtilisateurEntity utilisateur) {
        try {
            UtilisateurEntity utilisateurEnBase = repositoryUtilisateur.findById(utilisateur.getId());
            utilisateur.setMotDePasse(utilisateurEnBase.getMotDePasse());
            return repositoryUtilisateur.update(utilisateur);
        } catch (Exception e) {
            throw new NotFoundException("L'utilisateur n'a pas été trouvé. Modification impossible");
        }

    }

    @Override
    public List<UtilisateurEntity> findAll() {

        return repositoryUtilisateur.findAll();
    }
}
