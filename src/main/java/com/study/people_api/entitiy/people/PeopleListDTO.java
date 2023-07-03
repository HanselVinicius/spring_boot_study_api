package com.study.people_api.entitiy.people;

import java.util.Date;

public record PeopleListDTO(Long id, String nome, String cpf, Date data_de_aniversario) {


    public PeopleListDTO(People people){
        this(people.getId(), people.getNome(), people.getCpf(), people.getDataDeAniversario());
    }
}
