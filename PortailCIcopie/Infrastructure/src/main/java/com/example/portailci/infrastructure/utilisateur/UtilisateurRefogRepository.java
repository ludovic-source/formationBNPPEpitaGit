package com.example.portailci.infrastructure.utilisateur;

import com.example.portailci.domain.utilisateur.IUtilisateurRefogRepository;
import com.example.portailci.domain.utilisateur.UtilisateurEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UtilisateurRefogRepository implements IUtilisateurRefogRepository {

    @Autowired
    private UtilisateurRefogMapper utilisateurRefogMapper;

    private static Logger logger = LoggerFactory.getLogger(UtilisateurRefogRepository.class);

    private Set<UtilisateurRefogVO> utilisateursRefog;

    public UtilisateurRefogRepository(Set<UtilisateurRefogVO> utilisateursRefog) {
        this.utilisateursRefog = utilisateursRefog;

        UtilisateurRefogVO utilisateur1 = new UtilisateurRefogVO("139696","JOLY","Christophe","14578A65","Valmy 2","Développeur");
        UtilisateurRefogVO utilisateur2 = new UtilisateurRefogVO("a30607","TAVIGNOT","Bertrand","41796B12","Papeete","Développeur");
        UtilisateurRefogVO utilisateur3 = new UtilisateurRefogVO("123456","JACKSON","Michael","12463258","Tassigny","CIL");
        UtilisateurRefogVO utilisateur4 = new UtilisateurRefogVO("000000","FEDERER","Roger","12463258","Tassigny","CIL");
        UtilisateurRefogVO utilisateur5 = new UtilisateurRefogVO("654321","NADAL","Rafael","12463258","Tassigny","CIL");
        UtilisateurRefogVO utilisateur6 = new UtilisateurRefogVO("987654","CHIMIER","Ludovic","74102R85","Paris","Développeur");

        this.utilisateursRefog.add(utilisateur1);
        this.utilisateursRefog.add(utilisateur2);
        this.utilisateursRefog.add(utilisateur3);
        this.utilisateursRefog.add(utilisateur4);
        this.utilisateursRefog.add(utilisateur5);
        this.utilisateursRefog.add(utilisateur6);
    }

    public Set<UtilisateurRefogVO> getUtilisateursRefog() {
        return utilisateursRefog;
    }

    public UtilisateurEntity getUtilisateurRefogVOByUid (String uid) {

        UtilisateurRefogVO utilisateurRefogVO = null;
        logger.debug("Méthode  getUtilisateurRefogVOByUid - Boucle sur le set d'utilisateurRefogVO - String uid recherché = " + uid);

        for (UtilisateurRefogVO utilisateur : this.utilisateursRefog){
            logger.debug("Méthode  getUtilisateurRefogVOByUid - utilisateurRefogVO uid = " + utilisateur.getUID() + " - Correspondance = " + (utilisateur.getUID()).equals(uid));

            if((utilisateur.getUID()).equals(uid)){
                utilisateurRefogVO = new UtilisateurRefogVO();
                utilisateurRefogVO.setUID(utilisateur.getUID());
                utilisateurRefogVO.setNom(utilisateur.getNom());
                utilisateurRefogVO.setPrenom(utilisateur.getPrenom());
                utilisateurRefogVO.setUOAffectation(utilisateur.getUOAffectation());
                utilisateurRefogVO.setSiteExercice(utilisateur.getSiteExercice());
                utilisateurRefogVO.setFonction(utilisateur.getFonction());
            }
        }
        if(utilisateurRefogVO != null) logger.debug("Méthode  getUtilisateurRefogVOByUid - Sortie de boucle = " + utilisateurRefogVO.toString());
        else logger.debug("Méthode  getUtilisateurRefogVOByUid - Sortie de boucle = " + null);


        // Si l'UID recherché existe on mappe l'UtilisateurRefogVO pour retourner un UtilisateurEntity
        if(utilisateurRefogVO != null) {
            UtilisateurEntity utilisateurEntity = utilisateurRefogMapper.mapToEntity(utilisateurRefogVO);
            logger.debug("On retourne l'UtilisateurrefogVO convertit en UtilisateurEntity : " + utilisateurEntity);
            return utilisateurEntity;
        }
        // Sinon on retourne un UtilisateurEntity null
        else{
            logger.debug("Aucun utilisateur portant l'UID : " + uid + " n'a été trouvé dans le REFOG. Retour de la méthode = " + null);
            return null;
        }

    }
}
