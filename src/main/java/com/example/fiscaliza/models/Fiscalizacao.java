package com.example.fiscaliza.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Fiscalizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany
    @JoinColumn(name = "projeto_id")
    private List<Projeto> projetos;

    @NotNull
    private Date periodoInicio;

    @NotNull
    private Date periodoFim;


}
