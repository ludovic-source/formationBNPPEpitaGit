package com.example.portailci.domain.droit;

import java.util.Set;

public interface IRepositoryDroit {

    DroitEntity findById(Long id);

    Set<DroitEntity> findAll();

}
