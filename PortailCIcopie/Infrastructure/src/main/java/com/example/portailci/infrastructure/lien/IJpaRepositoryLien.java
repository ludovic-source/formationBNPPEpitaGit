package com.example.portailci.infrastructure.lien;

import com.example.portailci.domain.lien.LienEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IJpaRepositoryLien extends JpaRepository<LienEntity, Integer> {

    @Query(value="SELECT * FROM liens WHERE thematique_id = :idThematique" +
            " AND (statut = 'publié' OR statut = 'publié restreint');", nativeQuery = true)
    List<LienEntity> getLiensByIdThematique(@Param("idThematique") Long idThematique);

    //@Query(value="select * from liens where thematique_id = :idThematique and statut = 'publié", nativeQuery = true)
    //List<LienEntity> getLiensDepubliesByIdThematique(@Param("idThematique") Long idThematique);

    List<LienEntity> findByStatut(String statut);

}
