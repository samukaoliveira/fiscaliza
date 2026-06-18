package com.example.fiscaliza.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Fiscalizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    @JoinColumn(name = "projeto_id")
    private List<Projeto> projetos;

    @ManyToMany(mappedBy = "fiscalizacoes")
    private Set<Atividade> atividades = new HashSet<>();

    @NotNull
    private Date periodoInicio;

    @NotNull
    private Date periodoFim;


}
