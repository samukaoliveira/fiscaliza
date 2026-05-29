package com.example.fiscaliza.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Duration;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @NotNull
    private Date dataInicio;

    @NotNull
    private Duration duracao;


}
