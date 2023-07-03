package com.study.people_api.controllers;

import com.study.people_api.entitiy.people.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/people")
public class PeopleController {

    @Autowired
    private PeopleRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid PeopleRegisterDTO data){
         repository.save(new People(data));
    }

    @GetMapping
    public Page<PeopleListDTO> getAll(@PageableDefault(size = 10,sort = {"nome"}) Pageable page){
        return repository.findAllByAtivoTrue(page).map(PeopleListDTO::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid PeopleUpdateDTO data){
        var medico = repository.getReferenceById(data.id());
        medico.updateInfo(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        var person = repository.getReferenceById(id);
        person.excluir();
    }

}
