package com.example.portailci.exposition.profil;

import com.example.portailci.exposition.commun.AbstractMapper;
import com.example.portailci.domain.droit.DroitEntity;
import com.example.portailci.domain.profil.ProfilEntity;
import com.example.portailci.exposition.droit.DroitDTO;
import com.example.portailci.exposition.droit.DroitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ProfilMapper extends AbstractMapper<ProfilDTO, ProfilEntity> {

    @Autowired
    private DroitMapper droitMapper;

    @Override
    public ProfilDTO mapToDto(ProfilEntity entity) {

        ProfilDTO dto = new ProfilDTO();

        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setDescription(entity.getDescription());
        // Conversion du Set<DroitEntity> en Set<DroitDTO>
        Set<DroitDTO> droitsDTOSet = new HashSet<>();
        if(entity.getDroits() != null) {

            for (DroitEntity droitEntity : entity.getDroits()) {
                droitsDTOSet.add(droitMapper.mapToDto(droitEntity));
            }
        }
        dto.setDroits(droitsDTOSet);

        return dto;
    }

    @Override
    public ProfilEntity mapToEntity(ProfilDTO dto) {

        ProfilEntity profilEntity = new ProfilEntity();

        if(dto.getId() != null) {
            profilEntity.setId(dto.getId());
        }
        profilEntity.setNom(dto.getNom());
        profilEntity.setDescription(dto.getDescription());
        // Conversion du Set<DroitDTO> en Set<DroitEntity>
        Set<DroitEntity> droitEntitySet = new HashSet<>();
        if(dto.getDroits() != null) {
            for (DroitDTO droitDTO : dto.getDroits()) {
                droitEntitySet.add(droitMapper.mapToEntity(droitDTO));
            }
        }
        profilEntity.setDroits(droitEntitySet);

        return profilEntity;
    }



}
