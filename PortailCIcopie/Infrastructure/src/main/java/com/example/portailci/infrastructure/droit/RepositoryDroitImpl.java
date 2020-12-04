package com.example.portailci.infrastructure.droit;

import com.example.portailci.domain.droit.DroitEntity;
import com.example.portailci.domain.droit.IRepositoryDroit;
import com.example.portailci.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class RepositoryDroitImpl implements IRepositoryDroit {

    @Autowired
    private IDroitJpaRepository droitJpaRepository;

    private static DroitEntity  droitEntity;

    @Override
    public DroitEntity findById(Long id) {
        Optional<DroitEntity> droit =  droitJpaRepository.findById(id);
        if (droit.isPresent()) {
            droit.ifPresent(TheDroit -> lambdaFindById(TheDroit));
            return this.droitEntity;
        }
        else {
            return null;
        }
    }

    public static void lambdaFindById(DroitEntity droit) {
        droitEntity = droit;
    }

    @Override
    public Set<DroitEntity> findAll() {

        List<DroitEntity> listeDroits = droitJpaRepository.findAll();

        return new HashSet<>(listeDroits);
    }
}
