package com.limanov.rest.mapper;

import com.limanov.rest.dto.DogDto;
import com.limanov.rest.entity.Dog;
import org.springframework.stereotype.Component;

@Component
public class DogMapper extends AbstractMapper<Dog, DogDto> {
    public DogMapper() {
        super(Dog.class, DogDto.class);
    }
}
