package com.example.portailci.exposition.utilisateur;

import com.example.portailci.exposition.commun.AbstractMapper;
import com.example.portailci.domain.utilisateur.UtilisateurEntity;
import com.example.portailci.exposition.profil.ProfilMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class UtilisateurMapper extends AbstractMapper<UtilisateurDTO, UtilisateurEntity> {

    @Autowired
    private ProfilMapper profilMapper;

    private static final Logger LOG = LoggerFactory.getLogger(UtilisateurMapper.class);

    /**
     *
     * @param entity UtilisateurEntity présente dans la couche Domain
     * @return UtilisateurDTO
     */
    @Override
    public UtilisateurDTO mapToDto(@Valid UtilisateurEntity entity) {

        LOG.debug("En entrée : " + entity.toString());

        final UtilisateurDTO utilisateurDTO = new UtilisateurDTO();

        utilisateurDTO.setId(entity.getId());
        utilisateurDTO.setUid(entity.getUid());
        utilisateurDTO.setNom(entity.getNom());
        utilisateurDTO.setPrenom(entity.getPrenom());
        utilisateurDTO.setUoAffectation(entity.getUoAffectation());
        utilisateurDTO.setSiteExercice(entity.getSiteExercice());
        utilisateurDTO.setFonction(entity.getFonction());
        utilisateurDTO.setProfil(profilMapper.mapToDto(entity.getProfil()));

        LOG.debug("En sortie : " + utilisateurDTO.toString());
        return utilisateurDTO;
    }

    public UtilisateurRefogDTO mapToRefogDto(@Valid UtilisateurEntity entity) {

        LOG.debug("En entrée : " + entity.toString());

        final UtilisateurRefogDTO utilisateurDTO = new UtilisateurRefogDTO();


        utilisateurDTO.setUid(entity.getUid());
        utilisateurDTO.setNom(entity.getNom());
        utilisateurDTO.setPrenom(entity.getPrenom());

        utilisateurDTO.setUoAffectation(entity.getUoAffectation());
        utilisateurDTO.setSiteExercice(entity.getSiteExercice());
        utilisateurDTO.setFonction(entity.getFonction());


        LOG.debug("En sortie : " + utilisateurDTO.toString());
        return utilisateurDTO;
    }


    /**
     *
     * @param dto UtilisateurDTO en retour du client
     * @return UtilisateurEntity
     */
    @Override
    public UtilisateurEntity mapToEntity(@Valid UtilisateurDTO dto) {

        final UtilisateurEntity entity = new UtilisateurEntity();

        LOG.debug("En entrée : " + dto.toString());

        if(dto.getId() != null){
            entity.setId(dto.getId());
        }
        entity.setUid(dto.getUid());
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setUoAffectation(dto.getUoAffectation());
        entity.setSiteExercice(dto.getSiteExercice());
        entity.setFonction(dto.getFonction());
        entity.setProfil(profilMapper.mapToEntity(dto.getProfil()));

        LOG.debug("En sortie : " + entity.toString());

        return entity;
    }


    public UtilisateurEntity mapToEntity (@Valid UtilisateurRefogDTO dto) {

        final UtilisateurEntity entity = new UtilisateurEntity();

        LOG.debug("En entrée : " + dto.toString());

        entity.setUid(dto.getUid());
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setUoAffectation(dto.getUoAffectation());
        entity.setSiteExercice(dto.getSiteExercice());
        entity.setFonction(dto.getFonction());

        LOG.debug("En sortie : " + entity.toString());

        return entity;
    }
}
