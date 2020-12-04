package com.example.portailci.domain.profil;

import java.util.Optional;
import java.util.Set;

public interface IRepositoryProfil {

    ProfilEntity findById(Long id);

    Optional<ProfilEntity> findByNom(String nom);

    Set<ProfilEntity> findAll();

    ProfilEntity create(ProfilEntity profil);

    ProfilEntity update(ProfilEntity profil);

    void delete(ProfilEntity profil);

}
