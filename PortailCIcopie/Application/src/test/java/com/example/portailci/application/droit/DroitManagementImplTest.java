package com.example.portailci.application.droit;

import com.example.portailci.domain.droit.DroitEntity;
import com.example.portailci.domain.droit.IRepositoryDroit;
import com.example.portailci.domain.exception.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = DroitManagementImpl.class)
@ExtendWith(SpringExtension.class)
public class DroitManagementImplTest {

    @Autowired
    private IDroitManagement droitManagement;
    @MockBean
    private IRepositoryDroit repositoryDroit;

    DroitEntity consultation = new DroitEntity();
    DroitEntity redaction = new DroitEntity();
    DroitEntity administration = new DroitEntity();
    Set<DroitEntity> droits = new HashSet<>();

    private void init() {
        administration.setId(1L);
        administration.setNom("Administration");
        administration.setDescription("Gestion des utilisateurs et profils");
        droits.add(administration);

        redaction.setId(2L);
        redaction.setNom("Rédaction");
        redaction.setDescription("Publication des liens, création des thématiques");
        droits.add(redaction);

        consultation.setId(3L);
        consultation.setNom("Utilisation");
        consultation.setDescription("Utilisation du portail");
        droits.add(consultation);
    }

    @Test
    @DisplayName("Find all droits should succes")
    public void find_all_droits_should_succes() {
        init();

        when(repositoryDroit.findAll()).thenReturn(droits);

        assertThat(droitManagement.findAll()).contains(administration);
        assertThat(droitManagement.findAll()).contains(redaction);
        assertThat(droitManagement.findAll()).contains(consultation);
    }

    @Test
    @DisplayName("Find an existing droit should succes")
    public void find_an_existing_droit_should_succes() {
        init();

        when(repositoryDroit.findById(administration.getId())).thenReturn(administration);

        DroitEntity admin = droitManagement.findById(administration.getId());
        assertThat(admin.getId()).isEqualTo(administration.getId());
        assertThat(admin.getDescription()).isEqualTo(administration.getDescription());
        assertThat(admin.getNom()).isEqualTo(administration.getNom());
    }

    @Test
    @DisplayName("Find a non existing droit should fail")
    public void find_a_non_existing_droit_should_fail() {
        init();
        DroitEntity inconnu = new DroitEntity();
        inconnu.setId(1000L);

        when(repositoryDroit.findById(inconnu.getId())).thenReturn(null);

        assertThatThrownBy(() -> droitManagement.findById(inconnu.getId()))
            .isInstanceOf(NotFoundException.class);
    }

}
