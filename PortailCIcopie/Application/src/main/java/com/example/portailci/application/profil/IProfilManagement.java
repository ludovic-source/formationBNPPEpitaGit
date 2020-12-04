package com.example.portailci.application.profil;

import com.example.portailci.domain.profil.ProfilEntity;

import java.util.Set;

public interface IProfilManagement {

    ProfilEntity create(ProfilEntity utilisateur);

    ProfilEntity findByID(Long id);

    ProfilEntity findByNom(String nom);

    ProfilEntity findProfilOfConnectedUser();

    void  delete(Long id);

    ProfilEntity update(ProfilEntity utilisateur);

    Set<ProfilEntity> findAll();
}
