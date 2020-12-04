package com.example.portailci.security;

import java.io.IOException;
import java.util.Arrays;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

/*
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
*/

    @Bean
    public AuthenticationProvider getProvider() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Entrée dans la méthode getProvider de la classe SecurityConfig");
        System.out.println("----------------------------------------------------------------");
        AppAuthProvider provider = new AppAuthProvider();
        provider.setUserDetailsService(customUserDetailsService);
        logger.debug("getProvider() retour = " + provider);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Entrée dans la méthode configure(AuthenticationManagerBuilder de la classe SecurityConfig");
        System.out.println("----------------------------------------------------------------");
        logger.debug("Méthode configure(AuthenticationManagerBuilder auth = " + auth + " )");
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Entrée dans la méthode passwordEncoder(retourne un BCryptPasswordEncoder) de la classe SecurityConfig");
        System.out.println("----------------------------------------------------------------");
        return new BCryptPasswordEncoder();
    }


    // A utiliser uniquement en dev lorsque le front et le back end sont sur le même serveur
    // Le bean est utilisé pour le .cors() dans la methode configure
    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Entrée dans la méthode corsConfigurationSource(retourne une CorsConfigurationSource) de la classe SecurityConfig");
        System.out.println("----------------------------------------------------------------");
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.applyPermitDefaultValues();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://localhost:4200","http://localhost:4201"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        //source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Entrée dans la méthode configure(HttpSecurity) de la classe SecurityConfig");
        System.out.println("----------------------------------------------------------------");
        http    .cors()   // A utiliser uniquement en dev
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint() {
                })
                .and()
                .authenticationProvider(getProvider())
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(new AuthentificationLoginSuccessHandler())
                .failureHandler(new AuthentificationLoginFailureHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new AuthentificationLogoutSuccessHandler())
                .invalidateHttpSession(true)
                .and()
                .authorizeRequests()
                // IMPORTANT: Mettre toutes les routes de la plus restrictive à la plus permissive (contrôle dans l'ordre de déclaration des URL)
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/portailci/**").authenticated()
                .anyRequest().permitAll();
    }

    private class AuthentificationLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

        private Logger logger = LoggerFactory.getLogger(AuthentificationLoginSuccessHandler.class);

        @Override
        public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                            final Authentication authentication) throws IOException, ServletException {
            System.out.println("----------------------------------------------------------------");
            System.out.println("Entrée dans la méthode onAuthenticationSuccess la classe AuthentificationLoginSuccessHandler situé dans le fichier SecurityConfig");
            System.out.println("----------------------------------------------------------------");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("true");
            logger.debug(" Méthode onAuthenticationSuccess - response status = " + response.getStatus());

        }
    }

    private class AuthentificationLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

        private Logger logger = LoggerFactory.getLogger(AuthentificationLoginFailureHandler.class);
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            super.onAuthenticationFailure(request, response, exception);
            System.out.println("----------------------------------------------------------------");
            System.out.println("Entrée dans la méthode onAuthenticationFailure la classe AuthentificationLoginFailureHandler situé dans le fichier SecurityConfig");
            System.out.println("----------------------------------------------------------------");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("false");

            logger.debug(" Méthode onAuthenticationFailure - response status = " + response.getStatus());
        }
    }

    private class AuthentificationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

        private Logger logger = LoggerFactory.getLogger(AuthentificationLogoutSuccessHandler.class);

        @Override
        public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                    final Authentication authentication) throws IOException, ServletException {
            System.out.println("----------------------------------------------------------------");
            System.out.println("Entrée dans la méthode onLogoutSuccess la classe AuthentificationLogoutSuccessHandler situé dans le fichier SecurityConfig");
            System.out.println("----------------------------------------------------------------");
            response.setStatus(HttpServletResponse.SC_OK);
            logger.debug(" Méthode onLogoutSuccess - response status = " + response.getStatus());

        }
    }


}
