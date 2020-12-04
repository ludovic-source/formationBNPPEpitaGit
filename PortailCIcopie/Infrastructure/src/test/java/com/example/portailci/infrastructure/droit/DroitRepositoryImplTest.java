package com.example.portailci.infrastructure.droit;

import com.example.portailci.domain.droit.DroitEntity;
import com.example.portailci.domain.droit.IRepositoryDroit;
import com.example.portailci.domain.exception.NotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestInstance(PER_CLASS)
public class DroitRepositoryImplTest {

    @Autowired
    private IRepositoryDroit repositoryDroit;


    private DroitEntity droitAdministration = new DroitEntity();
    private DroitEntity droitConsultation = new DroitEntity();
    private DroitEntity droitCreation = new DroitEntity();
    private DroitEntity droitModification = new DroitEntity();
    private DroitEntity droitSuppression = new DroitEntity();


    @BeforeAll
    public void beforeAll() {

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

    }

/*
    @Test
    public void should_return_a_DroitEntity_when_searching_an_existing_id() {

        assertThat(repositoryDroit.findById(1L)).isInstanceOf(DroitEntity.class);
    }

    // TEST KO A CORRIGER

    @Test
    public void should_thrown_a_NotFoundException_when_searching_an_inexisting_id() {

        Throwable exception = catchThrowable(() ->repositoryDroit.findById(1000L));

        assertThat(exception)
            .isNotNull()
            .isInstanceOf(NotFoundException.class);
    }


    @Test
    public void should_return_a_set_of_5_elements_when_searching_all_Droits() {
        assertThat(repositoryDroit.findAll()).hasSize(5);
    }

*/



}
