package com.example.portailci.infrastructure.thematique;

import com.example.portailci.domain.thematique.IRepositoryThematique;
import com.example.portailci.domain.thematique.ThematiqueEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RepositoryThematiqueImpl implements IRepositoryThematique {
    @Autowired
    private IThematiqueJpaRepository thematiqueJpaRepository;
    private static ThematiqueEntity thematiqueEntity;       // variable de classe utilisée pour fonction lambda

    public RepositoryThematiqueImpl(IThematiqueJpaRepository thematiqueJpaRepository) {
        this.thematiqueJpaRepository = thematiqueJpaRepository;
    }

    @Override
    public List<ThematiqueEntity> findAllByIdParent(Long idParent) {
        List<ThematiqueEntity> thematiques = thematiqueJpaRepository.findAllByIdParent(idParent);
        return thematiques;
    }

    @Override
    public ThematiqueEntity save(ThematiqueEntity thematique) {
        return thematiqueJpaRepository.save(thematique);
    }

    @Override
    public ThematiqueEntity update(ThematiqueEntity thematique) {
        return thematiqueJpaRepository.save(thematique);
    }

    @Override
    public ThematiqueEntity findById(Long id) {
        Optional<ThematiqueEntity> thematiqueEntity = thematiqueJpaRepository.findById(id);
        if(thematiqueEntity.isPresent()) {
            thematiqueEntity.ifPresent(TheThematique -> lambdaFindById(TheThematique));
            return this.thematiqueEntity;
        } else {
            return null;
        }
    }

    @Override
    public ThematiqueEntity findByNotIdAndNomAndIdParent(Long id, String nom, Long idParent) {
        Optional<ThematiqueEntity> thematiqueEntity = thematiqueJpaRepository.findByNotIdAndNomAndIdParent(id, nom, idParent);
        if(thematiqueEntity.isPresent()) {
            thematiqueEntity.ifPresent(TheThematique -> lambdaFindById(TheThematique));
            return this.thematiqueEntity;
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        ThematiqueEntity thematiqueASupprimer = findById(id);
        if (thematiqueASupprimer != null) {
            thematiqueJpaRepository.delete(findById(id));
        }
    }

    public static void lambdaFindById(ThematiqueEntity thematique) {
        thematiqueEntity = thematique;
    }
}
