package com.example.portailci.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AppAuthProvider extends DaoAuthenticationProvider {

    private static Logger LOGGER = LoggerFactory.getLogger(AppAuthProvider.class);

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Entrée dans la méthode passwordEncoder de la classe AppAuthProvider");
        System.out.println("----------------------------------------------------------------");

        return new BCryptPasswordEncoder();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        System.out.println("----------------------------------------------------------------");
        System.out.println("Entrée dans la méthode authenticate de la classe AppAuthProvider");
        System.out.println("----------------------------------------------------------------");

        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;

        String name = auth.getName();
        String password = auth.getCredentials().toString();

        LOGGER.debug("Auth.getname() = " + auth.getName());
        LOGGER.debug("Auth.getCredentials() = " + auth.getCredentials().toString());
        try {
            UserDetails user = userDetailsService.loadUserByUsername(name);

            if (user != null && passwordEncoder().matches(password, user.getPassword()) == true) {

                LOGGER.debug("Authentification réussie !");
                return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            }
            throw new BadCredentialsException("Authentification échouée. Merci de saisir votre UID et mot de passe");

        } catch (Exception e) {
            LOGGER.debug("Authentification échouée.");
            throw new BadCredentialsException("Authentification échouée. Merci de saisir votre UID et mot de passe");
        }


    }

    @Override
    public boolean supports(Class<?> authentication) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Entrée dans la méthode supports de la classe AppAuthProvider");
        System.out.println("----------------------------------------------------------------");
        return true;
    }
}
