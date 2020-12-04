package com.example.portailci.application.thematique;

import com.example.portailci.domain.thematique.ThematiqueEntity;

import java.util.List;

public interface IManagementThematique {
    ThematiqueEntity createThematique(ThematiqueEntity thematique, String extensionImage);
    ThematiqueEntity updateThematique(ThematiqueEntity thematique);
    List<ThematiqueEntity> findThematiquesEnfants(Long idThematiqueParent);
    ThematiqueEntity findThematique(Long id);
    void deleteThematique(Long id);
}
