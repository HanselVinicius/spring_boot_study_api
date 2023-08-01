package com.study.people_api.entitiy.people;

import com.study.people_api.entitiy.address.Address;

import java.util.Date;

public record PeopleListDTO(Long id, String nome, String cpf, Date dataDeAniversario, Address endereco, String telefone) {


    public PeopleListDTO(People people){
        this(people.getId(), people.getNome(), people.getCpf(), people.getDataDeAniversario(),people.getEndereco(),people.getTelefone());
    }
}
