package com.example.portailci.exposition.utilisateur;

import com.example.portailci.application.utilisateur.IUtilisateurManagement;
import com.example.portailci.domain.profil.ProfilEntity;
import com.example.portailci.domain.utilisateur.UtilisateurEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(SpringExtension.class) // Remplace le runner @RunWith(SpringRunner.class) de JUnit4
@AutoConfigureMockMvc
@EnableWebMvc
public class UtilisateurControllerTest {
    private  static final Long ID = 1L;
    private  static final String UID_UTILISATEUR = "b52104";
    private  static final String NOM_UTILISATEUR = "WAYNE";
    private  static final String PRENOM_UTILISATEUR = "Bruce";
    private  static final String MDP_UTILISATEUR = "toto1234";
    private  static final String UO_UTILISATEUR = "41526Z48";
    private  static final String SITE_UTILISATEUR = "Gotham City";
    private  static final String FONCTION_UTILISATEUR = "Justicier";
    private  static final ProfilEntity PROFIL_UTILISATEUR = new ProfilEntity(1L);

    private  static final String NOUVEAU_NOM_UTILISATEUR = "KENT";
    private  static final String NOUVEAU_PRENOM_UTILISATEUR = "Clark";
    private  static final String NOUVEAU_SITE_UTILISATEUR = "Metropolis";


    private final UtilisateurEntity alreadyExistBruceWayne = new UtilisateurEntity(ID,UID_UTILISATEUR,NOM_UTILISATEUR,PRENOM_UTILISATEUR,MDP_UTILISATEUR,UO_UTILISATEUR,SITE_UTILISATEUR,FONCTION_UTILISATEUR, PROFIL_UTILISATEUR);
    private final UtilisateurEntity newBruceWayne = new UtilisateurEntity(UID_UTILISATEUR,NOM_UTILISATEUR,PRENOM_UTILISATEUR,MDP_UTILISATEUR,UO_UTILISATEUR,SITE_UTILISATEUR,FONCTION_UTILISATEUR, PROFIL_UTILISATEUR);
    private final UtilisateurEntity modifyingBruceWayne = new UtilisateurEntity(ID,UID_UTILISATEUR,NOUVEAU_NOM_UTILISATEUR,NOUVEAU_PRENOM_UTILISATEUR,MDP_UTILISATEUR,UO_UTILISATEUR,NOUVEAU_SITE_UTILISATEUR,FONCTION_UTILISATEUR, PROFIL_UTILISATEUR);
    private final List<UtilisateurEntity> utilisateurEntities = new ArrayList<>();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IUtilisateurManagement utilisateurManagement;

    @BeforeEach
    void init() {
        utilisateurManagement.create(alreadyExistBruceWayne);
    }

    @Test
    public void should_Utilisateur_have_a_6_characters_UID() {
        assertThat(alreadyExistBruceWayne.getUid().length()).isEqualTo(6);
    }

    // TEST KO - A CORRIGER
    /*
    @WithMockUser(authorities = {"Administration","Consultation"}) // On simule un retour d'utilisateur avec les autorisations "Administration" et "Consultation"
    @Test
    public void should_find_Utilisateur_by_id_success() throws Exception {

        //Given
        when(utilisateurManagement.findByID(any(Long.class))).thenReturn(alreadyExistBruceWayne);

        //When
        final String result = mockMvc.perform(get("/portailci/utilisateurs/get/{id}", ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // ATTENTION: Depuis Spring-boot 2.2.0, l'encodage par défaut n'est plus UTF-8, mais ISO 8859-1 par défaut
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        //Then
        String expectJson = objectMapper.writeValueAsString(alreadyExistBruceWayne);

        // On utilise la librairie JSONAssert de Skyscreamer afin d'avoir de la souplesse dans la comparaison des Json
        JSONAssert.assertEquals(expectJson, result, false);
    }
    */

