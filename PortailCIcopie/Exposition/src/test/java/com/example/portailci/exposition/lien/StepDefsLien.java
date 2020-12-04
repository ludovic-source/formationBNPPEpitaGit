package com.example.portailci.exposition.lien;

import com.example.portailci.domain.lien.IRepositoryLien;
import com.example.portailci.domain.lien.LienEntity;
import com.example.portailci.domain.thematique.ThematiqueEntity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class StepDefsLien {

    @Autowired
    ControllerLien controllerLien = new ControllerLien();

    @MockBean
    private IRepositoryLien repositoryLien;

    private LienEntity lien = new LienEntity();
    private LienEntity resultLien = new LienEntity();

    @Given("le redacteur utilise le lien")
    public void le_redacteur_utilise_le_lien(){
        this.lien = initLien(1);
    }

    /* ------ NE FONCTIONNE PAS A CAUSE DE L'AUTHENTIFICATION
    @When("le redacteur cree un lien")
    public void le_redacteur_cree_un_lien() {
        //when(repositoryLien.save(this.lien)).thenReturn(this.lien);
        this.resultLien = controllerLien.create(this.lien);
    }

    @Then("le redacteur recupere le lien cree")
    public void le_redacteur_recupere_le_lien_cree(String string) {
        assertThat(this.resultLien).isNotNull();
    }
    */
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
