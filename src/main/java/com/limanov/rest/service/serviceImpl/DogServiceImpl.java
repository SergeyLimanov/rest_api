package com.limanov.rest.service.serviceImpl;

import com.limanov.rest.dto.DogDto;
import com.limanov.rest.entity.Dog;
import com.limanov.rest.mapper.DogMapper;
import com.limanov.rest.repository.DogRepository;
import com.limanov.rest.service.DogService;
import jakarta.validation.Validator;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Slf4j
@Data
@Service
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;
    private final DogMapper mapper;
    private final Validator validator;

    @Override
    public DogDto getById(Long id) {
        return mapper.toDto(dogRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Dog with id: %s is not found.", id))));
    }

    @Override
    public List<DogDto> getAllDogs() {
        return mapper.toDtoList(dogRepository.findAll());
    }


    @Override
    public DogDto createOrUpdate(DogDto dto) {
        return mapper.toDto(dogRepository.save(mapper.toEntity(dto)));
    }

    @Override
    public void deleteById(Long id) {
        Dog deletedDog = dogRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Сотрудник с ИД: %s отсутствует.", id)));
        dogRepository.delete(deletedDog);
    }
}
