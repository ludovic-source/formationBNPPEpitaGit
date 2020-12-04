package com.example.portailci.exposition.lien;

import com.example.portailci.application.lien.IManagementLien;
import com.example.portailci.application.lien.LienControleDonneesException;
import com.example.portailci.domain.lien.LienEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "API Liens", description = "API permettant de faire des opérations CRUD sur les liens")
@RestController
@RequestMapping("/portailci/lien")
public class ControllerLien {

    @Autowired
    IManagementLien managementLien;

    private static final Log logger = LogFactory.getLog(ControllerLien.class);

    @ApiOperation(value = "Création d'un lien", response = LienEntity.class)
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('Création')")
    public LienEntity create(@RequestBody LienEntity lien) {
        if (lien != null) {
            return managementLien.createLien(lien);
        }
        else {
            logger.error("couche exposition - controllerLien --> create : le lien n'est pas renseigné");
            throw (new LienControleDonneesException("couche exposition - controllerLien --> create : le lien n'est pas renseigné"));
        }
    }

    @ApiOperation(value = "Modification d'un lien", response = LienEntity.class)
    @PutMapping("/modifier")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('Modification')")
    public LienEntity modifier(@RequestBody LienEntity lien) {
        if (lien != null) {
            return managementLien.prePublierLien(lien);
        }
        else {
            logger.error("couche exposition - controllerLien --> modifier : le lien n'est pas renseigné");
            throw (new LienControleDonneesException("couche exposition - controllerLien --> modifier : le lien n'est pas renseigné"));
        }
    }

    @ApiOperation(value = "Modification du statut de publication en \"Publié\" d'un lien", response = LienEntity.class)
    @PutMapping("/publier")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('Modification')")
    public LienEntity publier(@RequestBody LienEntity lien) {
        if (lien != null) {
            return managementLien.publierLien(lien);
        }
        else {
            logger.error("couche exposition - controllerLien --> publier : le lien n'est pas renseigné");
            throw (new LienControleDonneesException("couche exposition - controllerLien --> publier : le lien n'est pas renseigné"));
        }
    }

    @ApiOperation(value = "Modification du statut de publication en \"Dépublié\" d'un lien", response = LienEntity.class)
    @PutMapping("/depublier")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('Suppression')")
    public LienEntity depublier(@RequestBody LienEntity lien) {
        if (lien != null) {
            return managementLien.depublierLien(lien);
        }
        else {
            logger.error("couche exposition - controllerLien --> dépublier : le lien n'est pas renseigné");
            throw (new LienControleDonneesException("couche exposition - controllerLien --> dépublier : le lien n'est pas renseigné"));
        }
    }

    @ApiOperation(value = "Récupération d'un lien par son id", response = LienEntity.class)
    @GetMapping("/find/id/{id}")
    @PreAuthorize("hasAuthority('Consultation')")
    public LienEntity findByIdLien(@PathVariable("id") Integer idLien) {
        if (idLien != 0) {
            return managementLien.findById(idLien);
        }
        else {
            logger.error("couche exposition - controllerLien --> findById : l'identifiant du lien n'est pas renseigné");
            throw (new LienControleDonneesException("couche exposition - controllerLien --> findById : l'identifiant du lien n'est pas renseigné"));
        }
    }

    // API REST qui renvoie tous les liens, sauf les liens dépubliés
    @ApiOperation(value = "Récupération d'une collection de liens à partir de l'id d'une thématique", response = LienEntity.class)
    @GetMapping("/find/thematique/{id}")
    @PreAuthorize("hasAuthority('Consultation')")
    public List<LienEntity> findAllByThematique(@PathVariable("id") Long idThematique) {
        if (!idThematique.equals(0L)) {
            return managementLien.getLiensByIdThematique(idThematique);
        }
        else {
            logger.error("couche exposition - controllerLien --> findAllByIdThematique : l'id thematique n'est pas renseigné");
            throw (new LienControleDonneesException("couche exposition - controllerLien --> findAllByIdThematique : l'id thematique n'est pas renseigné"));
        }
    }

    // API REST qui renvoie tous les liens dépubliés
    @ApiOperation(value = "Récupération d'une collection des liens dont le statut de publication est \"Dépublié\"", response = LienEntity.class)
    @GetMapping("/find/depublies")
    @PreAuthorize("hasAuthority('Administration')")
    public List<LienEntity> getAllLiensDepublies() {
        return managementLien.getAllLiensDepublies();
    }
}
