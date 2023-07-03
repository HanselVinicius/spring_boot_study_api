package com.study.people_api.entitiy.address;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String cep;
    private String uf;

    public Address(AddressDTO data) {
        this.rua = data.rua();
        this.numero = data.numero();
        this.complemento = data.complemento();
        this.bairro = data.bairro();
        this.cidade = data.cidade();
        this.cep = data.cep();
        this.uf = data.uf();
    }

    public void atualizarInformacoes(AddressDTO dados) {
        if(dados.rua() != null){
            this.rua = dados.rua();
        }if(dados.bairro() != null){
            this.bairro = dados.bairro();
        }if(dados.cep() != null){
            this.cep = dados.cep();
        }if(dados.uf() != null){
            this.uf = dados.uf();
        }if(dados.cidade() != null){
            this.cidade = dados.cidade();
        }if(dados.numero() != null){
            this.numero = dados.numero();
        }if(dados.complemento() != null){
            this.complemento = dados.complemento();
        }
    }
}
