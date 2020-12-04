package com.example.portailci.application.thematique;

import com.example.portailci.domain.exception.AlreadyExistsException;
import com.example.portailci.domain.exception.NotFoundException;
import com.example.portailci.domain.exception.ThematiqueDataException;
import com.example.portailci.domain.exception.ThematiqueNotEmptyException;
import com.example.portailci.domain.lien.IRepositoryLien;
import com.example.portailci.domain.lien.LienEntity;
import com.example.portailci.domain.thematique.IRepositoryThematique;
import com.example.portailci.domain.thematique.ThematiqueEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ManagementThematiqueImpl.class)
@ExtendWith(SpringExtension.class)
public class TestManagementThematiqueImpl {
    @Autowired
    private IManagementThematique managementThematique;
    @MockBean
    private IRepositoryThematique repositoryThematique;
    @MockBean
    private IRepositoryLien repositoryLien;

    private ThematiqueEntity thematique = new ThematiqueEntity();
    private String extension = "";

    @Test
    @DisplayName("Create a thematique in a non existing parent should fail")
    public void create_a_thematique_in_a_non_existing_parent_should_fail() {
        thematique.setNom("Nom thématique");
        thematique.setDescription("Description thématique");
        thematique.setIdParent(-1L);        // -1 : id parent inexistant
        thematique.setImagePath("");

        if (thematique.getImagePath().length() != 0) {
            String[] decoupage = thematique.getImagePath().split("\\.");
            extension = decoupage[decoupage.length - 1];
        }

        assertThatThrownBy(() -> managementThematique.createThematique(thematique, extension))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("GENERIC_NOT_FOUND");
    }

    @Test
    @DisplayName("Create a thematique in an existing parent with a non existing name should succes")
    public void create_a_thematique_in_an_existing_parent_with_a_non_existing_name_should_succes() {
        thematique.setNom("Nom thématique");
        thematique.setDescription("Description thématique");
        thematique.setIdParent(1L);        // 1 : id parent existant
        thematique.setImagePath("");

        ThematiqueEntity thematiqueParent = new ThematiqueEntity();
        thematiqueParent.setId(1L);
        thematiqueParent.setNom("Parent");
        thematiqueParent.setIdParent(0L);
        thematiqueParent.setDescription("Description thématique parent");
        thematiqueParent.setNiveau(10);
        thematiqueParent.setImagePath("http://Chemin_image/image.gif");

        extension = "";
        if (thematique.getImagePath().length() != 0) {
            String[] decoupage = thematique.getImagePath().split("\\.");
            extension = decoupage[decoupage.length - 1];
        }
        when(repositoryThematique.findById(thematiqueParent.getId())).thenReturn(thematiqueParent); // thématique parent
        when(repositoryThematique.findByNotIdAndNomAndIdParent(thematique.getId(), thematique.getNom(), thematique.getIdParent())).thenReturn(null); // pas de thématique avec le même nom
        thematique.setId(100L); // valorisation arbitraire pour ne pas avoir l'id à null au retour de l'appel à la méthode save
        when(repositoryThematique.save(thematique)).thenReturn(thematique); // la création retourne la thématique

        thematique = managementThematique.createThematique(thematique, extension);

        assertThat(thematique.getNiveau() == (thematiqueParent.getNiveau() + 1));
    }


    @Test
    @DisplayName("Create a thematique with an image's extension non authorized should fail")
    public void create_a_thematique_with_a_image_extension_non_authorized_should_fail() {
        thematique.setNom("Nom thématique");
        thematique.setDescription("Description thématique");
        thematique.setIdParent(0L);        // ==> thème de niveau 1 - peut avoir une image
        thematique.setImagePath("image.txt");        // extension de fichier incorrecte

        extension = "";
        if (thematique.getImagePath().length() != 0) {
            String[] decoupage = thematique.getImagePath().split("\\.");
            extension = decoupage[decoupage.length - 1];
        }

        when(repositoryThematique.findByNotIdAndNomAndIdParent(thematique.getId(), thematique.getNom(), thematique.getIdParent())).thenReturn(null); // pas de thématique avec le même nom
        thematique.setId(100L); // valorisation arbitraire pour ne pas avoir l'id à null au retour de l'appel à la méthode save
        when(repositoryThematique.save(thematique)).thenReturn(thematique); // la création retourne la thématique

        assertThatThrownBy(() -> managementThematique.createThematique(thematique, extension))
                .isInstanceOf(ThematiqueDataException.class)
                .hasMessageContaining("COMPULSORY_DATA");
    }

