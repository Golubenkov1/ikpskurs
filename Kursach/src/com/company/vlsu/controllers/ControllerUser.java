package com.company.vlsu.controllers;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.company.vlsu.repository.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController("/api")
public class ControllerUser {
    private final UserRepository repository;
    private final UserResourceAssembler assembler;

    ControllerUser(UserRepository repository, UserResourceAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/")
    public RepresentationModel root() {

        RepresentationModel rootResource = new RepresentationModel();

        rootResource.add( //
                linkTo(methodOn(ControllerUser.class).root()).withSelfRel(), //
                linkTo(methodOn(ControllerUser.class).findAll()).withRel("users"));

        return rootResource;
    }

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> findAll() {
        return assembler.toCollectionModel(repository.findAll());
    }

    @PostMapping("/users")
    public ResponseEntity<EntityModel<User>> newUser(@RequestBody User user) {

        User savedUser = repository.save(user);

        return savedUser.getId() //
                .map(id -> ResponseEntity.created( //
                        linkTo(methodOn(UserRepository.class).findOne(id)).toUri()).body(assembler.toModel(savedUser)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<EntityModel<User>> findOne(@PathVariable Long id) {

        return repository.findById(id) //
                .map(assembler::toModel) //
                .map(ResponseEntity::ok) //
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addUser")
    public ResponseEntity<EntityModel<User>> addUser(@RequestParam String name,@RequestParam  String email,@RequestParam  String password,@RequestParam
            String role,@RequestParam  String contactNumber) {
        User user = new User(name, email, password, role,contactNumber);
        repository.userRepository.save(user);
        return user;
    }
    }

}
