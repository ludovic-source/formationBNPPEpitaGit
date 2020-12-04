package com.example.portailci.exposition.utilisateur;

import com.example.portailci.application.utilisateur.IUtilisateurManagement;
import com.example.portailci.domain.exception.NotFoundException;
import com.example.portailci.domain.utilisateur.UtilisateurEntity;
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
import java.util.List;
@Api(produces = "API pour les opérations CRUD sur les utilisateurs")
@RestController
@RequestMapping("/portailci/utilisateurs")
public class UtilisateurController {

    private static Logger logger = LoggerFactory.getLogger(UtilisateurController.class);

    @Autowired
    private IUtilisateurManagement utilisateurManagement;

    @Autowired
    private UtilisateurMapper utilisateurMapper;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('Administration') and hasAuthority('Création')")
    public ResponseEntity<Object> createUtilisateur(@NotNull @RequestBody final UtilisateurDTO utilisateurDTO) {

        try{
            logger.debug(" Méthode createUtilisateur - UtilisateurDTO reçu en paramètre = " + utilisateurDTO);
            UtilisateurEntity createdUtilisateurEntity = utilisateurManagement.create(utilisateurMapper.mapToEntity(utilisateurDTO));

            logger.debug(" Méthode createUtilisateur - UtilisateurEntity reçu de la base de données = " + createdUtilisateurEntity);

            UtilisateurDTO createdUtilisateurDTO = utilisateurMapper.mapToDto(createdUtilisateurEntity);
            logger.debug(" Méthode createUtilisateur - UtilisateurDTO retourné au client = " + createdUtilisateurDTO);

            return new ResponseEntity<>(createdUtilisateurDTO, HttpStatus.CREATED);
        }
        catch (Exception e) {

            logger.debug(" Erreur sur la méthode createUtilisateur : " + e.getMessage());
            return new ResponseEntity<>("Erreur lors de la création de l'utilisateur : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @ApiOperation(value = "Recherche d'un utilisateur à partir de son UID", response = UtilisateurDTO.class)
    @PreAuthorize("hasAnyAuthority('Administration', 'Consultation')")
    @GetMapping(value = "/getByUID/{utilisateurUid}", produces = {"application/json"})
    public ResponseEntity<Object> getUtilisateurByUid(@PathVariable final String utilisateurUid) {

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();

        try {
            utilisateurDTO = utilisateurMapper.mapToDto(utilisateurManagement.findByUid(utilisateurUid, false));

            return new ResponseEntity<>(utilisateurDTO, HttpStatus.OK);
        } catch (NotFoundException e) {
            //On retourne un utilisateur vide et un code HTTP 404 Not found
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            //On retourne un utilisateur vide et un code HTTP 400 Bad Request
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Recherche d'un utilisateur dans le REFOG à partir d'un UID", response = UtilisateurDTO.class)
    @PreAuthorize("hasAuthority('Administration') and hasAuthority('Consultation')")
    @GetMapping(value = "/getRefogByUID/{utilisateurUid}", produces = {"application/json"})
    public ResponseEntity<Object> getUtilisateurRefogByUid(@PathVariable final String utilisateurUid) {

        UtilisateurRefogDTO utilisateurRefogDTO = new UtilisateurRefogDTO();

        try {
            utilisateurRefogDTO = utilisateurMapper.mapToRefogDto(utilisateurManagement.findByUid(utilisateurUid, true));
            logger.debug(" Retour UtilisateurRefogDTO = " + utilisateurRefogDTO);
            return new ResponseEntity<>(utilisateurRefogDTO, HttpStatus.OK);
        } catch (NotFoundException e) {
            logger.debug(" Erreur sur la méthode getUtilisateurRefogByUid : " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.debug(" Erreur sur la méthode getUtilisateurRefogByUid : " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @ApiOperation(value = "Recherche d'un utilisateur à partir de son Id", response = UtilisateurDTO.class)
    @PreAuthorize("hasAuthority('Administration') and hasAuthority('Consultation')")
    @GetMapping(value = "/get/{id}", produces = {"application/json"})
    public ResponseEntity<Object> getUtilisateurById(@PathVariable final Long id) {
        try {
            UtilisateurDTO utilisateurDTO = utilisateurMapper.mapToDto(utilisateurManagement.findByID(id));

            return new ResponseEntity<>(utilisateurDTO, HttpStatus.OK);
        } catch (NotFoundException e) {
            logger.debug(" Erreur sur la méthode getUtilisateurById : " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Recherche la liste de tous les utilisateurs", response = UtilisateurDTO.class)
    @PreAuthorize("hasAuthority('Administration') and hasAuthority('Consultation')")
    @GetMapping(value = "/get", produces = {"application/json"})
    public ResponseEntity<List<UtilisateurDTO>> getUtilisateurs() {

        List<UtilisateurEntity> utilisateurEntityList = utilisateurManagement.findAll();

        List<UtilisateurDTO> listeUtilisateurs = utilisateurMapper.mapToDtoList(utilisateurEntityList);

        return new ResponseEntity<>(listeUtilisateurs, HttpStatus.OK);
    }

    @ApiOperation(value = "Suppression d'un utilisateur à partir de son Id", response = String.class)
    @PreAuthorize("hasAuthority('Administration') and hasAuthority('Suppression')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUtitlisateur(@PathVariable final Long id) {

        try {
            logger.debug(" On recherche l'utilisateur a supprimer ayant pour id : " + id);
            utilisateurManagement.delete(id);
            return new ResponseEntity<>("Utilisateur supprimé avec succès", HttpStatus.OK);
        } catch (NotFoundException e) {
            logger.debug("Erreur de la suppression de l'utilisateur : " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Modification d'un utilisateur", response = UtilisateurDTO.class)
    @PreAuthorize("hasAuthority('Administration') and hasAuthority('Modification')")
    @PutMapping("/update")
    public ResponseEntity<Object> updateUtilisateur(@NotNull @RequestBody final UtilisateurDTO utilisateurDTO) {

        try {
            UtilisateurEntity createdUtilisateurEntity = utilisateurManagement.update(utilisateurMapper.mapToEntity(utilisateurDTO));

            UtilisateurDTO createdUtilisateurDTO = utilisateurMapper.mapToDto(createdUtilisateurEntity);

            return new ResponseEntity<>(createdUtilisateurDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.debug(" Erreur sur la méthode updateUtilisateur : " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);

        }
    }
}
