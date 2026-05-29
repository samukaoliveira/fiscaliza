package com.example.fiscaliza.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Historia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String descricao;

    @OneToMany
    @JoinColumn(name = "taksk_id")
    private List<Tasks> tasks;

    @OneToMany
    @JoinColumn(name = "historia_id")
    private List<Historia> relacionadas;
}
