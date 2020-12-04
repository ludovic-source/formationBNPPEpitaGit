package com.example.portailci.security;

import com.example.portailci.domain.exception.NotFoundException;
import com.example.portailci.domain.utilisateur.IRepositoryUtilisateur;
import com.example.portailci.domain.utilisateur.UtilisateurEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;


@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private IRepositoryUtilisateur repositoryUtilisateur;

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Entrée dans la méthode loadUserByUsername de la classe CustomUserDetails");
        System.out.println("----------------------------------------------------------------");
        logger.debug(" Méthode loadUserByUsername - uid = " + uid);
        try {
            final UtilisateurEntity utilisateurEntity = repositoryUtilisateur.findByUid(uid);
            logger.debug(" Utilisateur trouvé en base = " + utilisateurEntity);

            if (utilisateurEntity == null) {
                logger.debug(" Utilisateur trouvé en base = null -UsernameNotFoundException levée");
                throw new UsernameNotFoundException("L'UID : " + uid + " n'a pas été trouvé");
            }
            logger.debug("On retourne un User: username = "+ utilisateurEntity.getUid() + " - Mot de passe = " + utilisateurEntity.getMotDePasse() + " authorities = " + getAuthorities(utilisateurEntity));
            return new User(utilisateurEntity.getUid(), utilisateurEntity.getMotDePasse(), getAuthorities(utilisateurEntity));
        }
        catch (Exception e) {
            logger.debug(" Méthode loadUserByUsername - Exception levée : " + e.getMessage());
            //return new User("","",false,false,false,false,null);
            throw new NotFoundException(e.getMessage());
        }
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(final UtilisateurEntity utilisateurEntity) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Entrée dans la méthode getAuthorities de la classe CustomUserDetails");
        System.out.println("----------------------------------------------------------------");

        final String[] userRoles = utilisateurEntity.getProfil().getDroits().stream().map((droit) -> droit.getNom()).toArray(String[]::new);

        for (String role: userRoles){
            System.out.println(role);
        }

        final Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}