    @Test
    @DisplayName("Create a thematique without name should fail")
    public void create_a_thematique_without_name_should_fail() {
        thematique.setDescription("Description thématique");
        thematique.setIdParent(1L);
        thematique.setImagePath("");

        ThematiqueEntity thematiqueParent = new ThematiqueEntity();
        thematiqueParent.setId(1L);
        thematiqueParent.setNom("Parent");
        thematiqueParent.setIdParent(0L);
        thematiqueParent.setDescription("Description thématique parent");
        thematiqueParent.setNiveau(10);
        thematiqueParent.setImagePath("http://Chemin_image/image.gif");

        if (thematique.getImagePath().length() != 0) {
            String[] decoupage = thematique.getImagePath().split("\\.");
            extension = decoupage[decoupage.length - 1];
        }

        when(repositoryThematique.findById(thematiqueParent.getId())).thenReturn(thematiqueParent); // thématique parent
        when(repositoryThematique.findByNotIdAndNomAndIdParent(thematique.getId(), thematique.getNom(), thematique.getIdParent())).thenReturn(null); // pas de thématique avec le même nom
        thematique.setId(100L); // valorisation arbitraire pour ne pas avoir l'id à null au retour de l'appel à la méthode save
        when(repositoryThematique.save(thematique)).thenReturn(thematique); // la création retourne la thématique

        assertThatThrownBy(() -> managementThematique.createThematique(thematique, extension))
                .isInstanceOf(ThematiqueDataException.class)
                .hasMessageContaining("COMPULSORY_DATA");
    }

    @Test
    @DisplayName("Create a thematique without idParent should fail")
    public void create_a_thematique_without_idParent_should_fail() {
        thematique.setNom("Nom thématique");
        thematique.setDescription("Description thématique");
        thematique.setImagePath("");

        ThematiqueEntity thematiqueParent = new ThematiqueEntity();
        thematiqueParent.setId(1L);
        thematiqueParent.setNom("Parent");
        thematiqueParent.setIdParent(0L);
        thematiqueParent.setDescription("Description thématique parent");
        thematiqueParent.setNiveau(10);
        thematiqueParent.setImagePath("http://Chemin_image/image.gif");

        if (thematique.getImagePath().length() != 0) {
            String[] decoupage = thematique.getImagePath().split("\\.");
            extension = decoupage[decoupage.length - 1];
        }

        when(repositoryThematique.findById(thematiqueParent.getId())).thenReturn(thematiqueParent); // thématique parent
        when(repositoryThematique.findByNotIdAndNomAndIdParent(thematique.getId(), thematique.getNom(), thematique.getIdParent())).thenReturn(null); // pas de thématique avec le même nom
        thematique.setId(100L); // valorisation arbitraire pour ne pas avoir l'id à null au retour de l'appel à la méthode save
        when(repositoryThematique.save(thematique)).thenReturn(thematique); // la création retourne la thématique

        assertThatThrownBy(() -> managementThematique.createThematique(thematique, extension))
                .isInstanceOf(ThematiqueDataException.class)
                .hasMessageContaining("COMPULSORY_DATA");
    }

