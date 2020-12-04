package com.example.portailci.infrastructure.lien;

import ch.qos.logback.core.spi.LifeCycle;
import com.example.portailci.domain.lien.IRepositoryLien;
import com.example.portailci.domain.lien.LienEntity;
import com.example.portailci.domain.thematique.IRepositoryThematique;
import com.example.portailci.domain.thematique.ThematiqueEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestInstance(PER_CLASS)
public class TestJpaRepository {

    @Autowired
    private IRepositoryLien repositoryLien;

    @Autowired
    private IRepositoryThematique repositoryThematique;

    private LienEntity lien1 = new LienEntity();
    private LienEntity lien2 = new LienEntity();
    private ThematiqueEntity thematique = new ThematiqueEntity();

    @BeforeAll
    public void beforeAll() {
        thematique = initThematique();
        repositoryThematique.save(thematique);
        lien1 = initLien(1);
        repositoryLien.save(lien1);
        lien2 = initLien(2);
        repositoryLien.save(lien2);
    }

    @Test
    public void tester_save_lien() {
        lien1.setNom("test save");
        LienEntity resultSave = repositoryLien.save(lien1);

        assertThat((resultSave)).isInstanceOf(LienEntity.class);
        assertThat(resultSave.getId()).isNotNull();
        assertThat(resultSave.getNom()).isEqualTo("test save");
        assertThat(resultSave.getUrl()).isEqualTo("http://coucou");
    }

    @Test
    public void tester_echec_save_lien_avec_nom_null() {
        LienEntity lienLocal = initLien(3);
        lienLocal.setNom(null);
        assertThatThrownBy(() -> {
            repositoryLien.save(lienLocal);
        } ).isInstanceOf(javax.validation.ConstraintViolationException.class)
                .hasMessageContaining("ne doit pas être nul");
    }

    @Test
    public void tester_echec_save_lien_avec_description_null() {
        LienEntity lienLocal = initLien(4);
        lienLocal.setDescription(null);
        assertThatThrownBy(() -> {
            repositoryLien.save(lienLocal);
        } ).isInstanceOf(javax.validation.ConstraintViolationException.class)
                .hasMessageContaining("ne doit pas être nul");
    }

    @Test
    public void tester_echec_save_lien_avec_url_null() {
        LienEntity lienLocal = initLien(5);
        lienLocal.setUrl(null);
        assertThatThrownBy(() -> {
            repositoryLien.save(lienLocal);
        } ).isInstanceOf(javax.validation.ConstraintViolationException.class)
                .hasMessageContaining("ne doit pas être nul");
    }

    @Test
    public void tester_echec_save_lien_avec_statut_null() {
        LienEntity lienLocal = initLien(6);
        lienLocal.setStatut(null);
        assertThatThrownBy(() -> {
            repositoryLien.save(lienLocal);
        } ).isInstanceOf(javax.validation.ConstraintViolationException.class)
                .hasMessageContaining("ne doit pas être nul");
    }

    @Test
    public void tester_echec_save_lien_avec_mode_affichage_null() {
        LienEntity lienLocal = initLien(7);
        lienLocal.setMode_affichage(null);
        assertThatThrownBy(() -> {
            repositoryLien.save(lienLocal);
        } ).isInstanceOf(javax.validation.ConstraintViolationException.class)
                .hasMessageContaining("ne doit pas être nul");
    }

    @Test
    public void tester_echec_save_lien_avec_date_publication_restreinte_null() {
        LienEntity lienLocal = initLien(8);
        lienLocal.setDate_publication_restreinte(null);
        assertThatThrownBy(() -> {
            repositoryLien.save(lienLocal);
        } ).isInstanceOf(javax.validation.ConstraintViolationException.class)
                .hasMessageContaining("ne doit pas être nul");
    }

    @Test
    public void tester_findById_lien_found() {

        LienEntity resultFindById = repositoryLien.findById(lien1.getId());
        assertThat((resultFindById)).isInstanceOf(LienEntity.class);
        assertThat(resultFindById.getId()).isNotNull();
        assertThat(resultFindById.getId()).isEqualTo(1);
        assertThat(resultFindById.getUrl()).isEqualTo("http://coucou");
    }

    @Test
    public void tester_findById_lien_not_found() {
        LienEntity resultFindById = repositoryLien.findById(1234);
        assertThat(resultFindById).isNull();
    }

