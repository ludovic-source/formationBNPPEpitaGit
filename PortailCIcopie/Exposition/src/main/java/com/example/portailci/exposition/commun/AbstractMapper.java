package com.example.portailci.exposition.commun;

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
    public abstract T mapToDto(S entity);

    /**
     *
     * @param dto DTO en retour du client
     * @return l'entité correspondant au DTO reçu
     */
    public abstract  S mapToEntity (T dto);

    /**
     *
     * @param entityList Liste d'entités reçues en paramètre
     * @return Liste de DTO correspondant à l'entité en entrée
     */
    public List<T> mapToDtoList(final List<S> entityList) {
        return entityList.stream().filter(Objects::nonNull).map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     *
     * @param entitySet Set d'entitées reçu en entrée
     * @return Set de DTO correspondant à l'entité
     */
    public Set<T> mapToDtoSet(final Set<S> entitySet) {
        return entitySet.stream().map(this::mapToDto).collect(Collectors.toSet());
    }

    /**
     *
     * @param dtoList Liste de DTO reçus en paramètre
     * @return Liste d'entités correspondant à la DTO en entrée
     */
    public List<S> mapToEntityList(final List<T> dtoList) {
        return dtoList.stream().filter(Objects::nonNull).map(this::mapToEntity).collect(Collectors.toList());
    }
}
