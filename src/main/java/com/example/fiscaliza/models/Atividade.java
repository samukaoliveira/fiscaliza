package com.example.fiscaliza.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String descricao;

    @NotNull
    private Integer prazo;

    @ManyToOne
    @JoinColumn(name = "dependencia_id")
    private Atividade dependencia;

    @ManyToMany
    @JoinTable(
            name = "fiscalizacao_atividade",
            joinColumns = @JoinColumn(name = "atividade_id"),
            inverseJoinColumns = @JoinColumn(name = "fiscalizacao_id")
    )
    private Set<Fiscalizacao>fiscalizacoes = new HashSet<>();

    public String getDescDependencia(){
        if(this.dependencia == null){
            return "Sem dependências";
        }
        return this.dependencia.getDescricao();
    }

    public Long getDependenciaId(){
        if(this.dependencia == null){
            return null;
        }
        return this.dependencia.getId();
    }
}
