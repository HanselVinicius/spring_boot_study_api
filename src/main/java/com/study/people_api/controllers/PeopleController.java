package com.study.people_api.controllers;

import com.study.people_api.entitiy.people.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/v1/people")
public class PeopleController {

    @Autowired
    private PeopleRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PeopleRegisterDTO data, UriComponentsBuilder uriBuilder){
        People people = new People(data);
        repository.save(people);
        var uri = uriBuilder.path("/v1/people/{id}").buildAndExpand(people.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailPersonDTO(people));
    }

    @GetMapping
    public ResponseEntity<Page<PeopleListDTO>> getAll(@PageableDefault(size = 10,sort = {"nome"}) Pageable page){
        Page<PeopleListDTO> peoples = repository.findAllByAtivoTrue(page).map(PeopleListDTO::new);
        return ResponseEntity.ok(peoples);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid PeopleUpdateDTO data){
        var person = repository.getReferenceById(data.id());
        person.updateInfo(data);
        return  ResponseEntity.ok(new DetailPersonDTO(person));
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        var person = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetailPersonDTO(person));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var person = repository.getReferenceById(id);
        person.excluir();
        return  ResponseEntity.noContent().build();

    }

}
