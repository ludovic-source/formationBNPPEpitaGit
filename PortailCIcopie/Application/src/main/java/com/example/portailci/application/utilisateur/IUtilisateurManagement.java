package com.example.portailci.application.utilisateur;

import com.example.portailci.domain.utilisateur.UtilisateurEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUtilisateurManagement {

    UtilisateurEntity create(UtilisateurEntity utilisateur);

    UtilisateurEntity findByUid(String utilisateurUid, boolean fromRefog);

    UtilisateurEntity findByID(Long id);

    void  delete(Long id);

    UtilisateurEntity update(UtilisateurEntity utilisateur);

    List<UtilisateurEntity> findAll();
}
