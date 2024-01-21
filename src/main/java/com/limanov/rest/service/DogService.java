package com.limanov.rest.service;

import com.limanov.rest.dto.DogDto;

import java.util.List;

public interface DogService {

    DogDto getById(Long id);

    List<DogDto> getAllDogs();

    DogDto createOrUpdate(DogDto dto);

    void deleteById(Long id);
}
