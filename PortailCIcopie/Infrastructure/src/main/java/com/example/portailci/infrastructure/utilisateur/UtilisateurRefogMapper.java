package com.example.portailci.infrastructure.utilisateur;

import com.example.portailci.domain.utilisateur.UtilisateurEntity;
import com.example.portailci.infrastructure.commun.AbstractMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurRefogMapper extends AbstractMapper<UtilisateurRefogVO, UtilisateurEntity> {

    private Logger logger = LoggerFactory.getLogger(UtilisateurRefogMapper.class);

    @Override
    public UtilisateurRefogVO mapToVo(UtilisateurEntity entity) {
        logger.debug(" Méthode mapToDTO - UtilisateurEntity entity en entrée = " + entity);
        UtilisateurRefogVO utilisateurRefogVO = new UtilisateurRefogVO();
        utilisateurRefogVO.setUID(entity.getUid());
        utilisateurRefogVO.setNom(entity.getNom());
        utilisateurRefogVO.setPrenom(entity.getPrenom());
        utilisateurRefogVO.setUOAffectation(entity.getUoAffectation());
        utilisateurRefogVO.setSiteExercice(entity.getSiteExercice());
        utilisateurRefogVO.setFonction(entity.getFonction());


        logger.debug(" Méthode mapToDTO - UtilisateurRefogVO en sortie = " + utilisateurRefogVO);

        return utilisateurRefogVO;
    }

    @Override
    public UtilisateurEntity mapToEntity(UtilisateurRefogVO vo) {
        logger.debug(" Méthode mapToEntity - UtilisateurRefogVO vo en entrée = " + vo);

        UtilisateurEntity utilisateur = new UtilisateurEntity();

        utilisateur.setUid(vo.getUID());
        utilisateur.setNom(vo.getNom());
        System.out.println("UtilisateurEntity utilisateur.getNom() = " + utilisateur.getNom());
        utilisateur.setPrenom(vo.getPrenom());
        utilisateur.setUoAffectation(vo.getUOAffectation());
        utilisateur.setSiteExercice(vo.getSiteExercice());
        utilisateur.setFonction(vo.getFonction());

        logger.debug(" Méthode mapToEntity - UtilisateurEntity en sortie = " + utilisateur);

        return utilisateur;
    }

}
