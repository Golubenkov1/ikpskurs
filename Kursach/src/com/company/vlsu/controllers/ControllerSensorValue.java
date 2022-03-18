package com.company.vlsu.controllers;

import com.company.vlsu.repository.SensorValueRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController("/api")
public class ControllerSensorValue {
    private final SensorValueRepository repository;
    private final SensorValueResourceAssembler assembler;

    ControllerSensorValue(SensorValueRepository repository, SensorValueResourceAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/")
    public RepresentationModel root() {

        RepresentationModel rootResource = new RepresentationModel();

        rootResource.add( //
                linkTo(methodOn(ControllerSensorValue.class).root()).withSelfRel(), //
                linkTo(methodOn(ControllerSensorValue.class).findAll()).withRel("sensorValues"));

        return rootResource;
    }

    @GetMapping("/sensorValues")
    public CollectionModel<EntityModel<SensorValue>> findAll() {
        return assembler.toCollectionModel(repository.findAll());
    }

    @PostMapping("/sensorValues")
    public ResponseEntity<EntityModel<SensorValue>> newSensor(@RequestBody SensorValue sensorValue) {

        SensorValue savedSensorValue= repository.save(sensorValue);

        return savedSensorValue.getId() //
                .map(id -> ResponseEntity.created( //
                        linkTo(methodOn(SensorValueRepository.class).findOne(id)).toUri()).body(assembler.toModel(savedSensorValue)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sensorValues/{id}")
    public ResponseEntity<EntityModel<SensorValue>> findOne(@PathVariable Long id) {

        return repository.findById(id) //
                .map(assembler::toModel) //
                .map(ResponseEntity::ok) //
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/getSensorValues")
    public SensorValue selectListSensor(@RequestParam Date date, @RequestParam Time time, @RequestParam String type ){
        Gson g = new Gson();
        SensorValue sensorValue = g.fromJson(url+"{date}"+"{time}"+"{type}", SensorValue.class,date, time, type);
        return sensorValue;
    }
    }


