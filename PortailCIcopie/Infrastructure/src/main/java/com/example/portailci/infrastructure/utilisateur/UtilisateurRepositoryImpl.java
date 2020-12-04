package com.example.portailci.infrastructure.utilisateur;

import com.example.portailci.domain.exception.NotFoundException;
import com.example.portailci.domain.utilisateur.IRepositoryUtilisateur;
import com.example.portailci.domain.utilisateur.UtilisateurEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UtilisateurRepositoryImpl implements IRepositoryUtilisateur {

    private static Logger logger = LoggerFactory.getLogger(UtilisateurRepositoryImpl.class);

    @Autowired
    private IUtilisateurJpaRepository utilisateurJpaRepository;

    private static UtilisateurEntity utilisateurEntity;

    @Override
    public UtilisateurEntity findByUid(String uid) {

        logger.debug("Méthode findByUid - String uid = " + uid );

                logger.debug("Dans if fromRefog == false => Requête vers le JpaRepository");

                UtilisateurEntity utilisateur = utilisateurJpaRepository.findByUid(uid);
                logger.debug("Retour de la méthode findByUID du JpaRepository = " + utilisateur);
                if(utilisateur != null) {
                    return utilisateur;
                }
                else{
                    return null;
                }
        }


    @Override
    public UtilisateurEntity findById(Long id) throws NotFoundException{
        Optional<UtilisateurEntity> utilisateur = utilisateurJpaRepository.findById(id);
        if (utilisateur.isPresent()) {
            utilisateur.ifPresent(TheUtilisateur -> lambdaFindById(TheUtilisateur));
            return this.utilisateurEntity;
        }
        else {
            throw new NotFoundException("Aucun utilisateur portant l'id : " + id + " n'a été trouvé.");
        }
    }

    public static void lambdaFindById(UtilisateurEntity utiilisateur) {
        utilisateurEntity = utiilisateur;
    }

    @Override
    public List<UtilisateurEntity> findAll() {
        return utilisateurJpaRepository.findAll();
    }

    @Override
    public UtilisateurEntity create(UtilisateurEntity utilisateur) {

            return utilisateurJpaRepository.save(utilisateur);
    }

    @Override
    public UtilisateurEntity update(UtilisateurEntity utilisateur) {

        return utilisateurJpaRepository.save(utilisateur);
    }

    @Override
    public void delete(UtilisateurEntity utilisateur) {

            utilisateurJpaRepository.delete(utilisateur);
    }


}
