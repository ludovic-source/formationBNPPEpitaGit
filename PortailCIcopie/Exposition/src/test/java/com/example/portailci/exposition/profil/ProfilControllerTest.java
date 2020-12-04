package com.example.portailci.exposition.profil;

import com.example.portailci.application.profil.IProfilManagement;
import com.example.portailci.domain.droit.DroitEntity;
import com.example.portailci.domain.profil.ProfilEntity;
import com.example.portailci.exposition.droit.DroitDTO;
import com.example.portailci.exposition.droit.DroitMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProfilControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DroitMapper droitMapper;

    @Autowired
    private ProfilMapper profilMapper;

    @MockBean
    private IProfilManagement profilManagement;

    // Profil Admin
    private final Long ID_ADMIN = 1L;
    private final String NOM_ADMIN = "Administrateur";
    private final String DESCRIPTION_ADMIN = "Utilisateur ayant les droits d'admin";

    // Profil Rédacteur
    private final Long ID_REDAC = 2L;
    private final String NOM_REDAC = "Rédacteur";
    private final String DESCRIPTION_REDAC = "Utilisateur ayant les droits d'admin";

    private final Set<DroitEntity> droits_Admin = new HashSet<>();
    private final Set<DroitEntity> droits_Redac = new HashSet<>();
    private final Set<DroitDTO> droitsDTO_Admin = new HashSet<>();
    private final DroitEntity droitConsultation = new DroitEntity(1L);
    private final DroitEntity droitCreation = new DroitEntity(2L);
    private final DroitEntity droitModification = new DroitEntity(3L);
    private final DroitEntity droitSuppression = new DroitEntity(4L);
    private final DroitEntity droitAdministration = new DroitEntity(5L);

    private ProfilEntity profilAdminEntity = new ProfilEntity(ID_ADMIN,NOM_ADMIN,DESCRIPTION_ADMIN, droits_Admin);
    private ProfilEntity profilRedacteur = new ProfilEntity(ID_REDAC,NOM_REDAC,DESCRIPTION_REDAC,droits_Redac);
    private ProfilDTO newAdminProfilDTO = new ProfilDTO(NOM_ADMIN,DESCRIPTION_ADMIN,droitsDTO_Admin);
    private Set<ProfilEntity> profils = new HashSet<>();

    @BeforeAll
    public void beforeAll() {

        droits_Admin.add(droitConsultation);
        droits_Admin.add(droitCreation);
        droits_Admin.add(droitModification);
        droits_Admin.add(droitSuppression);
        droits_Admin.add(droitAdministration);

        droits_Redac.add(droitConsultation);
        droits_Redac.add(droitCreation);
        droits_Redac.add(droitModification);
        droits_Redac.add(droitSuppression);

        droitsDTO_Admin.add(droitMapper.mapToDto(droitConsultation));
        droitsDTO_Admin.add(droitMapper.mapToDto(droitCreation));
        droitsDTO_Admin.add(droitMapper.mapToDto(droitModification));
        droitsDTO_Admin.add(droitMapper.mapToDto(droitSuppression));
        droitsDTO_Admin.add(droitMapper.mapToDto(droitAdministration));


    }

    @BeforeEach
    public void beforeEach() {
        profils.add(profilAdminEntity);
        profils.add(profilRedacteur);

    }

    //region Tests SpringSecurity

    //region Consultation Profil

    @WithMockUser(authorities = {"Consultation"})
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Consultation_authority_and_want_to_find_all_Profils() throws Exception {

        mockMvc.perform(get("/portailci/profils/get")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"Administration"})
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Admninistration_authority_and_want_to_find_all_Profils() throws Exception {

        mockMvc.perform(get("/portailci/profils/get")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"Administration","Consultation"})
    @Test
    public void should_return_a_ResponseEntity_with_List_of_DroitDTO_and_HTTPStatusOK_when_User_have_all_necessary_authorities_and_want_to_find_all_Profils() throws Exception {

        //Given

        when(profilManagement.findAll()).thenReturn(profils);

        //When
        String result = mockMvc.perform(get("/portailci/profils/get")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // ATTENTION: Depuis Spring-boot 2.2.0, l'encodage par défaut n'est plus UTF-8, mais ISO 8859-1 par défaut
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        //Then
        String expectJson = objectMapper.writeValueAsString(profils);

        // On utilise la librairie JSONAssert de Skyscreamer afin d'avoir de la souplesse dans la comparaison des Json
        JSONAssert.assertEquals(expectJson, result, false);
    }

    //endregion

    //region Création Profil
    @WithMockUser(authorities = {"Création"})
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Creation_authority_and_want_to_create_a_Profil() throws Exception {

        String jsonFromClient = objectMapper.writeValueAsString(newAdminProfilDTO);

        mockMvc.perform(post("/portailci/utilisateurs/create")
                .content(jsonFromClient)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"Administration"}) // On simule un retour d'utilisateur avec l'autorisation "Administration" seulement alors que l'accès nécessite l'autorisation "Administration" ET "Création"
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Admninistration_authority_and_want_to_create_a_Profil() throws Exception {

        String jsonFromClient = objectMapper.writeValueAsString(newAdminProfilDTO);

        mockMvc.perform(post("/portailci/utilisateurs/create")
                .content(jsonFromClient)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"Administration","Création"})
    @Test
    public void should_return_a_ResponseEntity_with_ProfilFullDTO_and_HTTPStatusCreated_when_User_have_all_necessary_authorities_and_want_to_create_a_Profil() throws Exception {

        //Given
        when(profilManagement.create(any(ProfilEntity.class))).thenReturn(profilAdminEntity);
        String jsonFromClient = objectMapper.writeValueAsString(newAdminProfilDTO);

        //When
        String result = mockMvc.perform(post("/portailci/profils/create")
                .content(jsonFromClient)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                // ATTENTION: Depuis Spring-boot 2.2.0, l'encodage par défaut n'est plus UTF-8, mais ISO 8859-1 par défaut
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        //Then
        String expectJson = objectMapper.writeValueAsString(profilMapper.mapToDto(profilAdminEntity));

        // On utilise la librairie JSONAssert de Skyscreamer afin d'avoir de la souplesse dans la comparaison des Json
        JSONAssert.assertEquals(expectJson, result, false);
    }
//endregion

    //region Suppression Utilisateur
    @WithMockUser(authorities = {"Administration"})
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Admninistration_authority_and_want_to_delete_a_Profil() throws Exception {

        mockMvc.perform(delete("/portailci/profils/delete/{id}",ID_ADMIN)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"Suppression"})
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Suppression_authority_and_want_to_delete_a_Profil() throws Exception {


        mockMvc.perform(delete("/portailci/profils/delete/{id}",ID_ADMIN)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"Administration","Suppression"})
    @Test
    public void should_be_success_when_User_have_Admninistration_and_Suppression_authorities_and_want_to_delete_a_Profil() throws Exception {

        mockMvc.perform(delete("/portailci/profils/delete/{id}",ID_ADMIN)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //endregion

    //region Modification Utilisateur
    @WithMockUser(authorities = {"Administration"})
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Admninistration_authority_and_want_to_update_a_Profil() throws Exception {

        profilAdminEntity.setNom("Nettoyeur");

        String jsonFromClient = objectMapper.writeValueAsString(profilAdminEntity);

        mockMvc.perform(put("/portailci/profils/update")
                .content(jsonFromClient)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"Modification"})
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Modification_authority_and_want_to_update_a_Profil() throws Exception {

        profilAdminEntity.setNom("Nettoyeur");

        String jsonFromClient = objectMapper.writeValueAsString(profilAdminEntity);

        mockMvc.perform(put("/portailci/profils/update")
                .content(jsonFromClient)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"Administration","Modification"})
    @Test
    public void should_be_success_when_User_have_Admninistration_and_Modification_authorities_and_want_to_update_a_Profil() throws Exception {

        //Given
        ProfilEntity profilToModify = new ProfilEntity(profilAdminEntity.getId(),"Nettoyeur", profilAdminEntity.getDescription(), profilAdminEntity.getDroits());

        when(profilManagement.update(any(ProfilEntity.class))).thenReturn(profilToModify);

        String jsonFromClient = objectMapper.writeValueAsString(profilMapper.mapToDto(profilToModify));

        //When
        String result = mockMvc.perform(put("/portailci/profils/update")
                .content(jsonFromClient)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // ATTENTION: Depuis Spring-boot 2.2.0, l'encodage par défaut n'est plus UTF-8, mais ISO 8859-1 par défaut
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        //Then
        String expectJson = objectMapper.writeValueAsString(profilToModify);

        // On utilise la librairie JSONAssert de Skyscreamer afin d'avoir de la souplesse dans la comparaison des Json
        JSONAssert.assertEquals(expectJson, result, false);
    }

    //endregion

    //endregion


}
