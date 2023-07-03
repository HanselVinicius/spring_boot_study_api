package com.study.people_api.entitiy.people;


import com.study.people_api.entitiy.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "peoples_tb")
@Entity(name = "People")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class People {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Date dataDeAniversario;
    @Embedded
    private Address endereco;
    private String telefone;
    private String cpf;
    private boolean ativo;

    public People(PeopleRegisterDTO data) {
        this.ativo = true;
        this.nome = data.nome();
        this.dataDeAniversario = data.dataDeAniversario();
        this.endereco = new Address(data.endereco());
        this.telefone = data.telefone();
        this.cpf = data.cpf();
    }

    public void updateInfo(PeopleUpdateDTO data) {
        if (data.nome() != null){
            this.nome = data.nome();
        }

        if(data.telefone() != null){
            this.telefone = data.telefone();
        }
        if(data.endereco() != null){
            this.endereco.atualizarInformacoes(data.endereco());
        }
    }


    public void excluir() {
        this.ativo = false;
    }
}
