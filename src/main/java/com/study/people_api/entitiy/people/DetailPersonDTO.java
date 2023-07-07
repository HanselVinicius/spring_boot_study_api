package com.study.people_api.entitiy.people;

import com.study.people_api.entitiy.address.Address;

import java.util.Date;

public record DetailPersonDTO(Long id,
                              String nome,
                              Date dataDeAniversario,
                              Address address,
                              String telefone,
                              String cpf) {
    public DetailPersonDTO(People people){
        this(people.getId(), people.getNome(), people.getDataDeAniversario(),people.getEndereco(), people.getTelefone(), people.getCpf());
    }
}