    @Test
    @DisplayName("Create an existing thematique in the same parent niveau 1 should fail")
    public void create_an_existing_thematique_in_a_parent_niveau_1_should_fail() {
        thematique.setNom("Nom déjà existant");
        thematique.setDescription("Description de la thématique");
        thematique.setIdParent(1L);
        thematique.setImagePath("");

        ThematiqueEntity thematiqueParent = new ThematiqueEntity();
        thematiqueParent.setId(1L);
        thematiqueParent.setNom("Parent");
        thematiqueParent.setIdParent(0L);
        thematiqueParent.setDescription("Description thématique parent");
        thematiqueParent.setNiveau(1);                          // parent de niveau 1
        thematiqueParent.setImagePath("http://Chemin_image/image.gif");

        ThematiqueEntity thematiqueExistante = new ThematiqueEntity();
        thematiqueExistante.setId(2L);
        thematiqueExistante.setNom(thematique.getNom());
        thematiqueExistante.setIdParent(thematique.getIdParent());
        thematiqueExistante.setDescription("Description de la thématique existante");
        thematiqueExistante.setNiveau(thematiqueParent.getNiveau()+1);
        thematiqueExistante.setImagePath("");

        if (thematique.getImagePath().length() != 0) {
            String[] decoupage = thematique.getImagePath().split("\\.");
            extension = decoupage[decoupage.length - 1];
        }

        when(repositoryThematique.findById(thematiqueParent.getId())).thenReturn(thematiqueParent);
        when(repositoryThematique.findById(thematiqueExistante.getId())).thenReturn(thematiqueExistante);
        when(repositoryThematique.findByNotIdAndNomAndIdParent(0L, thematique.getNom(), thematique.getIdParent())).thenReturn(thematiqueExistante);

        assertThatThrownBy(() -> managementThematique.createThematique(thematique, extension))
               .isInstanceOf(AlreadyExistsException.class)
               .hasMessageContaining("GENERIC_ALREADY_EXISTS");
    }

    @Test
    @DisplayName("Create an existing thematique in the same parent niveau > 1 should fail")
    public void create_an_existing_thematique_in_a_parent_niveau_gt1_should_fail() {
        thematique.setNom("Nom déjà existant");
        thematique.setDescription("Description de la thématique");
        thematique.setIdParent(10L);
        thematique.setImagePath("");

        ThematiqueEntity thematiqueParent = new ThematiqueEntity();
        thematiqueParent.setId(thematique.getIdParent());
        thematiqueParent.setNom("Parent");
        thematiqueParent.setIdParent(1L);
        thematiqueParent.setDescription("Description thématique parent");
        thematiqueParent.setNiveau(2);                  // parent de niveau > 1
        thematiqueParent.setImagePath("");

        ThematiqueEntity thematiqueExistante = new ThematiqueEntity();
        thematiqueExistante.setId(2L);
        thematiqueExistante.setNom(thematique.getNom());
        thematiqueExistante.setIdParent(thematique.getIdParent());
        thematiqueExistante.setDescription("Description de la thématique existante");
        thematiqueExistante.setNiveau(thematiqueParent.getNiveau()+1);
        thematiqueExistante.setImagePath("");

        if (thematique.getImagePath().length() != 0) {
            String[] decoupage = thematique.getImagePath().split("\\.");
            extension = decoupage[decoupage.length - 1];
        }

        when(repositoryThematique.findById(thematiqueParent.getId())).thenReturn(thematiqueParent);
        when(repositoryThematique.findById(thematiqueExistante.getId())).thenReturn(thematiqueExistante);
        when(repositoryThematique.findByNotIdAndNomAndIdParent(0L, thematique.getNom(), thematique.getIdParent())).thenReturn(thematiqueExistante);

        assertThatThrownBy(() -> managementThematique.createThematique(thematique, extension))
                .isInstanceOf(AlreadyExistsException.class)
                .hasMessageContaining("GENERIC_ALREADY_EXISTS");
    }

    @Test
    @DisplayName("Update a non existing thematique should fail")
    public void update_a_non_existing_thematique_should_fail() {
        thematique.setId(-1L);                  // id inexistant
        thematique.setNom("Nom thématique");
        thematique.setDescription("Description thématique");
        thematique.setIdParent(1L);
        thematique.setNiveau(2);
        thematique.setImagePath("");

        when(repositoryThematique.findById(thematique.getId())).thenReturn(null); // la thématique n'existe pas, elle n'est pas trouvée quand on la recherche

        assertThatThrownBy(() -> managementThematique.updateThematique(thematique))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("GENERIC_NOT_FOUND");
    }

