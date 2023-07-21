package com.study.people_api.controllers;

import com.study.people_api.entitiy.address.Address;
import com.study.people_api.entitiy.address.AddressDTO;
import com.study.people_api.entitiy.people.DetailPersonDTO;
import com.study.people_api.entitiy.people.People;
import com.study.people_api.entitiy.people.PeopleRegisterDTO;
import com.study.people_api.entitiy.users.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters

@TestPropertySource(locations = "classpath:application-test.properties")
class PeopleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository repository;

    @Autowired
    private JacksonTester<PeopleRegisterDTO> peopleRegisterDTOJacksonTester;

    @Autowired
    private JacksonTester<DetailPersonDTO> detailPersonDTOJacksonTester;


    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    public void cadastrar_cenario1()throws Exception{
        var response = mvc.perform(post("/v1/people")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 202 quando informacoes estao validas")
    @WithMockUser
    public void cadastrar_cenario2()throws Exception{
        var dadosCadastro = new PeopleRegisterDTO(
                "teste",
                new Date(1997-06-20),
                dadosEndereco(),
                "123456789",
                "12345678910"
        );

        when(repository.save(any())).thenReturn(new People(dadosCadastro));

        var response = mvc.perform(post("/v1/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(peopleRegisterDTOJacksonTester.write(
                        dadosCadastro
                ).getJson())
        ).andReturn().getResponse();

        var dadosDetail = new DetailPersonDTO(
                null,
                dadosCadastro.nome(),
                dadosCadastro.dataDeAniversario(),
                new Address(dadosCadastro.endereco()),
                dadosCadastro.telefone(),
                dadosCadastro.cpf()

        );

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        var jsonEsperado = detailPersonDTOJacksonTester.write(
                dadosDetail
        ).getJson();


        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }


    private AddressDTO dadosEndereco() {
        return new AddressDTO(
                "rua xpto",
                "840",
                "",
                "JD Olivia",
                "Ribeirao preto",
                "14285070",
                "SP"
        );
    }


}