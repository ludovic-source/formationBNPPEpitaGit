package com.example.portailci.infrastructure.lien;

import com.example.portailci.domain.lien.IRepositoryLien;
import com.example.portailci.domain.lien.LienEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RepositoryLienImpl implements IRepositoryLien {

    private IJpaRepositoryLien jpaRepositoryLien;

    private static LienEntity lienEntity;

    public RepositoryLienImpl(IJpaRepositoryLien jpaRepositoryLien) {
        this.jpaRepositoryLien = jpaRepositoryLien;
    }

    @Override
    public LienEntity save(LienEntity lien) {
        return jpaRepositoryLien.save(lien);
    }

    @Override
    public LienEntity findById(Integer id) {
        Optional<LienEntity> lien = jpaRepositoryLien.findById(id);
        if (lien.isPresent()) {
            lien.ifPresent(TheLien -> lambdaFindById(TheLien));
            return this.lienEntity;
        }
        else {
            return null;
        }
    }

    public static void lambdaFindById(LienEntity lien) {
        lienEntity = lien;
    }

    @Override
    public List<LienEntity> getLiensByIdThematique(Long idThematique) {
        return jpaRepositoryLien.getLiensByIdThematique(idThematique);
    }

    @Override
    public List<LienEntity> findByStatut(String statut) {
        return jpaRepositoryLien.findByStatut(statut);
    }


}
