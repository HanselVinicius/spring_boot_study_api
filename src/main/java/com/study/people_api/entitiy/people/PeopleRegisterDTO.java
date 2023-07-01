package com.study.people_api.entitiy.people;

import com.study.people_api.entitiy.address.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record PeopleRegisterDTO(
    @NotBlank
     String nome,
     Date dataDeAniversario,
    @NotNull
    @Valid
     AddressDTO endereco,
    @NotBlank
     String telefone,
    @NotBlank
//    @Pattern(regexp = "^\\\\d{3}\\\\.\\\\d{3}\\\\.\\\\d{3}-\\\\d{2}$")
     String cpf
){}
