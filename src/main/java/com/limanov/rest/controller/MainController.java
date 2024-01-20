package com.limanov.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.limanov.rest.dto.DogDto;
import com.limanov.rest.entity.Dog;
import com.limanov.rest.repository.DogRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@Tag(name = "main Dog controller")
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class MainController {

    private final DogRepository dogRepository;
    private final ObjectMapper objectMapper;

    @GetMapping("/main")
    public String getMainMessage() {
        return "Hello World!";
    }

    @Operation(
            summary = "get all Dog from APP"
    )
    @GetMapping("/dogs/list")
    public List<Dog> getAllDog() {
        return dogRepository.findAll();
    }

    @Operation(
            summary = "get Dog from APP by id"
    )
    @GetMapping("/dogs/id")
    public Dog getDogById(@RequestParam Long id) {

        return dogRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Dog with id: %s is not found.", id)));
    }

    @Operation(
            summary = "add or update dog into Application",
            description = "get Dog dto, convert to Dog and put to data base"
    )
    @PostMapping//RequestParam чтобы мы могли в запрос передавать параметры
    public void createOrUpdateDog(@RequestBody DogDto dogDto) {

        try {
            dogRepository.save(
                    Dog.builder()
                            .age(dogDto.getAge())
                            .name(dogDto.getName())
                            .weight(dogDto.getWeight())
                            .build());
            String dogName = dogDto.getName() != null ? dogDto.getName() : "";
            log.info("Add new dog : " + dogName);
        } catch (Exception e) {
            throw new RuntimeException("Error with dog", e);
        }
    }

    @DeleteMapping
    @Operation(
            summary = "remove Dog from APP by id"
    )
    public void delete(@RequestParam Long id) {
        dogRepository.deleteById(id);
    }

}
