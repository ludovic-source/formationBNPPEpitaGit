package com.example.portailci.infrastructure.utilisateur;

import com.example.portailci.domain.utilisateur.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUtilisateurJpaRepository extends JpaRepository<UtilisateurEntity, Long> {

    UtilisateurEntity findByUid(String uid);
}
