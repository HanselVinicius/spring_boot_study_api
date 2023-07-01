package com.study.people_api.controllers;

import com.study.people_api.entitiy.people.People;
import com.study.people_api.entitiy.people.PeopleRegisterDTO;
import com.study.people_api.entitiy.people.PeopleRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
