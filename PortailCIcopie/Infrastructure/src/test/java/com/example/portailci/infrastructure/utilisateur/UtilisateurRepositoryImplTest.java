package com.example.portailci.infrastructure.utilisateur;

import com.example.portailci.domain.droit.DroitEntity;
import com.example.portailci.domain.droit.IRepositoryDroit;
import com.example.portailci.domain.exception.NotFoundException;
import com.example.portailci.domain.profil.IRepositoryProfil;
import com.example.portailci.domain.profil.ProfilEntity;
import com.example.portailci.domain.utilisateur.IRepositoryUtilisateur;
import com.example.portailci.domain.utilisateur.IUtilisateurRefogRepository;
import com.example.portailci.domain.utilisateur.UtilisateurEntity;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestInstance(PER_CLASS)
public class UtilisateurRepositoryImplTest {

    @Autowired
    private IRepositoryUtilisateur repositoryUtilisateur;

    @Autowired
    private IUtilisateurRefogRepository utilisateurRefogRepository;

    @Autowired
    private IRepositoryProfil repositoryProfil;

    @Autowired
    private IRepositoryDroit repositoryDroit;

    private ProfilEntity profil = new ProfilEntity();
    private UtilisateurEntity initUtilisateurEntity = new UtilisateurEntity();
    private DroitEntity droitAdministration = new DroitEntity();
    private DroitEntity droitConsultation = new DroitEntity();
    private DroitEntity droitCreation = new DroitEntity();
    private DroitEntity droitModification = new DroitEntity();
    private DroitEntity droitSuppression = new DroitEntity();

    @BeforeAll
    public void beforeAll() {

        //System.out.println("------------------------- Méthode beforeAll --------------------------------------------");


        // Initialisation des droits
        droitAdministration.setId(1L);
        droitAdministration.setNom("Administration");
        droitAdministration.setDescription("Droit d'administrer");
        droitConsultation.setId(2L);
        droitConsultation.setNom("Consultation");
        droitConsultation.setDescription("Droit de consulter");
        droitCreation.setId(3L);
        droitCreation.setNom("Création");
        droitCreation.setDescription("Droit de créer");
        droitModification.setId(4L);
        droitModification.setNom("Modification");
        droitModification.setDescription("Droit de modifier");
        droitSuppression.setId(5L);
        droitSuppression.setNom("Suppression");
        droitSuppression.setDescription("Droit de supprimer");

        // Initialisation du profil "Administrateur"
        profil.setId(1L);
        profil.setNom("Administrateur");
        profil.setDescription("Admin");
        Set<DroitEntity> droits = new HashSet<>();
        droits.add(droitAdministration);
        droits.add(droitConsultation);
        droits.add(droitCreation);
        droits.add(droitModification);
        droits.add(droitSuppression);
        profil.setDroits(droits);
    }
    @BeforeEach
    public void beforeEach() {

        //System.out.println("------------------------- Méthode beforeEach --------------------------------------------");

        // Initialisation des paramètres de l'utilisateur
        initUtilisateurEntity.setUid("123456");
        initUtilisateurEntity.setNom("ABELLI" );
        initUtilisateurEntity.setPrenom("Rudy" );
        initUtilisateurEntity.setMotDePasse("toto" );
        initUtilisateurEntity.setUoAffectation("12463258" );
        initUtilisateurEntity.setSiteExercice("Tassigny" );
        initUtilisateurEntity.setFonction("CIL" );
        initUtilisateurEntity.setProfil( profil );
        initUtilisateurEntity = repositoryUtilisateur.create(initUtilisateurEntity);

    }

    @AfterEach
    public void afterEach() {
        //System.out.println("------------------------- Méthode afterEach --------------------------------------------");


        System.out.println("Id de l'utilisateur : " + initUtilisateurEntity.getId());
        repositoryUtilisateur.delete(initUtilisateurEntity);
        initUtilisateurEntity.setId(0L);
    }


    @Test
    public void should_return_UtilisateurEntity_when_create_a_new_user() {
        //System.out.println("------------------------- Test  should_return_UtilisateurEntity_when_create_a_new_user()--------------------------------------------");

        UtilisateurEntity createdUtilisateur = initUtilisateurEntity;
        System.out.println(createdUtilisateur.getId());
        assertThat(createdUtilisateur).isInstanceOf(UtilisateurEntity.class);
    }
/*
    @Test
    public void should_return_an_UtilisateurEntity_with_the_same_id_when_update_the_Entity() {

        //System.out.println("------------------------- Test  should_return_an_UtilisateurEntity_with_the_same_id_when_update_the_Entity()--------------------------------------------");

        UtilisateurEntity utilisateurEntityAModifier = initUtilisateurEntity;
        UtilisateurEntity utilisateurEntityModifie = repositoryUtilisateur.update(utilisateurEntityAModifier);

        assertThat(utilisateurEntityAModifier.getId()).isEqualTo(utilisateurEntityModifie.getId());
    }

    @Test
    public void should_return_change_name_when_the_UtilisateurEntity_name_is_updated(){

        UtilisateurEntity utilisateurEntityAModifier = initUtilisateurEntity;
        utilisateurEntityAModifier.setNom("toto");
        UtilisateurEntity utilisateurEntityModifie = repositoryUtilisateur.update(utilisateurEntityAModifier);
        assertThat(utilisateurEntityModifie.getNom()).isEqualTo("toto");

    }

    @Test
    public void should_thrown_NotFoundException_when_search_an_inexisting_id () {

        Long idTest = 10000L;
        Throwable exception = catchThrowable( () -> repositoryUtilisateur.findById(idTest));

        assertThat(exception)
                .isNotNull()
                .isInstanceOf(NotFoundException.class);
    }


    @Test
    public void should_return_null_when_search_an_inexisting_UID_in_REFOG() {
        String inexistingUID = "zzzzzzzzzzzz";

        UtilisateurEntity inexistingUtilisateur = utilisateurRefogRepository.getUtilisateurRefogVOByUid(inexistingUID);

        assertThat(inexistingUtilisateur)
                .isNull();
    }

    @Test
    public void should_return_null_when_search_an_inexisting_UID_in_Database() {
        String inexistingUID = "zzzzzzzzzzzz";

        UtilisateurEntity inexistingUtilisateur = repositoryUtilisateur.findByUid(inexistingUID);

        assertThat(inexistingUtilisateur)
                .isNull();

    }

*/
}