    // Test OK, mais déclenche un test bis avec erreur : "org.junit.jupiter.api.extension.ParameterResolutionException: No ParameterResolver registered for parameter [java.lang.String uid] "
    @WithMockUser(authorities = {"Administration","Consultation"}) // On simule un retour d'utilisateur avec les autorisations "Administration" et "Consultation"
    @ParameterizedTest
    @ValueSource(strings = {"1","a3","b45","4d58","a3060","123456"})
    public void should_thrown_an_exception_when_receive_an_UtilisateurDTO_with_a_UID_lesser_than_6(String uid) throws Exception {
        System.out.println("uid = " + uid);
        mockMvc.perform(get("/portailci/utilisateurs/getByUID/{utilisateurUid}", uid)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    //region Tests Spring Security



    // Si NullPointerException: le contrôle sécutité ne fonctionne pas (aucun mock fait d'où le retour Null)

//region Consultation Utilisateur

    @WithMockUser(authorities = {"Consultation"}) // On simule un retour d'utilisateur avec l'autorisation "Création" seulement alors que l'accès nécessite l'autorisation "Administration" ET "Création"
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Consultation_authority_and_want_to_find_all_Utilisateurs() throws Exception {

        mockMvc.perform(get("/portailci/utilisateurs/get")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"Administration"}) // On simule un retour d'utilisateur avec l'autorisation "Administration" seulement alors que l'accès nécessite l'autorisation "Administration" ET "Création"
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Admninistration_authority_and_want_to_find_all_Utilisateurs() throws Exception {

        mockMvc.perform(get("/portailci/utilisateurs/get")

                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    // TEST KO - A CORRIGER
    /*
    @WithMockUser(authorities = {"Administration","Consultation"})
    @Test
    public void should_return_a_ResponseEntity_with_Set_of_UtilisateurFullDTO_and_HTTPStatusOK_when_User_have_all_necessary_authorities_and_want_to_find_all_Utilisateurs() throws Exception {

        //Given
        utilisateurEntities.add(alreadyExistBruceWayne);
        utilisateurEntities.add(modifyingBruceWayne);

        when(utilisateurManagement.findAll()).thenReturn(utilisateurEntities);

        String jsonFromClient = objectMapper.writeValueAsString(newBruceWayne);

        //When
        String result = mockMvc.perform(get("/portailci/utilisateurs/get")
                .content(jsonFromClient)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // ATTENTION: Depuis Spring-boot 2.2.0, l'encodage par défaut n'est plus UTF-8, mais ISO 8859-1 par défaut
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        //Then
        String expectJson = objectMapper.writeValueAsString(utilisateurEntities);

        // On utilise la librairie JSONAssert de Skyscreamer afin d'avoir de la souplesse dans la comparaison des Json
        JSONAssert.assertEquals(expectJson, result, false);
    }
    */


//endregion

//region Création Utilisateur
    @WithMockUser(authorities = {"Création"}) // On simule un retour d'utilisateur avec l'autorisation "Création" seulement alors que l'accès nécessite l'autorisation "Administration" ET "Création"
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Creation_authority_and_want_to_create_a_Utilisateur() throws Exception {

       String jsonFromClient = objectMapper.writeValueAsString(newBruceWayne);

       mockMvc.perform(post("/portailci/utilisateurs/create")
                        .content(jsonFromClient)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"Administration"}) // On simule un retour d'utilisateur avec l'autorisation "Administration" seulement alors que l'accès nécessite l'autorisation "Administration" ET "Création"
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Admninistration_authority_and_want_to_create_a_Utilisateur() throws Exception {

        String jsonFromClient = objectMapper.writeValueAsString(newBruceWayne);

        mockMvc.perform(post("/portailci/utilisateurs/create")
                .content(jsonFromClient)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    // TEST KO - A CORRIGER
    /*
    @WithMockUser(authorities = {"Administration","Création"})
    @Test
    public void should_return_a_ResponseEntity_with_UtilisateurFullDTO_and_HTTPStatusCreated_when_User_have_all_necessary_authorities_and_want_to_create_a_Utilisateur() throws Exception {

        //Given
        when(utilisateurManagement.create(any(UtilisateurEntity.class))).thenReturn(alreadyExistBruceWayne);
        String jsonFromClient = objectMapper.writeValueAsString(newBruceWayne);

        //When
         String result = mockMvc.perform(post("/portailci/utilisateurs/create")
                .content(jsonFromClient)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                 // ATTENTION: Depuis Spring-boot 2.2.0, l'encodage par défaut n'est plus UTF-8, mais ISO 8859-1 par défaut
                 .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
         //Then
        String expectJson = objectMapper.writeValueAsString(alreadyExistBruceWayne);

        // On utilise la librairie JSONAssert de Skyscreamer afin d'avoir de la souplesse dans la comparaison des Json
        JSONAssert.assertEquals(expectJson, result, false);
    }

     */
//endregion

//region Suppression Utilisateur
    @WithMockUser(authorities = {"Administration"}) // On simule une demande de suppression d'utilisateur avec l'autorisation "Administration" seulement alors que l'accès nécessite l'autorisation "Administration" ET "Suppression"
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Admninistration_authority_and_want_to_delete_a_Utilisateur() throws Exception {

        mockMvc.perform(delete("/portailci/utilisateurs/delete/{id}",ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"Suppression"}) // On simule une demande de suppression d'utilisateur avec l'autorisation "Suppression" seulement alors que l'accès nécessite l'autorisation "Administration" ET "Suppression"
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Suppression_authority_and_want_to_delete_a_Utilisateur() throws Exception {

        mockMvc.perform(delete("/portailci/utilisateurs/delete/{id}",ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"Administration","Suppression"}) // On simule une demande de suppression d'utilisateur avec l'autorisation "Administration" seulement alors que l'accès nécessite l'autorisation "Administration" ET "Suppression"
    @Test
    public void should_be_success_when_User_have_Admninistration_and_Suppression_authorities_and_want_to_delete_a_Utilisateur() throws Exception {

        mockMvc.perform(delete("/portailci/utilisateurs/delete/{id}",ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //endregion

//region Modification Utilisateur
    @WithMockUser(authorities = {"Administration"}) // On simule la modification d'utilisateur avec l'autorisation "Administration" seulement alors que l'accès nécessite l'autorisation "Administration" ET "Modification"
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Admninistration_authority_and_want_to_update_a_Utilisateur() throws Exception {

        String jsonFromClient = objectMapper.writeValueAsString(modifyingBruceWayne);

        mockMvc.perform(put("/portailci/utilisateurs/update")
                .content(jsonFromClient)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"Modification"}) // On simule la modification d'utilisateur avec l'autorisation "Modification" seulement alors que l'accès nécessite l'autorisation "Administration" ET "Modification"
    @Test
    public void should_thrown_an_AccessDeniedException_when_User_just_have_Modification_authority_and_want_to_update_a_Utilisateur() throws Exception {

        String jsonFromClient = objectMapper.writeValueAsString(modifyingBruceWayne);

        mockMvc.perform(put("/portailci/utilisateurs/update")
                .content(jsonFromClient)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    // TEST KO - A CORRIGER
    /*
    @WithMockUser(authorities = {"Administration","Modification"}) // On simule la modification d'utilisateur avec les bonnes autorisations
    @Test
    public void should_be_success_when_User_have_Admninistration_and_Modification_authorities_and_want_to_update_a_Utilisateur() throws Exception {

        //Given
        when(utilisateurManagement.update(any(UtilisateurEntity.class))).thenReturn(modifyingBruceWayne);
        String jsonFromClient = objectMapper.writeValueAsString(alreadyExistBruceWayne);

        //When
        String result = mockMvc.perform(put("/portailci/utilisateurs/update")
                .content(jsonFromClient)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // ATTENTION: Depuis Spring-boot 2.2.0, l'encodage par défaut n'est plus UTF-8, mais ISO 8859-1 par défaut
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        //Then
        String expectJson = objectMapper.writeValueAsString(modifyingBruceWayne);

        // On utilise la librairie JSONAssert de Skyscreamer afin d'avoir de la souplesse dans la comparaison des Json
        JSONAssert.assertEquals(expectJson, result, false);
    }
    */
    //endregion

    //endregion

}