    @Test
    @DisplayName("Update a thematique giving an existing name in the parent should fail")
    public void update_a_thematique_giving_an_existing_name_in_the_parent_should_fail() {
        thematique.setId(10L);
        thematique.setNom("Nom d'une thématique qui existe déjà sous le même parent");
        thematique.setDescription("Description thématique");
        thematique.setIdParent(3L);
        thematique.setNiveau(3);
        thematique.setImagePath("");

        ThematiqueEntity thematiqueParent = new ThematiqueEntity();
        thematiqueParent.setId(thematique.getIdParent());
        thematiqueParent.setNom("Parent");
        thematiqueParent.setIdParent(1L);
        thematiqueParent.setDescription("Description thématique parent");
        thematiqueParent.setNiveau(thematique.getNiveau()-1);
        thematiqueParent.setImagePath("");

        ThematiqueEntity thematiqueExistante = new ThematiqueEntity();
        thematiqueExistante.setId(9L);
        thematiqueExistante.setNom(thematique.getNom());                // même nom que la thématique qu'on souhaite modifier
        thematiqueExistante.setIdParent(thematique.getIdParent());      // même parent que la thématique qu'on souhaite modifier
        thematiqueExistante.setDescription("Description de la thématique existante");
        thematiqueExistante.setNiveau(thematique.getNiveau());
        thematiqueExistante.setImagePath("");

        when(repositoryThematique.findById(thematique.getId())).thenReturn(thematique);
        when(repositoryThematique.findById(thematiqueParent.getId())).thenReturn(thematiqueParent);
        when(repositoryThematique.findById(thematiqueExistante.getId())).thenReturn(thematiqueExistante);
        when(repositoryThematique.findByNotIdAndNomAndIdParent(thematique.getId(), thematique.getNom(), thematique.getIdParent())).thenReturn(thematiqueExistante);
        when(repositoryThematique.save(thematique)).thenReturn(thematique); // la modification retourne la thématique

        assertThatThrownBy(() -> managementThematique.updateThematique(thematique))
                .isInstanceOf(AlreadyExistsException.class)
                .hasMessageContaining("GENERIC_ALREADY_EXISTS");
    }

    @Test
    @DisplayName("Update a thematique should succes")
    public void update_a_thematique_should_succes() {
        thematique.setId(10L);
        thematique.setNom("Nom thématique modifié");
        thematique.setDescription("Description thématique");
        thematique.setIdParent(3L);
        thematique.setNiveau(3);
        thematique.setImagePath("");

        ThematiqueEntity thematiqueParent = new ThematiqueEntity();
        thematiqueParent.setId(thematique.getIdParent());
        thematiqueParent.setNom("Parent");
        thematiqueParent.setIdParent(1L);
        thematiqueParent.setDescription("Description thématique parent");
        thematiqueParent.setNiveau(thematique.getNiveau()-1);
        thematiqueParent.setImagePath("");

        when(repositoryThematique.findById(thematique.getId())).thenReturn(thematique);
        when(repositoryThematique.findById(thematiqueParent.getId())).thenReturn(thematiqueParent);
        when(repositoryThematique.findByNotIdAndNomAndIdParent(thematique.getId(), thematique.getNom(), thematique.getIdParent())).thenReturn(null); // pas de conflit de nom
        when(repositoryThematique.save(thematique)).thenReturn(thematique); // la modification retourne la thématique

        ThematiqueEntity thematiqueRetour = managementThematique.updateThematique(thematique);

        assertThat(thematique.getId()).isEqualTo(thematiqueRetour.getId());
        assertThat(thematique.getNom()).isEqualTo(thematiqueRetour.getNom());
        assertThat(thematique.getDescription()).isEqualTo(thematiqueRetour.getDescription());
        assertThat(thematique.getIdParent()).isEqualTo(thematiqueRetour.getIdParent());
        assertThat(thematique.getNiveau()).isEqualTo(thematiqueRetour.getNiveau());
        assertThat(thematique.getImagePath()).isEqualTo(thematiqueRetour.getImagePath());
    }


