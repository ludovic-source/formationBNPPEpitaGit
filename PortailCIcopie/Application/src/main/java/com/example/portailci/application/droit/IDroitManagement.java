package com.example.portailci.application.droit;

import com.example.portailci.domain.droit.DroitEntity;

import java.util.Set;

public interface IDroitManagement {
    DroitEntity findById(Long id);

    Set<DroitEntity> findAll();

}
