package com.example.portailci.application.profil;

import com.example.portailci.domain.droit.DroitEntity;
import com.example.portailci.domain.exception.NotFoundException;
import com.example.portailci.domain.profil.IRepositoryProfil;
import com.example.portailci.domain.profil.ProfilEntity;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ProfilManagementImpl.class)
@ExtendWith(SpringExtension.class)
public class ProfilManagementImplTest {
    @Autowired
    private IProfilManagement profilManagement;
    @MockBean
    private IRepositoryProfil repositoryProfil;

    // profils de base pour tests
    ProfilEntity administrateur = new ProfilEntity();
    ProfilEntity dieu = new ProfilEntity();
    ProfilEntity redacteur = new ProfilEntity();
    ProfilEntity utilisateur = new ProfilEntity();
    Set<ProfilEntity> profils = new HashSet<>();
    int nombredeProfils = 0;

    // droits
    DroitEntity consultation = new DroitEntity();
    DroitEntity creation = new DroitEntity();
    DroitEntity modification = new DroitEntity();
    DroitEntity suppression = new DroitEntity();
    DroitEntity administration = new DroitEntity();
    Set<DroitEntity> droits = new HashSet<>();

    private void init() {
        /* Droits */
        consultation.setId(1L);
        consultation.setNom("Consultation");
        consultation.setDescription("Droit de consultation");
        creation.setId(2L);
        creation.setNom("Création");
        creation.setDescription("Droit de création");
        modification.setId(3L);
        modification.setNom("Modification");
        modification.setDescription("Droit de modification");
        suppression.setId(4L);
        suppression.setNom("Suppression");
        suppression.setDescription("Droit de suppression");
        administration.setId(5L);
        administration.setNom("Administration");
        administration.setDescription("Droit d'administration");

        /* Profils */
        administrateur.setId(1L);
        administrateur.setNom("Administrateur");
        administrateur.setDescription("Gestion des utilisateurs et profils");
        droits.clear();
        droits.add(administration);
        droits.add(consultation);
        droits.add(creation);
        droits.add(modification);
        droits.add(suppression);
        administrateur.setDroits(droits);
        profils.add(administrateur);
        nombredeProfils++;

        redacteur.setId(2L);
        redacteur.setNom("Rédacteur");
        redacteur.setDescription("Gestion de liens et thématiques");
        droits.clear();
        droits.add(consultation);
        droits.add(creation);
        droits.add(modification);
        droits.add(suppression);
        redacteur.setDroits(droits);
        profils.add(redacteur);
        nombredeProfils++;

        utilisateur.setId(3L);
        utilisateur.setNom("Utilisateur");
        utilisateur.setDescription("Utilisation du Portail CI");
        droits.clear();
        droits.add(consultation);
        utilisateur.setDroits(droits);
        profils.add(utilisateur);
        nombredeProfils++;

        System.out.println("***************************************");
        System.out.println("*         Bilan initialisation        *");
        System.out.println("* Nombre de profils : " + profils.size() + "               *");
        System.out.println("***************************************");
    }

    // TOUS LES TESTS SONT KO - A CORRIGER
    /*
    @Test
    @DisplayName("Find all profils should succes")
    public void find_all_profils_should_succes() {
        init();

        when(repositoryProfil.findAll()).thenReturn(profils);

        assertThat(profilManagement.findAll()).contains(administrateur);
        assertThat(profilManagement.findAll()).contains(redacteur);
        assertThat(profilManagement.findAll()).contains(utilisateur);
        assertThat(profilManagement.findAll().size()).isEqualTo(3);
    }


    @Test
    @DisplayName("create a profil should succes")
    public void create_a_profil_should_succes() {
        init();

        ProfilEntity profilCree = new ProfilEntity();
        profilCree.setId(10L);
        profilCree.setNom("Nouveau Profil");
        profilCree.setDescription("Profil uniquement créé à des fins de tests");
        Set<DroitEntity> droitsProfil = new HashSet<>();
        droitsProfil.add(creation);
        droitsProfil.add(modification);
        profilCree.setDroits(droitsProfil);
        profils.add(profilCree);

        when(repositoryProfil.create(profilCree)).thenReturn(profilCree);
        when(repositoryProfil.findAll()).thenReturn(profils);

        profilManagement.create(profilCree);

        assertThat(profilManagement.findAll()).contains(profilCree);
        assertThat(profilManagement.findAll().size()).isEqualTo(nombredeProfils + 1);
    }

    @Test
    @DisplayName("Find an existing profil should succes")
    public void find_an_existing_profil_should_succes() {
        init();

        when(repositoryProfil.findById(administrateur.getId())).thenReturn(administrateur);

        assertThat(profilManagement.findByID(administrateur.getId()).getNom()).isEqualTo(administrateur.getNom());
        assertThat(profilManagement.findByID(administrateur.getId()).getDroits().size()).isEqualTo(administrateur.getDroits().size());
    }

    @Test
    @DisplayName("Find an inexisting profil should return null")
    public void find_an_inexisting_profil_should_return_null() {
        ProfilEntity profilInexistant = new ProfilEntity();
        profilInexistant.setId(1000L);

        when(repositoryProfil.findById(profilInexistant.getId())).thenReturn(null);

        assertThatThrownBy(() -> profilManagement.findByID(profilInexistant.getId()))
                                                    .isInstanceOf(NotFoundException.class)
                                                    .hasMessageContaining("Aucun profil ayant l'id : ");
    }

    @Test
    @DisplayName("Update an existing profil should success")
    public void update_an_existing_profil_should_success() {
        init();
        redacteur.setNom("Rédacteur modifié");

        when(repositoryProfil.update(redacteur)).thenReturn(redacteur);

        ProfilEntity redacteurModifie = profilManagement.update(redacteur);

        assertThat(redacteurModifie.getId()).isEqualTo(redacteur.getId());
        assertThat(redacteurModifie.getNom()).isEqualTo(redacteur.getNom());
        assertThat(redacteurModifie.getDescription()).isEqualTo(redacteur.getDescription());
        assertThat(redacteurModifie.getDroits()).isEqualTo(redacteur.getDroits());
    }

    @Test
    @DisplayName("Delete an existing profil should success")
    public void delete_an_existing_profil_should_success() {
        init();

        when(repositoryProfil.findById(redacteur.getId())).thenReturn(redacteur);
        profilManagement.delete(redacteur.getId());

        verify(repositoryProfil, times(1)).delete(eq(redacteur));
    }

    @Test
    @DisplayName("Delete an inexisting profil should fail")
    public void delete_an_inexisting_profil_should_fail() {
        ProfilEntity profilInexistant = new ProfilEntity();
        profilInexistant.setId(1000L);

        when(repositoryProfil.findById(profilInexistant.getId())).thenReturn(null);

        assertThatThrownBy(() -> profilManagement.delete(profilInexistant.getId()))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Aucun profil ayant l'id : ");
    }


     */
}
