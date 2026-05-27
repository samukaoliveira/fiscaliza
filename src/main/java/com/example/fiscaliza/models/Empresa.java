package com.example.fiscaliza.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Empresa {

    @Id
    private Integer id;

    @NotNull
    private String cnpj;

    @NotNull
    private String nome;

}
