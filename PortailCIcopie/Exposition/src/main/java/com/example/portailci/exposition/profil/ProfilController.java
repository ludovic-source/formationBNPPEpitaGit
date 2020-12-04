package com.example.portailci.exposition.profil;


import com.example.portailci.application.profil.IProfilManagement;
import com.example.portailci.domain.profil.ProfilEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

@RestController
@RequestMapping("/portailci/profils")
@Api(value = "API Profil", description = "API permettant de faire des opérations CRUD sur les profils utilisateurs")
public class ProfilController {

    private Logger logger = LoggerFactory.getLogger(ProfilController.class);

    @Autowired
    private IProfilManagement profilManagement;

    @Autowired
    private ProfilMapper profilMapper;

    @ApiOperation(value = "Récupération d'un profil à partir de son Id", response = ProfilDTO.class)
    @PreAuthorize("hasAuthority('Administration') and hasAuthority('Consultation')")
    @GetMapping(value = "/get/{id}", produces = {"application/json"})
    public ResponseEntity<Object> getProfilById(Long id) {
        try {

            ProfilDTO profilDTO = profilMapper.mapToDto(profilManagement.findByID(id));
            logger.debug("Retour de la méthode getProfilById pour id = " + id + " = " + profilDTO);
            return new ResponseEntity<>(profilDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.debug("Erreur sur la méthode getProfilById pour id = " + id + " : " + e.getMessage());
            return new ResponseEntity<>("Erreur lors de la recherche du profil : " + e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation(value = "Récupération d'une collection des profils", response = ProfilDTO.class)
    @PreAuthorize("hasAuthority('Administration') and hasAuthority('Consultation')")
    @GetMapping(value = "/get", produces = {"application/json"})
    public ResponseEntity<Set<ProfilDTO>> getProfils() {

        Set<ProfilDTO> profilDTOSet = profilMapper.mapToDtoSet(profilManagement.findAll());
        return new ResponseEntity<>(profilDTOSet, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Consultation')")
    @GetMapping("/get/connectedUser")
    public ResponseEntity<Object> getProfilOfConnectedUser() {
        ProfilDTO profilDTO = profilMapper.mapToDto(profilManagement.findProfilOfConnectedUser());

        return new ResponseEntity<>(profilDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Création d'un profil", response = ProfilDTO.class)
    @PreAuthorize("hasAuthority('Administration') and hasAuthority('Création')")
    @PostMapping("/create")
    public ResponseEntity<Object> createProfil(@NotNull @RequestBody final ProfilDTO profilDTO) {

        try {
            logger.debug(" Méthode createProfil - ProfilDTO reçue en paramètre = " + profilDTO);
            ProfilEntity profilEntity = profilMapper.mapToEntity(profilDTO);
            logger.debug(" Méthode createProfil - ProfilEntity mappée = " + profilEntity);
            ProfilDTO createdProfil = profilMapper.mapToDto(profilManagement.create(profilEntity));
            logger.debug(" Méthode createProfil - profilDTO en retour de la base = " + profilEntity);

            return new ResponseEntity<>(createdProfil, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.debug(" Erreur sur la méthode createProfil : " + e.getMessage());
            return new ResponseEntity<>("Erreur lors de la création du profil : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Modification d'un profil", response = ProfilDTO.class)
    @PreAuthorize("hasAuthority('Administration') and hasAuthority('Modification')")
    @PutMapping("/update")
    public ResponseEntity<Object> updateProfil(@NotNull @RequestBody final ProfilDTO profilDTO) {

        try {
            logger.debug(" Méthode updateProfil - profilDTO reçu en paramètre = " + profilDTO);
            ProfilEntity profilEntity = profilMapper.mapToEntity(profilDTO);
            logger.debug(" Méthode updateProfil - profilEntity mappé = " + profilEntity);

            ProfilDTO updatedProfil = profilMapper.mapToDto(profilManagement.update(profilEntity));
            logger.debug(" Méthode updateProfil - ProfilDTO reçu de la base après modification = " + updatedProfil);

            return new ResponseEntity<>(updatedProfil, HttpStatus.OK);
        } catch (Exception e) {
            logger.debug(" Erreur sur la méthode updateProfil : " + e.getMessage());
            return new ResponseEntity<>("Erreur lors de la modification du profil : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Suppression d'un profil à partir de son id", response = String.class)
    @PreAuthorize("hasAuthority('Administration') and hasAuthority('Suppression')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProfil(@NotNull @PathVariable final Long id) {
        try{
            logger.debug(" On recherche le profil à supprimer ayant pour id : " + id);
            profilManagement.delete(id);
            return new ResponseEntity<>("Profil supprimé avec succès !", HttpStatus.OK);
        }
        catch (Exception e) {
            logger.debug("Erreur lors de la suppression du profil : " + e.getMessage());
            return new ResponseEntity<>("Erreur lors de la suppression du profil : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
