package com.example.portailci.exposition.droit;

import com.example.portailci.application.droit.IDroitManagement;
import com.example.portailci.domain.droit.DroitEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class) // Remplace le runner @RunWith(SpringRunner.class) de JUnit4
@AutoConfigureMockMvc
@EnableWebMvc
public class DroitControllerTest {

private static final Set<DroitEntity> droits = new HashSet<>();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IDroitManagement droitManagement;

    private final Long ID = 1L;
    private final String NOM = "Consultation";
    private final String DESCRIPTION = "Droit de consultation";
@BeforeEach
    public void init() {
    DroitEntity droitConsultation = new DroitEntity(ID,NOM,DESCRIPTION);
    DroitEntity droitCreation = new DroitEntity(2L,"Création","Droit de création");
    DroitEntity droitModification = new DroitEntity(3L,"Modification","Droit de modification");
    DroitEntity droitSuppression = new DroitEntity(4L,"Suppression","Droit de suppression");
    DroitEntity droitAdministration = new DroitEntity(5L,"Administration","Droit d'administration");

    droits.add(droitConsultation);
    droits.add(droitCreation);
    droits.add(droitModification);
    droits.add(droitSuppression);
    droits.add(droitAdministration);
}

//region Tests SpringSecurity

    @WithMockUser(authorities = {"Consultation"})
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Consultation_authority_and_want_to_find_all_Droits() throws Exception {

        mockMvc.perform(get("/portailci/droits/get")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"Administration"})
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Admninistration_authority_and_want_to_find_all_Droits() throws Exception {

        mockMvc.perform(get("/portailci/droits/get")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"Administration","Consultation"})
    @Test
    public void should_return_a_ResponseEntity_with_List_of_DroitDTO_and_HTTPStatusOK_when_User_have_all_necessary_authorities_and_want_to_find_all_Droits() throws Exception {

        //Given

        when(droitManagement.findAll()).thenReturn(droits);

        //When
        String result = mockMvc.perform(get("/portailci/droits/get")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // ATTENTION: Depuis Spring-boot 2.2.0, l'encodage par défaut n'est plus UTF-8, mais ISO 8859-1 par défaut
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        //Then
        String expectJson = objectMapper.writeValueAsString(droits);

        // On utilise la librairie JSONAssert de Skyscreamer afin d'avoir de la souplesse dans la comparaison des Json
        JSONAssert.assertEquals(expectJson, result, false);
    }

    @WithMockUser(authorities = {"Consultation"})
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Consultation_authority_and_want_to_find_a_Droit() throws Exception {

        mockMvc.perform(get("/portailci/droits/get/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"Administration"})
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Admninistration_authority_and_want_to_find_a_Droit() throws Exception {

        mockMvc.perform(get("/portailci/droits/get/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"Administration","Consultation"})
    @Test
    public void should_return_a_ResponseEntity_with_List_of_DroitDTO_and_HTTPStatusOK_when_User_have_all_necessary_authorities_and_want_to_find_a_Droit() throws Exception {

        //Given
        DroitEntity droitEntity = new DroitEntity(ID,NOM,DESCRIPTION);
        when(droitManagement.findById(any(Long.class))).thenReturn(droitEntity);

        //When
        String result = mockMvc.perform(get("/portailci/droits/get/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // ATTENTION: Depuis Spring-boot 2.2.0, l'encodage par défaut n'est plus UTF-8, mais ISO 8859-1 par défaut
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        //Then
        String expectJson = objectMapper.writeValueAsString(droitEntity);

        // On utilise la librairie JSONAssert de Skyscreamer afin d'avoir de la souplesse dans la comparaison des Json
        JSONAssert.assertEquals(expectJson, result, false);
    }

    //endregion
}
