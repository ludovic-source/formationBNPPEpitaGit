package com.example.portailci.infrastructure.commun;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractMapper <T, S> {
    /**
     *
     * @param entity Entité présente dans la couche Domain
     * @return Le DTO correspondant à l'entité mappée
     */
    public abstract T mapToVo(S entity);

    /**
     *
     * @param vo VO en retour du client
     * @return l'entité correspondant au VO reçu
     */
    public abstract  S mapToEntity (T vo);

    /**
     *
     * @param entityList Liste d'entités reçues en paramètre
     * @return Liste de VO correspondant à l'entité en entrée
     */
    public List<T> mapToVoList(final List<S> entityList) {
        return entityList.stream().filter(Objects::nonNull).map(this::mapToVo).collect(Collectors.toList());
    }

    /**
     *
     * @param entitySet Set d'entitées reçu en entrée
     * @return Set de VO correspondant à l'entité
     */
    public Set<T> mapToVoSet(final Set<S> entitySet) {
        return entitySet.stream().map(this::mapToVo).collect(Collectors.toSet());
    }

    /**
     *
     * @param voList Liste de VO reçus en paramètre
     * @return Liste d'entités correspondant à la DTO en entrée
     */
    public List<S> mapToEntityList(final List<T> voList) {
        return voList.stream().filter(Objects::nonNull).map(this::mapToEntity).collect(Collectors.toList());
    }
}