    @Test
    @DisplayName("Update a thematique niveau 1 with image should succes")
    public void update_a_thematique_niveau1_with_image_should_succes() {
        thematique.setId(10L);
        thematique.setNom("Thématique de niveau 1 avec image");
        thematique.setDescription("Description thématique");
        thematique.setIdParent(0L);
        thematique.setNiveau(3);
        thematique.setImagePath("image.png");

        when(repositoryThematique.findById(thematique.getId())).thenReturn(thematique);
        when(repositoryThematique.findByNotIdAndNomAndIdParent(thematique.getId(), thematique.getNom(), thematique.getIdParent())).thenReturn(null); // pas de conflit de nom
        when(repositoryThematique.save(thematique)).thenReturn(thematique); // la modification retourne la thématique

        ThematiqueEntity thematiqueRetour = managementThematique.updateThematique(thematique);

        assertThat(thematique.getId()).isEqualTo(thematiqueRetour.getId());
        assertThat(thematique.getNom()).isEqualTo(thematiqueRetour.getNom());
        assertThat(thematique.getDescription()).isEqualTo(thematiqueRetour.getDescription());
        assertThat(thematique.getIdParent()).isEqualTo(thematiqueRetour.getIdParent());
        assertThat(thematique.getNiveau()).isEqualTo(thematiqueRetour.getNiveau());
        assertThat(thematique.getImagePath()).isEqualTo(thematiqueRetour.getImagePath());
    }

    @Test
    @DisplayName("Update a thematique with image niveau >1 should fail")
    public void update_a_thematique_with_image_niveau_gt1_should_fail() {
        thematique.setId(10L);
        thematique.setNom("Nom thématique modifié");
        thematique.setDescription("Description thématique");
        thematique.setIdParent(3L);
        thematique.setNiveau(3);
        thematique.setImagePath("image.jpg");

        ThematiqueEntity thematiqueParent = new ThematiqueEntity();
        thematiqueParent.setId(thematique.getIdParent());
        thematiqueParent.setNom("Parent");
        thematiqueParent.setIdParent(1L);
        thematiqueParent.setDescription("Description thématique parent");
        thematiqueParent.setNiveau(thematique.getNiveau()-1);
        thematiqueParent.setImagePath("");

        when(repositoryThematique.findById(thematique.getId())).thenReturn(thematique);
        when(repositoryThematique.findById(thematiqueParent.getId())).thenReturn(thematiqueParent);
        when(repositoryThematique.findByNotIdAndNomAndIdParent(thematique.getId(), thematique.getNom(), thematique.getIdParent())).thenReturn(null); // pas de conflit de nom
        when(repositoryThematique.save(thematique)).thenReturn(thematique); // la modification retourne la thématique

        assertThatThrownBy(() -> managementThematique.updateThematique(thematique))
                .isInstanceOf(ThematiqueDataException.class)
                .hasMessageContaining("COMPULSORY_DATA");
    }

    @Test
    @DisplayName("Find all thematique's children should succes")
    public void find_all_thematique_s_children_should_succes() {
        ThematiqueEntity thematiqueParent = new ThematiqueEntity();
        thematiqueParent.setId(1L);
        thematiqueParent.setNom("Parent");
        thematiqueParent.setIdParent(0L);
        thematiqueParent.setDescription("Description thématique parent");
        thematiqueParent.setNiveau(1);
        thematiqueParent.setImagePath("http://Chemin_image/image.gif");

        thematique.setId(2L);
        thematique.setNom("Nom thématique enfant 1");
        thematique.setDescription("Description thématique 1");
        thematique.setIdParent(thematiqueParent.getId());
        thematique.setNiveau(thematiqueParent.getNiveau()+1);
        thematique.setImagePath("");

        ThematiqueEntity thematiqueDeux = new ThematiqueEntity();
        thematiqueDeux.setId(3L);
        thematiqueDeux.setNom("Nom thématique enfant 2");
        thematiqueDeux.setDescription("Description thématique 2");
        thematiqueDeux.setIdParent(thematiqueParent.getId());
        thematiqueDeux.setNiveau(thematiqueParent.getNiveau()+1);
        thematiqueDeux.setImagePath("");

        ThematiqueEntity thematiqueTrois = new ThematiqueEntity();
        thematiqueTrois.setId(4L);
        thematiqueTrois.setNom("Nom thématique enfant 2");
        thematiqueTrois.setDescription("Description thématique 2");
        thematiqueTrois.setIdParent(thematiqueParent.getId());
        thematiqueTrois.setNiveau(thematiqueParent.getNiveau()+1);
        thematiqueTrois.setImagePath("");

        List<ThematiqueEntity> thematiques = new ArrayList<>();
        thematiques.add(thematique);
        thematiques.add(thematiqueDeux);
        thematiques.add(thematiqueTrois);

        when(repositoryThematique.findAllByIdParent(thematiqueParent.getId())).thenReturn(thematiques);

        assertThat(managementThematique.findThematiquesEnfants(thematiqueParent.getId()).size()).isEqualTo(thematiques.size());
        for (int i = 0; i < thematiques.size(); i++) {
            assertThat(managementThematique.findThematiquesEnfants(thematiqueParent.getId()).get(i).getId().equals(thematiques.get(i).getId()));
        }
    }

