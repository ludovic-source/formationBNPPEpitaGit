package com.example.portailci.application.thematique;

import com.example.portailci.domain.exception.AlreadyExistsException;
import com.example.portailci.domain.exception.ThematiqueDataException;
import com.example.portailci.domain.exception.NotFoundException;
import com.example.portailci.domain.exception.ThematiqueNotEmptyException;
import com.example.portailci.domain.lien.IRepositoryLien;
import com.example.portailci.domain.thematique.ExtensionsImage;
import com.example.portailci.domain.thematique.IRepositoryThematique;
import com.example.portailci.domain.thematique.ThematiqueEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ManagementThematiqueImpl implements IManagementThematique {

    @Autowired
    IRepositoryThematique repositoryThematique;
    @Autowired
    IRepositoryLien repositoryLien;

    private static final Log logger = LogFactory.getLog(ManagementThematiqueImpl.class);

    @Override
    public ThematiqueEntity createThematique(ThematiqueEntity thematique, String extensionImage) {
        logger.info("Couche Application - ManagementThematique --> create thematique");
        logger.debug("create thematique : " + thematique.getNom());

        /* Contrôle des données d'entrée */
        controlerDonnees(thematique, extensionImage);

        int niveau = 0;

        /* La thématique parent doit obligatoirement exister pour une création */
        /* sauf cas particulier des thématiques de niveau 1 qui ont une thématique parent à 0 */
        if (thematique.getIdParent() != 0) {
            niveau = rechercherThematiqueParent(thematique.getIdParent()).getNiveau();
        }

        /* On vérifie qu'il n'y a pas déjà un thème ayant le même nom rattaché au parent */
        // id forcé à 0 car dans le cas d'une création, on n'a pas encore l'id à ce stade (et donc id = null)
        verifierUniciteNomThematique(0L, thematique.getNom(), thematique.getIdParent());

        thematique.setNiveau(niveau+1);
        return repositoryThematique.save(thematique);
    }

    private void verifierUniciteNomThematique(Long id, String nom, Long idParent) {
        if (repositoryThematique.findByNotIdAndNomAndIdParent(id, nom, idParent) != null) {
            logger.error("un thème avec le même nom existe déjà pour le thème parent : " + nom);
            throw new AlreadyExistsException("un thème avec le même nom existe déjà pour le thème parent : " + nom);
        }
    }

    private ThematiqueEntity rechercherThematiqueParent(Long idParent) {
        ThematiqueEntity thematiqueParent = repositoryThematique.findById(idParent);
        if (thematiqueParent == null) {
            // la thématique parent n'existe pas
            logger.error("thematique parent inexistante : " + idParent);
            throw new NotFoundException("Thématique parent inexistante : " + idParent);
        } else {
            return thematiqueParent;
        }
    }

    @Override
    public ThematiqueEntity updateThematique(ThematiqueEntity thematique) {
        logger.info("Couche Application - ManagementThematique --> update thematique");
        logger.debug("update thematique : " + thematique.getNom() + " / id_theme : " + thematique.getId());

        /* Contrôle des données d'entrée */
        String extension = "";
        if (thematique.getImagePath().length() != 0) {
            String[] decoupage = thematique.getImagePath().split("\\.");
            extension = decoupage[decoupage.length - 1];
        }

        controlerDonnees(thematique, extension);

        int niveau = 0;

        /* On vérifie que la thématique modifiée existe */
        if (repositoryThematique.findById(thematique.getId())==null) {
            logger.error("thematique inexistante : " + thematique.getId());
            throw new NotFoundException("Thématique inexistante : " + thematique.getId());
        }

        /* La thématique parent doit obligatoirement exister pour une modification */
        /* sauf cas particulier des thématiques de niveau 1 qui ont une thématique parent à 0 */
        if (thematique.getIdParent() != 0) {
            niveau = rechercherThematiqueParent(thematique.getIdParent()).getNiveau();
        }

        /* On vérifie qu'il n'y a pas déjà un thème ayant le même nom rattaché au parent */
        verifierUniciteNomThematique(thematique.getId(), thematique.getNom(), thematique.getIdParent());

        thematique.setNiveau(niveau+1);
        return repositoryThematique.save(thematique);
    }

    @Override
    public List<ThematiqueEntity> findThematiquesEnfants(Long idThematiqueParent) {
        logger.info("Couche Application - ManagementThematique --> recherche thematiques enfants");
        logger.debug("find thematiques de parent : " + idThematiqueParent);
        return repositoryThematique.findAllByIdParent(idThematiqueParent);
    }

    @Override
    public ThematiqueEntity findThematique(Long id) {
        logger.info("Couche Application - ManagementThematique --> recherche thematique");
        logger.debug("find données de la thématique : " + id);
        return repositoryThematique.findById(id);
    }

    @Override
    public void deleteThematique(Long id) {
        logger.info("Couche Application - ManagementThematique --> delete thematique");
        logger.debug("delete thématique : " + id);
        // On ne peut supprimer une thématique que si elle existe
        if (findThematique(id) != null) {
            // On ne peut supprimer une thématique que si elle est vide (ne contient ni thématique, ni lien)
            if (findThematiquesEnfants(id).size() == 0 && repositoryLien.getLiensByIdThematique(id).size() == 0) {
                repositoryThematique.delete(id);
            } else {
                logger.error("thematique non vide - ne peut être supprimée");
                throw new ThematiqueNotEmptyException ("Thématique non vide, contient  : " + findThematiquesEnfants(id).size() + " sous-thématique(s) / " + repositoryLien.getLiensByIdThematique(id).size() + " lien(s)");
            }
        } else {
            logger.error("thematique non trouvée - ne peut être supprimée : " + id);
            throw new NotFoundException ("Thématique non trouvée : " + id);
        }
    }

    private void controlerDonnees(ThematiqueEntity thematique, String extensionImage) {
        logger.info("Couche Application - ManagementThematique --> Contrôle des données");
        System.out.println("thematique.getId()          = " + thematique.getId());
        System.out.println("thematique.getDescription() = " + thematique.getDescription());
        System.out.println("thematique.getNom()         = " + thematique.getNom());
        System.out.println("thematique.getNiveau()      = " + thematique.getNiveau());
        System.out.println("thematique.getIdParent()    = " + thematique.getIdParent());
        System.out.println("thematique.getImagePath()   = " + thematique.getImagePath());
        System.out.println("extensionImage              = " + extensionImage);

        if ((thematique.getNom() == null)
        ||  (thematique.getIdParent() == null)) {
            logger.error("Donnée(s) non renseignée(s)");
            throw new ThematiqueDataException("Donnée(s) non renseignée(s)");
        }

        // Seules les thématiques de niveau 1 (donc ayant un idParent = 0) peuvent avoir une image
        if (thematique.getIdParent() != 0) {
            if (extensionImage.length() != 0) {
                logger.error("Une thématique de niveau > 1 ne peut pas avoir d'image");
                throw new ThematiqueDataException("Une thématique de niveau > 1 ne peut pas avoir d'image");
            }
        }

        // Extensions acceptées
        if (extensionImage.length() != 0) {
            boolean extensionImageAcceptee = false;
            for (ExtensionsImage extensionAcceptee : ExtensionsImage.values()) {
                if (extensionAcceptee.toString().equals(extensionImage)) {
                    System.out.println("entension image acceptée");
                    extensionImageAcceptee = true;
                }
            }
            if (!extensionImageAcceptee) {
                logger.error("Format d'image non accepté");
                throw new ThematiqueDataException("Format d'image non accepté");
            }
        }
    }

}
