package com.company.vlsu.controllers;

import com.company.vlsu.repository.SensorRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController("/api")
public class ControllerSensor {
    private final SensorRepository repository;
    private final SensorResourceAssembler assembler;

    ControllerSensor(SensorRepository repository, SensorResourceAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/")
    public RepresentationModel root() {

        RepresentationModel rootResource = new RepresentationModel();

        rootResource.add( //
                linkTo(methodOn(ControllerSensor.class).root()).withSelfRel(), //
                linkTo(methodOn(ControllerSensor.class).findAll()).withRel("sensors"));

        return rootResource;
    }

    @GetMapping("/sensors")
    public CollectionModel<EntityModel<Sensor>> findAll() {
        return assembler.toCollectionModel(repository.findAll());
    }

    @PostMapping("/sensors")
    public ResponseEntity<EntityModel<Sensor>> newSensor(@RequestBody Sensor sensor) {

        Sensor savedSensor= repository.save(sensor);

        return savedSensor.getId() //
                .map(id -> ResponseEntity.created( //
                        linkTo(methodOn(SensorRepository.class).findOne(id)).toUri()).body(assembler.toModel(savedSensor)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sensors/{id}")
    public ResponseEntity<EntityModel<Sensor>> findOne(@PathVariable Long id) {

        return repository.findById(id) //
                .map(assembler::toModel) //
                .map(ResponseEntity::ok) //
                .orElse(ResponseEntity.notFound().build());
    }

    }

}
