package com.example.portailci.infrastructure.profil;

import com.example.portailci.domain.profil.ProfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IProfilJpaRepository extends JpaRepository<ProfilEntity, Long> {
    Optional<ProfilEntity> findByNom(String nom);
}
