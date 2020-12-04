package com.example.portailci.application.lien;

import com.example.portailci.application.thematique.IManagementThematique;
import com.example.portailci.domain.lien.IRepositoryLien;
import com.example.portailci.domain.lien.LienEntity;
import com.example.portailci.domain.thematique.ThematiqueEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestManagementLienImpl {

    @Autowired
    private IManagementLien managementLien;

    @MockBean
    private IManagementThematique managementThematique;

    @MockBean
    private IRepositoryLien repositoryLien;

    private LienEntity lien = new LienEntity();

    @Test
    public void tester_createLien_controle_presence_thematique() {
        initLienEntreeSansThematique();
        assertThatThrownBy(() -> {
            managementLien.createLien(lien);
        } ).isInstanceOf(LienControleDonneesException.class)
                .hasMessageContaining("demande impossible : la thématique n'est pas renseignée");
    }

    @Test
    public void tester_createLien_reponse_ok() {
        initLienEntree();
        LienEntity resultLienMock = initResultMock();
        resultLienMock.setStatut("publié restreint");

        when(repositoryLien.save(lien)).thenReturn(resultLienMock);
        when(managementThematique.findThematique(lien.getThematique().getId()))
                .thenReturn(lien.getThematique());

        assertThat(managementLien.createLien(lien)).isEqualTo(resultLienMock);
    }

    @Test
    public void tester_createLien_reponse_ok_controle_statut() {
        initLienEntree();
        LienEntity resultLienMock = initResultMock();
        resultLienMock.setStatut("publié restreint");

        when(repositoryLien.save(lien)).thenReturn(resultLienMock);
        when(managementThematique.findThematique(lien.getThematique().getId()))
                .thenReturn(lien.getThematique());

        LienEntity resultLien = managementLien.createLien(lien);

        assertThat(resultLien.getStatut()).isEqualTo("publié restreint");
    }

    @Test
    public void tester_createLien_reponse_ok_controle_dates() {
        initLienEntree();
        LienEntity resultLienMock = initResultMock();
        LocalDateTime date = LocalDateTime.now();
        resultLienMock.setDate_publication_restreinte(date);

        when(repositoryLien.save(lien)).thenReturn(resultLienMock);
        when(managementThematique.findThematique(lien.getThematique().getId()))
                .thenReturn(lien.getThematique());

        LienEntity resultLien = managementLien.createLien(lien);

        assertThat(resultLien.getDate_depublication()).isNull();
        assertThat(resultLien.getDate_publication()).isNull();
        assertThat(resultLien.getDate_publication_restreinte()).isEqualTo(date);
    }

    @Test
    public void tester_prepublierLien_controle_existence_Lien() {
        initLienEntree();
        when(repositoryLien.findById(1)).thenReturn(null);
        assertThatThrownBy(() -> {
            managementLien.prePublierLien(lien);
        } ).isInstanceOf(LienNotFoundException.class)
                .hasMessageContaining("pré publication impossible : le lien n'existe pas");
    }

    @Test
    public void tester_prepublierLien_controle_presence_thematique() {
        initLienEntreeSansThematique();
        assertThatThrownBy(() -> {
            managementLien.prePublierLien(lien);
        } ).isInstanceOf(LienControleDonneesException.class)
                .hasMessageContaining("demande impossible : la thématique n'est pas renseignée");
    }

    @Test
    public void tester_prepublierLien_reponse_ok() {
        initLienEntree();
        LienEntity resultLienMock = initResultMock();
        resultLienMock.setStatut("publié restreint");

        when(repositoryLien.findById(lien.getId())).thenReturn(lien);
        when(repositoryLien.save(lien)).thenReturn(resultLienMock);

        assertThat(managementLien.prePublierLien(lien)).isEqualTo(resultLienMock);
    }

    @Test
    public void tester_prepublierLien_reponse_ok_controle_statut() {
        initLienEntree();
        LienEntity resultLienMock = new LienEntity();
        resultLienMock.setId(1);
        resultLienMock.setNom("lien de test");
        resultLienMock.setStatut("publié restreint");

        when(repositoryLien.findById(lien.getId())).thenReturn(lien);
        when(repositoryLien.save(lien)).thenReturn(resultLienMock);

        LienEntity resultLien = managementLien.prePublierLien(lien);

        assertThat(resultLien.getStatut()).isEqualTo("publié restreint");
    }

    @Test
    public void tester_prepublierLien_reponse_ok_controle_dates() {
        initLienEntree();
        LienEntity resultLienMock = initResultMock();
        LocalDateTime date = LocalDateTime.now();
        resultLienMock.setDate_publication_restreinte(date);

        when(repositoryLien.save(lien)).thenReturn(resultLienMock);
        when(repositoryLien.findById(lien.getId())).thenReturn(lien);

        LienEntity resultLien = managementLien.prePublierLien(lien);

        assertThat(resultLien.getDate_depublication()).isNull();
        assertThat(resultLien.getDate_publication()).isNull();
        assertThat(resultLien.getDate_publication_restreinte()).isEqualTo(date);
    }

    @Test
    public void tester_publierLien_controle_existence_Lien() {
        initLienEntree();
        when(repositoryLien.findById(1)).thenReturn(null);
        assertThatThrownBy(() -> {
            managementLien.publierLien(lien);
        } ).isInstanceOf(LienNotFoundException.class)
                .hasMessageContaining("publication impossible : le lien n'existe pas");
    }

    @Test
    public void tester_publierLien_controle_presence_thematique() {
        initLienEntreeSansThematique();
        assertThatThrownBy(() -> {
            managementLien.publierLien(lien);
        } ).isInstanceOf(LienControleDonneesException.class)
                .hasMessageContaining("demande impossible : la thématique n'est pas renseignée");
    }

    @Test
    public void tester_publierLien_reponse_ok() {
        initLienEntree();
        LienEntity resultLienMock = initResultMock();
        resultLienMock.setStatut("publié");

        when(repositoryLien.findById(lien.getId())).thenReturn(lien);
        when(repositoryLien.save(lien)).thenReturn(resultLienMock);

        assertThat(managementLien.publierLien(lien)).isEqualTo(resultLienMock);
    }

    @Test
    public void tester_publierLien_reponse_ok_controle_statut() {
        initLienEntree();
        LienEntity resultLienMock = new LienEntity();
        resultLienMock.setId(1);
        resultLienMock.setNom("lien de test");
        resultLienMock.setStatut("publié");

        when(repositoryLien.findById(lien.getId())).thenReturn(lien);
        when(repositoryLien.save(lien)).thenReturn(resultLienMock);

        LienEntity resultLien = managementLien.publierLien(lien);

        assertThat(resultLien.getStatut()).isEqualTo("publié");
    }

    @Test
    public void tester_publierLien_reponse_ok_controle_dates() {
        initLienEntree();
        LienEntity resultLienMock = initResultMock();
        LocalDateTime date = LocalDateTime.now();
        resultLienMock.setDate_publication(date);
        resultLienMock.setDate_publication_restreinte(date);

        when(repositoryLien.save(lien)).thenReturn(resultLienMock);
        when(repositoryLien.findById(lien.getId())).thenReturn(lien);

        LienEntity resultLien = managementLien.publierLien(lien);

        assertThat(resultLien.getDate_depublication()).isNull();
        assertThat(resultLien.getDate_publication()).isEqualTo(date);
        assertThat(resultLien.getDate_publication_restreinte()).isNotNull();
    }

    @Test
    public void tester_depublierLien_reponse_ok() {
        initLienEntree();
        LienEntity resultLienMock = initResultMock();
        resultLienMock.setStatut("dépublié");

        when(repositoryLien.findById(lien.getId())).thenReturn(lien);
        when(repositoryLien.save(lien)).thenReturn(resultLienMock);

        assertThat(managementLien.depublierLien(lien)).isEqualTo(resultLienMock);
    }

    @Test
    public void tester_depublierLien_reponse_ok_controle_statut() {
        initLienEntree();
        LienEntity resultLienMock = new LienEntity();
        resultLienMock.setId(1);
        resultLienMock.setNom("lien de test");
        resultLienMock.setStatut("dépublié");

        when(repositoryLien.findById(lien.getId())).thenReturn(lien);
        when(repositoryLien.save(lien)).thenReturn(resultLienMock);

        LienEntity resultLien = managementLien.depublierLien(lien);

        assertThat(resultLien.getStatut()).isEqualTo("dépublié");
    }

    @Test
    public void tester_depublierLien_reponse_ok_controle_dates() {
        initLienEntree();
        LienEntity resultLienMock = initResultMock();
        LocalDateTime date = LocalDateTime.now();
        resultLienMock.setDate_publication(date);
        resultLienMock.setDate_publication_restreinte(date);
        resultLienMock.setDate_depublication(date);

        when(repositoryLien.save(lien)).thenReturn(resultLienMock);
        when(repositoryLien.findById(lien.getId())).thenReturn(lien);

        LienEntity resultLien = managementLien.depublierLien(lien);

        assertThat(resultLien.getDate_depublication()).isEqualTo(date);

    }

    @Test
    public void tester_depublierLien_controle_existence_Lien() {
        initLienEntree();
        when(repositoryLien.findById(1)).thenReturn(null);
        assertThatThrownBy(() -> {
            managementLien.depublierLien(lien);
        } ).isInstanceOf(LienNotFoundException.class)
                .hasMessageContaining("dépublication impossible : le lien n'existe pas");
    }

    @Test
    public void tester_findById_controle_non_existence_Lien() {
        when(repositoryLien.findById(1)).thenReturn(null);
        assertThat(managementLien.findById(1)).isEqualTo(null);
    }

    @Test
    public void tester_findById_controle_Lien_trouve() {
        lien.setId(1);
        lien.setNom("lien de test");
        when(repositoryLien.findById(1)).thenReturn(lien);
        assertThat(managementLien.findById(1)).isEqualTo(lien);
    }

    @Test
    public void tester_getLiensByIdThematique_non_existence_Lien() {
        Long idThematique = Long.valueOf(1);
        when(repositoryLien.getLiensByIdThematique(idThematique)).thenReturn(null);
        assertThat(managementLien.findById(1)).isEqualTo(null);
    }

    @Test
    public void tester_getLiensByIdThematique__Lien_non_vide() {
        Long idThematique = Long.valueOf(1);
        List<LienEntity> listeLiens = new ArrayList<>();
        when(repositoryLien.getLiensByIdThematique(idThematique)).thenReturn(listeLiens);
        List<LienEntity> listeLiensTrouves = managementLien.getLiensByIdThematique(idThematique);
        assertThat(listeLiensTrouves.size()).isEqualTo(0);
    }

    @Test
    public void tester_getLiensByIdThematique_controle_Lien_trouve() {
        List<LienEntity> listeLiens = new ArrayList<>();
        lien.setId(1);
        lien.setNom("lien de test");
        listeLiens.add(lien);
        when(repositoryLien.getLiensByIdThematique((long) 1)).thenReturn(listeLiens);
        List<LienEntity> listeLiensTrouves = managementLien.getLiensByIdThematique((long) 1);
        assertThat(listeLiensTrouves.size()).isEqualTo(1);
        assertThat(listeLiens.get(0)).isInstanceOf(LienEntity.class);
        assertThat(listeLiensTrouves.get(0).getNom()).isEqualTo(listeLiens.get(0).getNom());
    }

    @Test
    public void tester_getAllLiensDepublies_controle_Lien_trouve() {
        List<LienEntity> listeLiens = new ArrayList<>();
        lien.setNom("lien de test");
        listeLiens.add(lien);
        listeLiens.add(lien);

        when(repositoryLien.findByStatut(any(String.class))).thenReturn(listeLiens);

        List<LienEntity> resultLiens = managementLien.getAllLiensDepublies();
        assertThat(resultLiens.size()).isEqualTo(2);
        for (LienEntity lien : resultLiens) {
            assertThat(lien).isInstanceOf(LienEntity.class);
            assertThat(lien.getNom()).isEqualTo("lien de test");
        }
    }

    @Test
    public void tester_controle_attribut_obligatoire_nom_pour_un_lien() {
        initLienEntree();
        lien.setNom(null);
        assertThatThrownBy(() -> {
            managementLien.createLien(lien);
        } ).isInstanceOf(LienControleDonneesException.class)
                .hasMessageContaining("les champs du lien ne sont pas correctement renseignés");
        assertThatThrownBy(() -> {
            managementLien.prePublierLien(lien);
        } ).isInstanceOf(LienControleDonneesException.class)
                .hasMessageContaining("les champs du lien ne sont pas correctement renseignés");
        assertThatThrownBy(() -> {
            managementLien.publierLien(lien);
        } ).isInstanceOf(LienControleDonneesException.class)
                .hasMessageContaining("les champs du lien ne sont pas correctement renseignés");
        assertThatThrownBy(() -> {
            managementLien.depublierLien(lien);
        } ).isInstanceOf(LienControleDonneesException.class)
                .hasMessageContaining("les champs du lien ne sont pas correctement renseignés");
    }

    @Test
    public void tester_controle_attribut_obligatoire_description_pour_un_lien() {
        initLienEntree();
        lien.setDescription(null);
        assertThatThrownBy(() -> {
            managementLien.createLien(lien);
        } ).isInstanceOf(LienControleDonneesException.class)
                .hasMessageContaining("les champs du lien ne sont pas correctement renseignés");
        assertThatThrownBy(() -> {
            managementLien.prePublierLien(lien);
        } ).isInstanceOf(LienControleDonneesException.class)
                .hasMessageContaining("les champs du lien ne sont pas correctement renseignés");
        assertThatThrownBy(() -> {
            managementLien.publierLien(lien);
        } ).isInstanceOf(LienControleDonneesException.class)
                .hasMessageContaining("les champs du lien ne sont pas correctement renseignés");
        assertThatThrownBy(() -> {
            managementLien.depublierLien(lien);
        } ).isInstanceOf(LienControleDonneesException.class)
                .hasMessageContaining("les champs du lien ne sont pas correctement renseignés");
    }

    @Test
    public void tester_controle_attribut_obligatoire_url_pour_un_lien() {
        initLienEntree();
        lien.setUrl(null);
        assertThatThrownBy(() -> {
            managementLien.createLien(lien);
        } ).isInstanceOf(LienControleDonneesException.class)
                .hasMessageContaining("les champs du lien ne sont pas correctement renseignés");
        assertThatThrownBy(() -> {
            managementLien.prePublierLien(lien);
        } ).isInstanceOf(LienControleDonneesException.class)
                .hasMessageContaining("les champs du lien ne sont pas correctement renseignés");
        assertThatThrownBy(() -> {
            managementLien.publierLien(lien);
        } ).isInstanceOf(LienControleDonneesException.class)
                .hasMessageContaining("les champs du lien ne sont pas correctement renseignés");
        assertThatThrownBy(() -> {
            managementLien.depublierLien(lien);
        } ).isInstanceOf(LienControleDonneesException.class)
                .hasMessageContaining("les champs du lien ne sont pas correctement renseignés");
    }

    @Test
    public void tester_controle_attribut_obligatoire_mode_affichage_pour_un_lien() {
        initLienEntree();
        lien.setMode_affichage(null);
        assertThatThrownBy(() -> {
            managementLien.createLien(lien);
        } ).isInstanceOf(LienControleDonneesException.class)
                .hasMessageContaining("les champs du lien ne sont pas correctement renseignés");
        assertThatThrownBy(() -> {
            managementLien.prePublierLien(lien);
        } ).isInstanceOf(LienControleDonneesException.class)
                .hasMessageContaining("les champs du lien ne sont pas correctement renseignés");
        assertThatThrownBy(() -> {
            managementLien.publierLien(lien);
        } ).isInstanceOf(LienControleDonneesException.class)
                .hasMessageContaining("les champs du lien ne sont pas correctement renseignés");
        assertThatThrownBy(() -> {
            managementLien.depublierLien(lien);
        } ).isInstanceOf(LienControleDonneesException.class)
                .hasMessageContaining("les champs du lien ne sont pas correctement renseignés");
    }

    private void initLienEntree() {
        ThematiqueEntity thematique = new ThematiqueEntity();
        thematique.setId((long) 1);
        lien.setThematique(thematique);
        lien.setNom("lien de test");
        lien.setUrl("http://coucou");
        lien.setMode_affichage(true);
        lien.setDescription("lien créé pour les tests unitaires");
    }

    private void initLienEntreeSansThematique() {
        lien.setNom("lien de test");
        lien.setUrl("http://coucou");
        lien.setMode_affichage(true);
        lien.setDescription("lien créé pour les tests unitaires");
    }

    private LienEntity initResultMock() {
        ThematiqueEntity thematique = new ThematiqueEntity();
        thematique.setId((long) 1);
        LienEntity resultLienMock = new LienEntity();
        resultLienMock.setThematique(thematique);
        resultLienMock.setId(1);
        resultLienMock.setNom("lien de test");
        resultLienMock.setDate_publication_restreinte(null);
        resultLienMock.setDate_publication(null);
        resultLienMock.setDate_depublication(null);
        return resultLienMock;
    }
}
