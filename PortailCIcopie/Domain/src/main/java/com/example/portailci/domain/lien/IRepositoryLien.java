package com.example.portailci.domain.lien;

import java.util.List;

public interface IRepositoryLien {

    LienEntity save(LienEntity lien);

    LienEntity findById(Integer id);

    List<LienEntity> getLiensByIdThematique(Long idThematique);

    List<LienEntity> findByStatut(String statut);

}
