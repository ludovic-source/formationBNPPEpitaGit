package com.example.portailci.infrastructure.thematique;

import com.example.portailci.domain.thematique.ThematiqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IThematiqueJpaRepository extends JpaRepository<ThematiqueEntity, Long> {
    ThematiqueEntity save(ThematiqueEntity thematiqueEntity);
    List<ThematiqueEntity> findAllByIdParent(Long idThemeParent);
    Optional<ThematiqueEntity> findById(Long id);

    @Query(value="select * from thematique where id <> :id and nom = :nom and id_Parent = :idParent", nativeQuery = true)
    Optional<ThematiqueEntity> findByNotIdAndNomAndIdParent(@Param("id") Long id, @Param("nom") String nom, @Param("idParent") Long idParent);
}
