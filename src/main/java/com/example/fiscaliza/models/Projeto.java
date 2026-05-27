package com.example.fiscaliza.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany
    @JoinColumn(name = "sprint_id")
    private List<Sprint> sprints;

    @OneToMany
    @JoinColumn(name = "historia_id")
    private List<Historia> backlog;

    @ManyToOne
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;
}