    @Test
    @DisplayName("Find a thematique should succes")
    public void find_a_thematique_should_succes() {
        thematique.setId(20L);
        thematique.setNom("Nom thématique");
        thematique.setDescription("Description thématique");
        thematique.setIdParent(19L);
        thematique.setNiveau(2);
        thematique.setImagePath("");

        when(repositoryThematique.findById(thematique.getId())).thenReturn(thematique);

        assertThat(managementThematique.findThematique(thematique.getId()).equals(thematique));
    }

    @Test
    @DisplayName("Find a non existing thematique should return null")
    public void find_a_non_existing_thematique_should_return_null() {
        thematique.setId(20L);

        when(repositoryThematique.findById(thematique.getId())).thenReturn(null);

        assertNull(managementThematique.findThematique(thematique.getId()));
    }

    @Test
    @DisplayName("Delete a non void thematique should fail")
    public void delete_a_non_void_thematique_should_fail() {
        thematique.setId(1000L);
        thematique.setNom("Nom thématique à supprimer");
        thematique.setDescription("Cette thématique n'est pas vide, elle contient 1 lien");
        thematique.setIdParent(1L);
        thematique.setNiveau(2);
        thematique.setImagePath("");

        LienEntity lien = new LienEntity();
        lien.setId(1);
        lien.setNom("un lien pour que la thématique ne soit pas vide");
        lien.setThematique(thematique);
        List<LienEntity> liens = new ArrayList<>();
        liens.add(lien);

        when(repositoryThematique.findById(thematique.getId())).thenReturn(thematique);
        when(repositoryLien.getLiensByIdThematique(thematique.getId())).thenReturn(liens);

        assertThatThrownBy(() -> managementThematique.deleteThematique(thematique.getId()))
                .isInstanceOf(ThematiqueNotEmptyException.class)
                .hasMessageContaining("GENERIC_THEMATIQUE_NOT_EMPTY");
    }

    @Test
    @DisplayName("Delete an existing thematique should succes")
    public void delete_an_existing_thematique_should_succes() {
        thematique.setId(1000L);
        thematique.setNom("Nom thématique à supprimer");
        thematique.setDescription("Cette thématique est vide");
        thematique.setIdParent(1L);
        thematique.setNiveau(2);
        thematique.setImagePath("");

        List<ThematiqueEntity> thematiques = new ArrayList<>();
        List<LienEntity> liens = new ArrayList<>();

        when(repositoryThematique.findById(thematique.getId())).thenReturn(thematique);
        when(repositoryLien.getLiensByIdThematique(thematique.getId())).thenReturn(liens);
        when(repositoryThematique.findAllByIdParent(thematique.getId())).thenReturn(thematiques);

        managementThematique.deleteThematique(thematique.getId());

        verify(repositoryThematique, times(1)).delete(eq(thematique.getId()));
    }

    @Test
    @DisplayName("Delete a non existing thematique should fail")
    public void delete_a_non_existing_thematique_should_fail() {
        thematique.setId(1000L);
        thematique.setNom("Nom thématique à supprimer");
        thematique.setDescription("Cette thématique est vide");
        thematique.setIdParent(1L);
        thematique.setNiveau(2);
        thematique.setImagePath("");

        List<ThematiqueEntity> thematiques = new ArrayList<>();
        List<LienEntity> liens = new ArrayList<>();

        when(repositoryThematique.findById(thematique.getId())).thenReturn(null);
        when(repositoryLien.getLiensByIdThematique(thematique.getId())).thenReturn(liens);
        when(repositoryThematique.findAllByIdParent(thematique.getId())).thenReturn(thematiques);

        assertThatThrownBy(() -> managementThematique.deleteThematique(thematique.getId()))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("GENERIC_NOT_FOUND");
    }

}
