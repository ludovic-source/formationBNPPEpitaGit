package com.example.portailci.infrastructure.thematique;

import com.example.portailci.domain.thematique.IRepositoryThematique;
import com.example.portailci.domain.thematique.ThematiqueEntity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestInstance(PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("Validation of the repository Thematique")
public class RepositoryThematiqueImplTest {
    @Autowired
    IRepositoryThematique repositoryThematique;

    private ThematiqueEntity thematique = new ThematiqueEntity();
    private ThematiqueEntity thematiqueUn = new ThematiqueEntity();
    private ThematiqueEntity thematiqueUnUn = new ThematiqueEntity();
    private ThematiqueEntity thematiqueUnDeux = new ThematiqueEntity();
    private ThematiqueEntity thematiqueDeux = new ThematiqueEntity();

    private List<ThematiqueEntity> thematiquesAvant;

    @BeforeEach
    void init() {
        // mise en place de l'environnement avant toute action :
        // - Thématique 1
        //   - Thématique 1.1
        //   - Thématique 1.2
        // - Thématique 2

        thematiqueUn.setNom("Thématique 1");
        thematiqueUn.setDescription("Description 1");
        thematiqueUn.setIdParent(0L);
        thematiqueUn.setNiveau(1);
        thematiqueUn.setImagePath("pas d'image");
        repositoryThematique.save(thematiqueUn);

        thematiqueUnUn.setNom("Thématique 1.1");
        thematiqueUnUn.setDescription("Description 1.1");
        thematiqueUnUn.setIdParent(1L);
        thematiqueUnUn.setNiveau(2);
        thematiqueUnUn.setImagePath("pas d'image");
        repositoryThematique.save(thematiqueUnUn);

        thematiqueUnDeux.setNom("Thématique 1.2");
        thematiqueUnDeux.setDescription("Description 1.2");
        thematiqueUnDeux.setIdParent(1L);
        thematiqueUnDeux.setNiveau(2);
        thematiqueUnDeux.setImagePath("pas d'image");
        repositoryThematique.save(thematiqueUnDeux);

        thematiqueDeux.setNom("Thématique 2");
        thematiqueDeux.setDescription("Description 2");
        thematiqueDeux.setIdParent(0L);
        thematiqueDeux.setNiveau(2);
        thematiqueDeux.setImagePath("pas d'image");
        repositoryThematique.save(thematiqueDeux);

        thematiquesAvant = repositoryThematique.findAllByIdParent(0L);
    }

    @Test
    @DisplayName("Création thématique")
    public void create_a_thematique() {
        // Given
        thematique.setNom("Thématique créée");
        thematique.setDescription("Description thématique créée");
        thematique.setIdParent(0L);
        thematique.setImagePath("pas d'image");

        // When
        ThematiqueEntity thematiqueRetour = repositoryThematique.save(thematique);

        // Then
        List<ThematiqueEntity> thematiquesApres = repositoryThematique.findAllByIdParent(thematique.getIdParent());
        assertThat(thematiquesApres.size()).isEqualTo(thematiquesAvant.size()+1);
        assertThat(thematiqueRetour.getId()).isNotNull();
        assertThat(thematiqueRetour.getNom()).isEqualTo(thematique.getNom());
        assertThat(thematiqueRetour.getDescription()).isEqualTo(thematique.getDescription());
        assertThat(thematiqueRetour.getIdParent()).isEqualTo(thematique.getIdParent());
        assertThat(thematiqueRetour.getNiveau()).isEqualTo(thematique.getNiveau());
        assertThat(thematiqueRetour.getImagePath()).isEqualTo(thematique.getImagePath());
    }

    @Test
    @DisplayName("Suppression thématique")
    public void delete_a_thematique() {
        // When
        repositoryThematique.delete(thematiqueDeux.getId());

        // Then
        List<ThematiqueEntity> thematiquesApres = repositoryThematique.findAllByIdParent(thematiqueDeux.getIdParent());
        assertThat(thematiquesApres.size()).isEqualTo(thematiquesAvant.size()-1);
        assertThat(repositoryThematique.findById(thematiqueDeux.getId())).isNull();
    }

    @Test
    @DisplayName("Recherche thématique")
    public void find_a_thematique() {
        // When
        ThematiqueEntity thematiqueRetour = repositoryThematique.findById(thematiqueUn.getId());

        // Then
        assertThat(thematiqueRetour.getId()).isEqualTo(thematiqueUn.getId());
        assertThat(thematiqueRetour.getNom()).isEqualTo(thematiqueUn.getNom());
        assertThat(thematiqueRetour.getDescription()).isEqualTo(thematiqueUn.getDescription());
        assertThat(thematiqueRetour.getIdParent()).isEqualTo(thematiqueUn.getIdParent());
        assertThat(thematiqueRetour.getNiveau()).isEqualTo(thematiqueUn.getNiveau());
        assertThat(thematiqueRetour.getImagePath()).isEqualTo(thematiqueUn.getImagePath());
    }

    @Test
    @DisplayName("Modification thématique")
    public void update_a_thematique() {
        // Given
        thematiqueUn.setNom("Thématique 1 Modifiée");

        // When
        ThematiqueEntity thematiqueRetour = repositoryThematique.update(thematiqueUn);

        // Then
        List<ThematiqueEntity> thematiquesApres = repositoryThematique.findAllByIdParent(thematiqueUn.getIdParent());
        assertThat(thematiquesApres.size()).isEqualTo(thematiquesAvant.size());
        assertThat(thematiqueRetour.getId()).isEqualTo(thematiqueUn.getId());
        assertThat(thematiqueRetour.getNom()).isEqualTo(thematiqueUn.getNom());
        assertThat(thematiqueRetour.getDescription()).isEqualTo(thematiqueUn.getDescription());
        assertThat(thematiqueRetour.getIdParent()).isEqualTo(thematiqueUn.getIdParent());
        assertThat(thematiqueRetour.getNiveau()).isEqualTo(thematiqueUn.getNiveau());
        assertThat(thematiqueRetour.getImagePath()).isEqualTo(thematiqueUn.getImagePath());
    }

    @Test
    @DisplayName("Recherche pour doublon - pas de doublon")
    public void not_find_by_not_id_and_nom_and_idParent() {
        // Given
        thematique.setId(thematiqueUnUn.getId());
        thematique.setNom("Thématique 1.3");            // nom de sous-thématique inexistant pour thématique
        thematique.setIdParent(thematiqueUnUn.getIdParent());

        // When/Then
        assertThat(repositoryThematique.findByNotIdAndNomAndIdParent(thematique.getId(), thematique.getNom(), thematique.getIdParent())).isNull();
    }

    @Test
    @DisplayName("Recherche pour doublon - doublon")
    public void find_by_not_id_and_nom_and_idParent() {
        // Given
        thematique.setId(thematiqueUnUn.getId());
        thematique.setNom(thematiqueUnDeux.getNom());         // nom de sous-thématique existant pour thématique
        thematique.setIdParent(thematiqueUnUn.getIdParent());

        // When/Then
        assertThat(repositoryThematique.findByNotIdAndNomAndIdParent(thematique.getId(), thematique.getNom(), thematique.getIdParent())).isNotNull();
    }
}