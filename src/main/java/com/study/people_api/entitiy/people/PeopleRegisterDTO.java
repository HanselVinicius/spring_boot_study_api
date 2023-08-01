package com.study.people_api.entitiy.people;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.people_api.entitiy.address.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record PeopleRegisterDTO(
    @NotBlank
     String nome,

     @JsonFormat(pattern = "MMM dd, yyyy hh:mm:ss a",locale = "en")
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
