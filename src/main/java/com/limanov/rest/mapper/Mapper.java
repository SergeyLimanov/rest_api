package com.limanov.rest.mapper;

import java.util.List;

public interface Mapper<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntityList(List<D> dtos);

    List<D> toDtoList(List<E> entities);
}
