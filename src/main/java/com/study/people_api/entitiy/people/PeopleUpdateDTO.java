package com.study.people_api.entitiy.people;

import com.study.people_api.entitiy.address.AddressDTO;
import jakarta.validation.constraints.NotNull;

public record PeopleUpdateDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        AddressDTO endereco
) {
}
