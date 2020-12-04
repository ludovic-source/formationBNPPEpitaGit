package com.example.portailci.infrastructure.droit;

import com.example.portailci.domain.droit.DroitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDroitJpaRepository extends JpaRepository<DroitEntity, Long> {

}
