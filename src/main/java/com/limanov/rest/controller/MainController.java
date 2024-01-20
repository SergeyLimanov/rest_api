package com.limanov.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.limanov.rest.entity.Dog;
import com.limanov.rest.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
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

    @GetMapping("/dogs/list")
    public List<Dog> getAllDog() {

        return dogRepository.findAll();

    }

    @GetMapping("/dogs/id")
    public Dog getDogById(@RequestParam Long id) {

        return dogRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Dog with id: %s is not found.", id)));
    }

    @PostMapping//RequestParam чтобы мы могли в запрос передавать параметры
    public void createOrUpdateDog(@RequestBody Dog dog) {

        try {
            dogRepository.save(dog);
            String dogName = dog.getName() != null ? dog.getName() : "";
            log.info("Add new dog : " + dogName);
        } catch (Exception e) {
            throw new RuntimeException("Error with dog", e);
        }
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) {
        dogRepository.deleteById(id);
    }

}
