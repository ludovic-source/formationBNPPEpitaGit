package com.example.portailci.application.droit;

import com.example.portailci.domain.droit.DroitEntity;
import com.example.portailci.domain.droit.IRepositoryDroit;
import com.example.portailci.domain.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class DroitManagementImpl implements IDroitManagement {

    @Autowired
    IRepositoryDroit repositoryDroit;

    private static final Logger LOG  = LoggerFactory.getLogger(DroitManagementImpl.class);

    @Override
    public DroitEntity findById(Long id) {
        LOG.debug("Couche Application/DroiManagementImpl/findById " + id);
        DroitEntity droitEntity = repositoryDroit.findById(id);
        if(droitEntity != null) return droitEntity;
        else throw new NotFoundException("Aucun droit portant l'id : " + id + " n'a été trouvé.");
    }

    @Override
    public Set<DroitEntity> findAll() {
        LOG.debug("Couche Application/DroiManagementImpl/findAll");
        return repositoryDroit.findAll();
    }
}