    @Test
    public void tester_getLiensByIdThematique_found() {

        List<LienEntity> resultGet = repositoryLien.getLiensByIdThematique((long) 1);
        assertThat(resultGet.size()).isEqualTo(2);

        Integer id = 0;
        for (LienEntity chaqueLien : resultGet) {
            id += 1;
            assertThat(chaqueLien).isInstanceOf(LienEntity.class);
            assertThat(chaqueLien.getId()).isEqualTo(id);
            assertThat(chaqueLien.getUrl()).isEqualTo("http://coucou");
        }
    }

    @Test
    public void tester_getLiensByIdThematique_not_found() {
        List<LienEntity> resultGet = repositoryLien.getLiensByIdThematique((long) 1234);
        assertThat(resultGet).isEmpty();
        assertThat(resultGet.size()).isEqualTo(0);
    }

    @Test
    public void tester_findByStatut_publie_found() {
        lien1.setStatut("publié");
        repositoryLien.save(lien1);
        lien2.setStatut("publié");
        repositoryLien.save(lien2);

        List<LienEntity> resultFindByStatut = repositoryLien.findByStatut("publié");
        assertThat(resultFindByStatut.size()).isEqualTo(2);

        Integer id = 0;
        for (LienEntity chaqueLien : resultFindByStatut) {
            id += 1;
            assertThat(chaqueLien).isInstanceOf(LienEntity.class);
            assertThat(chaqueLien.getId()).isEqualTo(id);
            assertThat(chaqueLien.getStatut()).isEqualTo("publié");
        }
    }

    @Test
    public void tester_findByStatut_depublie_found() {
        lien1.setStatut("dépublié");
        repositoryLien.save(lien1);
        lien2.setStatut("dépublié");
        repositoryLien.save(lien2);

        List<LienEntity> resultFindByStatut = repositoryLien.findByStatut("dépublié");
        assertThat(resultFindByStatut.size()).isEqualTo(2);

        Integer id = 0;
        for (LienEntity chaqueLien : resultFindByStatut) {
            id += 1;
            assertThat(chaqueLien).isInstanceOf(LienEntity.class);
            assertThat(chaqueLien.getId()).isEqualTo(id);
            assertThat(chaqueLien.getStatut()).isEqualTo("dépublié");
        }
    }

    @Test
    public void tester_findByStatut_publie_restreint_found() {

        lien1.setStatut("publié restreint");
        repositoryLien.save(lien1);
        lien2.setStatut("publié restreint");
        repositoryLien.save(lien2);

        List<LienEntity> resultFindByStatut = repositoryLien.findByStatut("publié restreint");
        assertThat(resultFindByStatut.size()).isEqualTo(2);

        Integer id = 0;
        for (LienEntity chaqueLien : resultFindByStatut) {
            id += 1;
            assertThat(chaqueLien).isInstanceOf(LienEntity.class);
            assertThat(chaqueLien.getId()).isEqualTo(id);
            assertThat(chaqueLien.getStatut()).isEqualTo("publié restreint");
        }
    }

    @Test
    public void tester_findByStatut_not_found() {
        List<LienEntity> resultfindByStatut = repositoryLien.findByStatut("mauvais statut");
        assertThat(resultfindByStatut).isEmpty();
        assertThat(resultfindByStatut.size()).isEqualTo(0);
    }

    private LienEntity initLien(Integer id) {
        LienEntity lien = new LienEntity();
        ThematiqueEntity thematique = initThematique();
        lien.setThematique(thematique);
        lien.setId(id);
        lien.setNom("lien de test");
        lien.setUrl("http://coucou");
        lien.setMode_affichage(true);
        lien.setStatut("publié restreint");
        LocalDateTime currentTime = LocalDateTime.now();
        lien.setDate_publication_restreinte(currentTime);
        lien.setDescription("lien créé pour les tests unitaires");
        return lien;
    }

    private ThematiqueEntity initThematique() {
        ThematiqueEntity thematique = new ThematiqueEntity();
        thematique.setDescription("sous thème spécial test");
        thematique.setIdParent((long) 8);
        thematique.setId((long) 1);
        thematique.setImagePath("non connu");
        thematique.setNiveau(2);
        thematique.setNom("test");
        return thematique;
    }

}
