package com.limanov.rest.mapper;


import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractMapper<E, D> implements Mapper<E, D> {

    @Autowired
    ModelMapper mapper;

    private Class<E> entityClass;
    private Class<D> dtoClass;

    protected AbstractMapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public E toEntity(D dto) {
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, entityClass);
    }

    @Override
    public D toDto(E entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, dtoClass);
    }

    @Override
    public List<E> toEntityList(List<D> dtos) {
        return Objects.isNull(dtos)
                ? null
                : dtos.stream().map(d -> mapper.map(d, entityClass)).collect(Collectors.toList());
    }

    @Override
    public List<D> toDtoList(List<E> entities) {
        return Objects.isNull(entities)
                ? null
                : entities.stream().map(e -> mapper.map(e, dtoClass)).collect(Collectors.toList());
    }


    protected Converter<D, E> toEntityConverter() {
        return context -> {
            D source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    protected void mapSpecificFields(D source, E destination) {
    }
}
