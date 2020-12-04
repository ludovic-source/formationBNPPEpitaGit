package com.example.portailci.exposition.droit;

import com.example.portailci.application.droit.IDroitManagement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/portailci/droits")
@Api(value = "API Droit", description = "API permettant de récupérer les droits d'accès à l'application")
public class DroitController {

    private static Logger logger = LoggerFactory.getLogger(DroitController.class);

    @Autowired
    private DroitMapper droitMapper;

    @Autowired
    IDroitManagement droitManagement;

    @ApiOperation(value = "Récupération d'un droit à partir de son Id", response = DroitDTO.class)
    @PreAuthorize("hasAuthority('Administration') and hasAuthority('Consultation')")
    @GetMapping( value ="/get/{id}", produces = {"application/json"})
    public ResponseEntity<DroitDTO> getDroitById(@PathVariable final Long id) {

        DroitDTO droitDTO =  droitMapper.mapToDto(droitManagement.findById(id));
        return new ResponseEntity<>(droitDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Récupération d'une collection des droits", response = DroitDTO.class)
    @PreAuthorize("hasAuthority('Administration') and hasAuthority('Consultation')")
    @GetMapping(value = "/get", produces = {"application/json"})
    public ResponseEntity<Set<DroitDTO>> getDroits() {

        Set<DroitDTO> droitDTOSet = droitMapper.mapToDtoSet(droitManagement.findAll());
        return new ResponseEntity<>(droitDTOSet, HttpStatus.OK);
    }


}
