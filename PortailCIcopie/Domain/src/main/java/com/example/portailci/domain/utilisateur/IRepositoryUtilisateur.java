package com.example.portailci.domain.utilisateur;

import java.util.List;

public interface IRepositoryUtilisateur {

    UtilisateurEntity findByUid(String uid);

    UtilisateurEntity findById(Long id);

    List<UtilisateurEntity> findAll();

    UtilisateurEntity create(UtilisateurEntity utilisateur);

    UtilisateurEntity update(UtilisateurEntity utilisateur);

    void delete(UtilisateurEntity utilisateur);

}
