package com.example.portailci.domain.thematique;

import java.util.List;

public interface IRepositoryThematique {
    List<ThematiqueEntity> findAllByIdParent(Long idParent);
    ThematiqueEntity save(ThematiqueEntity thematique);
    ThematiqueEntity update(ThematiqueEntity thematique);
    ThematiqueEntity findById(Long id);
    ThematiqueEntity findByNotIdAndNomAndIdParent(Long id, String nom, Long idParent);
    void delete(Long id);
}
