package com.example.portailci.application.utilisateur;


import com.example.portailci.domain.exception.AlreadyExistsException;
import com.example.portailci.domain.exception.NotFoundException;
import com.example.portailci.domain.profil.ProfilEntity;
import com.example.portailci.domain.utilisateur.IRepositoryUtilisateur;
import com.example.portailci.domain.utilisateur.IUtilisateurRefogRepository;
import com.example.portailci.domain.utilisateur.UtilisateurEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UtilisateurManagementImplTest {

    @Autowired
    private IUtilisateurManagement utilisateurManagement;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private IRepositoryUtilisateur repositoryUtilisateur;

    @MockBean
    private IUtilisateurRefogRepository utilisateurRefogRepository;

    private UtilisateurEntity expositionUtilisateurEntity;
    private UtilisateurEntity createdUtilisateur;
    private UtilisateurEntity alreadyExistUtilisateur;
    private List<UtilisateurEntity> utilisateurEntityList;

    @BeforeEach
    public void beforeEach() {
        expositionUtilisateurEntity = new UtilisateurEntity();
        expositionUtilisateurEntity.setUid("147963");
        expositionUtilisateurEntity.setNom("Musk");
        expositionUtilisateurEntity.setPrenom("Elon");
        expositionUtilisateurEntity.setMotDePasse("tesla");
        expositionUtilisateurEntity.setUoAffectation("78945A63");
        expositionUtilisateurEntity.setSiteExercice("Los Angeles");
        expositionUtilisateurEntity.setFonction("CEO SpaceX");
        expositionUtilisateurEntity.setProfil(new ProfilEntity(3L));

        createdUtilisateur = new UtilisateurEntity(
                1000L,
                expositionUtilisateurEntity.getUid(),
                expositionUtilisateurEntity.getNom(),
                expositionUtilisateurEntity.getPrenom(),
                expositionUtilisateurEntity.getMotDePasse(),
                expositionUtilisateurEntity.getUoAffectation(),
                expositionUtilisateurEntity.getSiteExercice(),
                expositionUtilisateurEntity.getFonction(),
                expositionUtilisateurEntity.getProfil()
        );
        alreadyExistUtilisateur = new UtilisateurEntity(
                2000L,
                expositionUtilisateurEntity.getUid(),
                expositionUtilisateurEntity.getNom(),
                expositionUtilisateurEntity.getPrenom(),
                expositionUtilisateurEntity.getMotDePasse(),
                expositionUtilisateurEntity.getUoAffectation(),
                expositionUtilisateurEntity.getSiteExercice(),
                expositionUtilisateurEntity.getFonction(),
                expositionUtilisateurEntity.getProfil()
        );


        utilisateurEntityList = new ArrayList<>();
        utilisateurEntityList.add(expositionUtilisateurEntity);
        utilisateurEntityList.add(createdUtilisateur);
        utilisateurEntityList.add(alreadyExistUtilisateur);
    }

    @AfterEach
    public void afterEach() {
        utilisateurEntityList = null;
        expositionUtilisateurEntity = null;
        createdUtilisateur = null;
    }
//region Tests sur la fonction de création
    @Test
    public void should_return_a_new_Utilisateur_with_Id_when_create_Utilisateur_with_an_inexisting_UID () {

        when(repositoryUtilisateur.create(any(UtilisateurEntity.class))).thenReturn(createdUtilisateur);
        when(repositoryUtilisateur.findByUid(anyString())).thenReturn(null);

        assertThat(utilisateurManagement.create(expositionUtilisateurEntity))
                                            .isNotNull()
                                            .isInstanceOf(UtilisateurEntity.class)
                                            .hasFieldOrPropertyWithValue("id",1000L);
    }

    @Test
    public void should_throw_AlreadyExistsException_when_try_to_create_a_Utilisateur_with_an_existing_UID() {
            when(repositoryUtilisateur.create(any(UtilisateurEntity.class))).thenThrow(new AlreadyExistsException("L'IUD " + expositionUtilisateurEntity.getUid() + " existe déjà, merci de bien vouloir modifier l'utiliseur existant ou de changer l'UID à créer."));
            assertThrows(AlreadyExistsException.class, () -> utilisateurManagement.create(expositionUtilisateurEntity));
    }
//endregion

//region Tests sur les fonctions de recherche
    @Test
    public void should_succes_when_find_an_existing_Id() {
        when(repositoryUtilisateur.findById(1000L)).thenReturn(createdUtilisateur);

        assertAll(
                () -> assertThat(utilisateurManagement.findByID(1000L)).isInstanceOf(UtilisateurEntity.class),
                () -> assertThat(utilisateurManagement.findByID(1000L)).isNotNull()
                                                                        .hasFieldOrPropertyWithValue("nom","Musk")
                                                                        .hasFieldOrPropertyWithValue("prenom", "Elon")
        );
    }

    @Test
    public void should_throw_a_NotFoundException_when_search_an_inexting_Id() {
        Long researchingId = 1000L;
        when(repositoryUtilisateur.findById(1000L)).thenThrow(new NotFoundException("Aucun utilisateur portant l'id : " + researchingId + " n'a été trouvé."));

        assertThrows(NotFoundException.class, () -> utilisateurManagement.findByID(researchingId));
    }

    @Test
    public void should_return_a_Set_of_Utilisateurs_when_search_all_Utilisateurs() {
        when(repositoryUtilisateur.findAll()).thenReturn(utilisateurEntityList);

        assertThat(utilisateurManagement.findAll()).isNotNull();
        assertThat(utilisateurManagement.findAll().size()).isEqualTo(3);

    }

    @Test
    public void should_return_an_UtilisateurEntity_without_all_attributes_when_searching_an_Utilisateur_in_REFOG() {
        UtilisateurEntity refogUtilisateur = new UtilisateurEntity();
        refogUtilisateur.setNom("Federer");
        refogUtilisateur.setPrenom("Roger");
        refogUtilisateur.setUid("111111");
        refogUtilisateur.setFonction("Champion de tennis");
        refogUtilisateur.setUoAffectation("74185P56");
        refogUtilisateur.setSiteExercice("Wimbledon");

        when(utilisateurRefogRepository.getUtilisateurRefogVOByUid(anyString())).thenReturn(refogUtilisateur);

        assertThat(utilisateurManagement.findByUid(refogUtilisateur.getUid(), true))
                .isInstanceOf(UtilisateurEntity.class)
                .hasNoNullFieldsOrPropertiesExcept("id", "motDePasse", "profil");
    }

    @Test
    public void should_return_an_UtilisateurEntity_with_all_attributes_when_search_an_existing_UID_in_database() {
        when(repositoryUtilisateur.findByUid(anyString())).thenReturn(alreadyExistUtilisateur);

        assertThat(utilisateurManagement.findByUid(alreadyExistUtilisateur.getUid(),false))
                .isInstanceOf(UtilisateurEntity.class)
                .hasNoNullFieldsOrProperties();
    }


    //endregion

//region Tests sur la fonction de suppression
/*    @Test
    public void should_throw_a_NotFoundException_when_try_to_delete_an_inexisting_Id(){

        Long deletingId = 1000L;
        expositionUtilisateurEntity.setId(deletingId);

        when(repositoryUtilisateur.findById(anyLong())).thenThrow(new NotFoundException("Aucun utilisateur portant l'id : " + deletingId + " n'a été trouvé."));
        assertThatThrownBy(() -> utilisateurManagement.delete(deletingId))
                .isInstanceOf(NotFoundException.class);
    }*/

/*    @Test
    public void should_success_when_delete_an_existing_Utilisateur() {
        when(repositoryUtilisateur.findById(alreadyExistUtilisateur.getId())).thenReturn(alreadyExistUtilisateur);

        utilisateurManagement.delete(alreadyExistUtilisateur.getId());
        verify(repositoryUtilisateur, times(1)).delete(eq(alreadyExistUtilisateur));
    }*/

    //endregion

//region Tests sur la fonction de modification

/*    @Test
    public void should_success_when_update_an_Utilisateur() {
        UtilisateurEntity modifiedUtilisateur = new UtilisateurEntity();
        String modifiedUoAffectation = "88888I99";
        String modifiedSiteExercice = "Mac 19";
        modifiedUtilisateur.setId(alreadyExistUtilisateur.getId());
        modifiedUtilisateur.setNom(alreadyExistUtilisateur.getNom());
        modifiedUtilisateur.setPrenom(alreadyExistUtilisateur.getPrenom());
        modifiedUtilisateur.setMotDePasse(alreadyExistUtilisateur.getMotDePasse());
        modifiedUtilisateur.setUid(alreadyExistUtilisateur.getUid());
        modifiedUtilisateur.setUoAffectation(modifiedUoAffectation);
        modifiedUtilisateur.setSiteExercice(modifiedSiteExercice);

        when(repositoryUtilisateur.update(modifiedUtilisateur)).thenReturn(modifiedUtilisateur);

        assertThat(utilisateurManagement.update(modifiedUtilisateur))
                .isNotNull()
                .isInstanceOf(UtilisateurEntity.class);
        assertThat(alreadyExistUtilisateur.getUoAffectation()).isNotEqualTo(modifiedUtilisateur.getUoAffectation());
        assertThat(alreadyExistUtilisateur.getSiteExercice()).isNotEqualTo(modifiedUtilisateur.getSiteExercice());


    }*/
//endregion



}
