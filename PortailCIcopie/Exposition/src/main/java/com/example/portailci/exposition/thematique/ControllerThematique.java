package com.example.portailci.exposition.thematique;

import com.example.portailci.application.lien.LienControleDonneesException;
import com.example.portailci.application.thematique.IManagementThematique;
import com.example.portailci.domain.thematique.ThematiqueEntity;


import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Api(value = "API Thématique", description = "Api permettant de faire des opérations CRUD sur les thématiques")
@RestController
@RequestMapping("/portailci/thematique")
public class ControllerThematique {

    @Autowired
    IManagementThematique managementThematique;

    @PostMapping(value="/create", produces={"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('Création')")
    @ApiOperation(value = "Créer une thématique")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thématique créée",
                examples=@Example(
                    value={
                            @ExampleProperty(
                                    mediaType = "Example json",
                                    value = "{\"nom\": \"Ligue 1\",\"description\": \"Championnat de France de football\",\"idParent\": 2}"
                            )
                    }
    ))
    })
    public ThematiqueEntity create(@RequestBody ThematiqueEntity thematique) {
        System.out.println("<Controller> Thématique - create : " + thematique.getNom());
        if (thematique != null) {
            String extension = "";
            if (thematique.getImagePath().length() != 0) {
                String[] decoupage = thematique.getImagePath().split("\\.");
                extension = decoupage[decoupage.length - 1];
            }
            managementThematique.createThematique(thematique, extension);
        }
        return thematique;
    }

    @ApiOperation(value = "Chargement d'un fichier", response = String.class)
    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('Création','Modification')")
    public String FileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        System.out.println("<Controller> Thématique - upload");
        if(file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Choisissez un fichier à uploader");
            return "redirect:uploadStatus";
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("Exposition/src/main/resources/files/" + file.getOriginalFilename());
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message", "fichier " + file.getOriginalFilename() + "uploadé");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/uploadStatus";
    }

    @ApiOperation(value = "Chargement d'un fichier", response = String.class)
    @PostMapping(value = "/createupload", consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('Création')")
    public ThematiqueEntity createupload(@RequestParam("file") MultipartFile file, ThematiqueDTO thematiqueDTO) {
        System.out.println("<Controller> Thématique - createupload : file : " + thematiqueDTO.getFile().getOriginalFilename());
        System.out.println("<Controller> Thématique - createupload : thématique : " + thematiqueDTO.getNom() + " / idParent : " + thematiqueDTO.getIdParent());

        // Etape 1 : création de la thématique
        ThematiqueEntity thematiqueRetour = null;
        if (thematiqueDTO.getNom() != null) {
            ThematiqueEntity thematiqueACreer = new ThematiqueEntity();
            thematiqueACreer.setNom(thematiqueDTO.getNom());
            thematiqueACreer.setIdParent(thematiqueDTO.getIdParent());
            thematiqueACreer.setDescription(thematiqueDTO.getDescription());

            String extension = "";
            if (thematiqueDTO.getFile().getOriginalFilename().length() != 0) {
                String[] decoupage = thematiqueDTO.getFile().getOriginalFilename().split("\\.");
                extension = decoupage[decoupage.length - 1];
            }
            thematiqueACreer.setImagePath(null);

            thematiqueRetour=managementThematique.createThematique(thematiqueACreer, extension);
        }

        if ((thematiqueRetour.getNiveau() == 1)
        && (thematiqueDTO.getFile().getOriginalFilename() != null)
        && (thematiqueDTO.getFile().getOriginalFilename().length() != 0)) {
            // Etape 2 : fabrication du nom du fichier à partir de l'id de la thématique
            String cheminNom = pathImage(thematiqueDTO.getFile().getOriginalFilename(), thematiqueRetour.getId());

            // Etape 3 : upload du fichier
            try {
                byte[] bytes = thematiqueDTO.getFile().getBytes();
                Files.write(Paths.get(cheminNom), bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Etape 4 : mise à jour de l'image
            thematiqueRetour.setImagePath(cheminNom);
            thematiqueRetour = managementThematique.updateThematique(thematiqueRetour);
        }

        return thematiqueRetour;
    }

    private String pathImage(String filename, Long id) {
        String chemin ="";
        String racine = "Exposition/src/main/resources/files/";
        if(filename != null) {
            chemin += racine;
            chemin += "img";
            chemin += id;

            // recherche de l'extension du fichier initial
            String[] decoupage = filename.split("\\.");
            String extension = "." + decoupage[decoupage.length-1];
            chemin += extension;
        }
        return chemin;
    }

    @ApiOperation(value = "Modification d'un thématique", response = ThematiqueEntity.class)
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('Modification')")
    public ThematiqueEntity update(@RequestBody ThematiqueEntity thematique) {
        System.out.println("<Controller> Thématique - update : " + thematique.getNom() + " / id : " + thematique.getId());
        if (thematique != null) {
            managementThematique.updateThematique(thematique);
        }
        return thematique;
    }

    @ApiOperation(value = "Modification du chargement d'un fichier", response = ThematiqueEntity.class)
    @PostMapping(value = "/updateupload", consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('Modification')")
    public ThematiqueEntity updateupload(@RequestParam("file") MultipartFile file, @RequestParam("theme") ThematiqueDTO thematiqueDTO) {
        System.out.println("<Controller> Thématique - updateupload : file : " + thematiqueDTO.getFile().getOriginalFilename());
        System.out.println("<Controller> Thématique - updateupload : thématique : " + thematiqueDTO.getNom() + " / idParent : " + thematiqueDTO.getIdParent());

        // Recherche de la thématique à modifier
        ThematiqueEntity thematiqueAModifier = managementThematique.findThematique(thematiqueDTO.getId());

        // Surcharge de ses attributs par ceux passés dans le DTO pour la modification
        thematiqueAModifier.setNom(thematiqueDTO.getNom());
        thematiqueAModifier.setDescription(thematiqueDTO.getDescription());
        thematiqueAModifier.setIdParent(thematiqueDTO.getIdParent());

        String cheminNom = "";
        // La thématique est de niveau 1 si elle a un idParent = 0
        // ce n'est que dans ce cas qu'elle peut avoir une image
        if ((thematiqueAModifier.getIdParent() == 0)
                && (thematiqueDTO.getFile().getOriginalFilename() != null)
                && (thematiqueDTO.getFile().getOriginalFilename().length() != 0)) {
            // Fabrication du nom du fichier à partir de l'id de la thématique (on écrase le fichier si il existe, si plus d'image on garde un fichier qui n'est pas lié à la thématique)
            cheminNom = pathImage(thematiqueDTO.getFile().getOriginalFilename(), thematiqueAModifier.getId());

            // Upload du fichier
            try {
                byte[] bytes = thematiqueDTO.getFile().getBytes();
                Files.write(Paths.get(cheminNom), bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Mise à jour de l'image
        thematiqueAModifier.setImagePath(cheminNom);
        thematiqueAModifier = managementThematique.updateThematique(thematiqueAModifier);

        return thematiqueAModifier;
    }

    // ----------------------------------------------------------------------------------
    // --------------------- NOUVELLE VERSION LUDO --------------------------------------
    // ----------------------------------------------------------------------------------
    @PostMapping(value = "/updateupload/id", consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('Modification')")
    public ThematiqueEntity updateuploadById(@RequestParam("file") MultipartFile file, @RequestParam("theme") Long id) {
        //System.out.println("<Controller> Thématique - updateupload : thématique : " + thematique.getNom() + " / idParent : " + thematique.getIdParent());

        if(file.isEmpty()) {
            Log logger = LogFactory.getLog(ControllerThematique.class);
            logger.error("couche exposition - controllerThematique --> fichier à uploader vide");
            throw (new LienControleDonneesException("couche exposition - controllerThematique --> fichier à uploader vide"));
        }

        // Recherche de la thématique à modifier
        ThematiqueEntity thematiqueAModifier = managementThematique.findThematique(id);

        // Surcharge de ses attributs par ceux passés dans le DTO pour la modification
        thematiqueAModifier.setNom(thematiqueAModifier.getNom());
        thematiqueAModifier.setDescription(thematiqueAModifier.getDescription());
        thematiqueAModifier.setIdParent(thematiqueAModifier.getIdParent());

        String cheminNom = "";
        // La thématique est de niveau 1 si elle a un idParent = 0
        // ce n'est que dans ce cas qu'elle peut avoir une image
        if ((thematiqueAModifier.getIdParent() == 0)
                && (file.getOriginalFilename() != null)
                && (file.getOriginalFilename().length() != 0)) {
            // Fabrication du nom du fichier à partir de l'id de la thématique (on écrase le fichier si il existe, si plus d'image on garde un fichier qui n'est pas lié à la thématique)
            cheminNom = pathImage(file.getOriginalFilename(), thematiqueAModifier.getId());

            // Upload du fichier
            try {
                byte[] bytes = file.getBytes();
                Files.write(Paths.get(cheminNom), bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Mise à jour de l'image
        thematiqueAModifier.setImagePath(cheminNom);
        thematiqueAModifier = managementThematique.updateThematique(thematiqueAModifier);

        return thematiqueAModifier;
    }

    @GetMapping(value = "image/id/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    @PreAuthorize("hasAuthority('Consultation')")
    public @ResponseBody byte[] getImageById(@PathVariable("id") Long idTheme) throws IOException {
        Log logger = LogFactory.getLog(ControllerThematique.class);
        if (idTheme != 0) {
            ThematiqueEntity thematique = managementThematique.findThematique(idTheme);
            String cheminImage = thematique.getImagePath();
            System.out.println("chemin image = " + cheminImage);
            //cheminImage = cheminImage.substring(29);
            //InputStream in = getClass().getResourceAsStream(cheminImage);
            InputStream in = new FileInputStream(cheminImage);
            return IOUtils.toByteArray(in);

        }
        else {
            logger.error("couche exposition - controllerThematique --> récupération impossible car l'identifiant du thème est absent");
            throw (new LienControleDonneesException("couche exposition - controllerThematique --> récupération impossible car l'identifiant du thème est absent"));
        }
    }


    @ApiOperation(value = "Récupération d'une collection de thématiques enfants à partir de l'id d'une thématique parent", response = ThematiqueEntity.class)
    @GetMapping(value = "/findenfants/{idParent}", produces = {"application/json"})
    @PreAuthorize("hasAuthority('Consultation')")
    public List<ThematiqueEntity> find(@PathVariable("idParent") Long idParent) {
        System.out.println("<Controller> Thématique - find enfants de " + idParent);
        return managementThematique.findThematiquesEnfants(idParent);
    }

    @ApiOperation(value = "Récupération d'une thématique à partir de son id", response = ThematiqueEntity.class)
    @GetMapping(value = "/find/{idThematique}", produces = {"application/json"})
    @PreAuthorize("hasAuthority('Consultation')")
    public ThematiqueEntity thematiqueFind(@PathVariable("idThematique") Long id) {
        System.out.println("<Controller> Thématique - find id = " + id);
        return managementThematique.findThematique(id);
    }

    @ApiOperation(value = "Suppression d'une thématique à partir de son id", response = ThematiqueEntity.class)
    @DeleteMapping(value = "delete/{idThematique}")
    @PreAuthorize("hasAuthority('Suppression')")
    public void deleteThematique(@PathVariable("idThematique") Long id) {
        System.out.println("<Controller> Thématique - delete thématique " + id);
        managementThematique.deleteThematique(id);
    }

}
