package com.example.fiscaliza.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
