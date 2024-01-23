package com.limanov.rest.controller;

import com.limanov.rest.dto.DogDto;
import com.limanov.rest.service.DogService;
import com.limanov.rest.service.KafkaProducer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "main Dog controller")
@Slf4j
@RequestMapping("/api")
@Data
public class MainController {
    private final DogService dogService;
    private final KafkaProducer kafkaProducer;


    @GetMapping(value = "{firstName}")
    public String getMainMessage(@PathVariable("firstName") String firstName) {
        return String.format(
                "{\" \n message\":\"Hello %s\" \n}", firstName);
    }

    @Operation(
            summary = "get all Dog from APP"
    )
    @GetMapping("/dogs/list")
    public List<DogDto> getAllDog() {

        return dogService.getAllDogs();
    }

    @Operation(
            summary = "get Dog from APP by id"
    )
    @GetMapping("/dogs/id")
    public DogDto getDogById(@RequestParam Long id) {
        DogDto byId = dogService.getById(id);
        kafkaProducer.produce(byId);
        return byId;
    }

    @Operation(
            summary = "add or update dog into Application",
            description = "get Dog dto, convert to Dog and put to data base"
    )
    @PostMapping
    public DogDto createOrUpdateDog(@RequestBody DogDto dogDto) {

        return dogService.createOrUpdate(dogDto);
    }

    @DeleteMapping
    @Operation(
            summary = "remove Dog from APP by id"
    )
    public void delete(@RequestParam Long id) {
        dogService.deleteById(id);
    }

}
