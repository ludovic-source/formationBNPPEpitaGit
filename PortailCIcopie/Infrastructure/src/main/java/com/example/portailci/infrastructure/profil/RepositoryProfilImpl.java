package com.example.portailci.infrastructure.profil;

import com.example.portailci.domain.profil.IRepositoryProfil;
import com.example.portailci.domain.profil.ProfilEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class RepositoryProfilImpl implements IRepositoryProfil {

    @Autowired
    private IProfilJpaRepository profilJpaRepository;

    private static ProfilEntity profilEntity;

    @Override
    public ProfilEntity findById(Long id) {
        Optional<ProfilEntity> profil =  profilJpaRepository.findById(id);

        if (profil.isPresent()) {
            profil.ifPresent(TheDroit -> lambdaFindById(TheDroit));
            return this.profilEntity;
        }
        else {
            return null;
        }
    }

    public static void lambdaFindById(ProfilEntity profil) {
        profilEntity = profil;
    }

    @Override
    public Optional<ProfilEntity> findByNom(String nom) {

        return profilJpaRepository.findByNom(nom);
    }

    @Override
    public Set<ProfilEntity> findAll() {

        List<ProfilEntity> listeProfils =  profilJpaRepository.findAll();

        return new HashSet<>(listeProfils);
    }

    @Override
    public ProfilEntity create(ProfilEntity profil) {
        return profilJpaRepository.save(profil);
    }

    @Override
    public ProfilEntity update(ProfilEntity profil) {
        return profilJpaRepository.save(profil);
    }

    @Override
    public void delete(ProfilEntity profil) {

        profilJpaRepository.delete(profil);
    }
}
