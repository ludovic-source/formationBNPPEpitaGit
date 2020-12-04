package com.example.portailci.exposition.droit;

import com.example.portailci.exposition.commun.AbstractMapper;
import com.example.portailci.domain.droit.DroitEntity;
import org.springframework.stereotype.Component;


@Component
public class DroitMapper extends AbstractMapper<DroitDTO, DroitEntity> {
    @Override
    public DroitDTO mapToDto(DroitEntity entity) {

        DroitDTO dto = new DroitDTO();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    @Override
    public DroitEntity mapToEntity(DroitDTO dto) {

        DroitEntity entity = new DroitEntity();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setDescription(dto.getDescription());

        return entity;
    }
}
