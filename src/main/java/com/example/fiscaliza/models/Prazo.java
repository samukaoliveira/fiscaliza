package com.example.fiscaliza.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prazos")
@Getter
@Setter
public class Prazo {

    public static final Long SINGLETON_ID = 1L;

    @Id
    private Long id;

    @Column(name = "entrega_sprint")
    private Integer entregaSprint;

    @Column(name = "homologacao")
    private Integer homologacao;

    @Column(name = "ocorrencia")
    private Integer ocorrencia;

    @Column(name = "termo_provisorio")
    private Integer termoProvisorio;

    @Column(name = "termo_definitivo")
    private Integer termoDefinitivo;

    @Column(name = "nf_provisoria")
    private Integer nfProvisoria;

    @Column(name = "nf_definitiva")
    private Integer nfDefinitiva;

    public Prazo() {
        this.id = SINGLETON_ID;
    }

}