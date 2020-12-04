package com.example.portailci.exposition.thematique;

import com.example.portailci.application.thematique.ManagementThematiqueImpl;
import com.example.portailci.domain.thematique.ThematiqueEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
public class ControllerThematiqueTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ManagementThematiqueImpl managementThematique;

    private ThematiqueEntity thematique = new ThematiqueEntity();
    private ThematiqueEntity thematiqueEnfant = new ThematiqueEntity();
    private List<ThematiqueEntity> thematiquesEnfant = new ArrayList<>();
    private String extension = "";

    private void initThematique() {
        thematique.setId(1L);
        thematique.setNom("Nom");
        thematique.setDescription("Description");
        thematique.setIdParent(0L);
        thematique.setImagePath("chemin image.ext");
        thematique.setNiveau(1);

        String[] decoupage = thematique.getImagePath().split("\\.");
        extension = decoupage[decoupage.length - 1];

        thematiqueEnfant.setId(2L);
        thematiqueEnfant.setNom("Nom Enfant");
        thematiqueEnfant.setDescription("Description Enfant");
        thematiqueEnfant.setIdParent(thematique.getId());
        thematiqueEnfant.setImagePath("chemin image.ext");
        thematiqueEnfant.setNiveau(thematique.getNiveau()+1);
        thematiquesEnfant.add(thematiqueEnfant);
    }

    @Test
    @DisplayName("Création thématique échec access denied 403")
    @WithMockUser(authorities = {"Consultation"})
    public void create_thematique_should_fail_forbidden() throws Exception {
        initThematique();

        when(managementThematique.createThematique(thematique, extension)).thenReturn(thematique);
        String result = mockMvc.perform(MockMvcRequestBuilders
                .post("/portailci/thematique/create")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(thematique)))
                .andExpect(status().isForbidden())
                .andReturn().getResponse().getContentAsString()
                ;
    }

    @Test
    @DisplayName("Création thématique succès")
    @WithMockUser(authorities = {"Création"})
    public void create_thematique_should_success() throws Exception {
        initThematique();

        when(managementThematique.createThematique(thematique, extension)).thenReturn(thematique);
        String result = mockMvc.perform(MockMvcRequestBuilders
                .post("/portailci/thematique/create")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(thematique)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString()
                ;

        ThematiqueEntity thematiqueRetour = objectMapper.readValue(result, ThematiqueEntity.class);
        assertThat(thematiqueRetour.getId()).isEqualTo(thematique.getId());
    }

    @Test
    @DisplayName("Recherche thématique access is denied")
    @WithMockUser(authorities = {"Création"})
    public void find_thematique_access_denied_should_fail() throws Exception {
        initThematique();

        when(managementThematique.findThematique(thematique.getId())).thenReturn(thematique);

        String result = mockMvc.perform(MockMvcRequestBuilders
                .get("/portailci/thematique/find/{idThematique}", thematique.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn().getResponse().getContentAsString()
                ;
    }

    @Test
    @DisplayName("Recherche thématique succès")
    @WithMockUser(authorities = {"Consultation"})
    public void find_thematique_should_success() throws Exception {
        initThematique();

        when(managementThematique.findThematique(thematique.getId())).thenReturn(thematique);

        String result = mockMvc.perform(MockMvcRequestBuilders
                .get("/portailci/thematique/find/{idThematique}", thematique.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString()
                ;

        ThematiqueEntity thematiqueRetour = objectMapper.readValue(result, ThematiqueEntity.class);
        assertThat(thematiqueRetour.getId()).isEqualTo(thematique.getId());
    }

    @Test
    @DisplayName("Recherche thématique not found")
    @WithMockUser(authorities = {"Consultation"})
    public void find_non_existing_thematique_should_return_null() throws Exception {
        initThematique();

        when(managementThematique.findThematique(thematique.getId())).thenReturn(null);

        String result = mockMvc.perform(MockMvcRequestBuilders
                .get("/portailci/thematique/find/{idThematique}", thematique.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())                         // la recherche retourne null quand on ne trouve pas
                .andReturn().getResponse().getContentAsString()
                ;

        assertThat(result).isEqualTo("");
    }

    @Test
    @DisplayName("Suppression thématique échec access denied 403")
    @WithMockUser(authorities = {"Consultation"})
    public void delete_thematique_should_fail_forbidden() throws Exception {
        initThematique();

        doNothing().when(managementThematique).deleteThematique(thematique.getId());

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/portailci/thematique/delete/{idThematique}", thematique.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(thematique)))
                .andExpect(status().isForbidden())
                .andReturn().getResponse().getContentAsString()
                ;
    }

    @Test
    @DisplayName("Suppression thématique success")
    @WithMockUser(authorities = {"Suppression"})
    public void delete_thematique_should_succes() throws Exception {
        initThematique();

        doNothing().when(managementThematique).deleteThematique(thematique.getId());

        String result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/portailci/thematique/delete/{idThematique}", thematique.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(thematique)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString()
                ;

        verify(managementThematique, times(1)).deleteThematique(thematique.getId());
    }

    @Test
    @DisplayName("Modification thématique fail access denied")
    @WithMockUser(authorities = {"Consultation"})
    public void update_thematique_access_denied_should_fail() throws Exception {
        initThematique();
        thematique.setDescription("La description change");

        when(managementThematique.updateThematique(thematique)).thenReturn(thematique);
        String result = mockMvc.perform(MockMvcRequestBuilders
                .put("/portailci/thematique/update")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(thematique)))
                .andExpect(status().isForbidden())
                .andReturn().getResponse().getContentAsString()
                ;
    }

    @Test
    @DisplayName("Modification thématique success")
    @WithMockUser(authorities = {"Modification"})
    public void update_thematique_should_succes() throws Exception {
        initThematique();
        thematique.setDescription("La description change");

        when(managementThematique.updateThematique(thematique)).thenReturn(thematique);
        String result = mockMvc.perform(MockMvcRequestBuilders
                .put("/portailci/thematique/update")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(thematique)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString()
                ;

        ThematiqueEntity thematiqueRetour = objectMapper.readValue(result, ThematiqueEntity.class);
        System.out.println("result = " + result);
        assertThat(thematiqueRetour.getDescription()).isEqualTo(thematique.getDescription());
    }

    @Test
    @DisplayName("Recherche thématiques enfants access is denied")
    @WithMockUser(authorities = {"Création"})
    public void find_thematiques_enfants_access_denied_should_fail() throws Exception {
        initThematique();

        when(managementThematique.findThematiquesEnfants(thematique.getId())).thenReturn(thematiquesEnfant);

        String result = mockMvc.perform(MockMvcRequestBuilders
                .get("/portailci/thematique/findenfants/{idParent}", thematique.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn().getResponse().getContentAsString()
                ;
    }

    @Test
    @DisplayName("Recherche thématiques enfants success")
    @WithMockUser(authorities = {"Consultation"})
    public void find_thematiques_enfants_should_success() throws Exception {
        initThematique();

        when(managementThematique.findThematiquesEnfants(thematique.getId())).thenReturn(thematiquesEnfant);

        String result = mockMvc.perform(MockMvcRequestBuilders
                .get("/portailci/thematique/findenfants/{idParent}", thematique.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString()
                ;
        System.out.println("result = " + result);
    }

}
