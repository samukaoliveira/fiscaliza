package com.example.fiscaliza.repository;

import com.example.fiscaliza.models.Empresa;
import com.example.fiscaliza.models.Fiscalizacao;
import com.example.fiscaliza.services.FiscalizacaoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;

@Repository
public interface FiscalizacaoRepository extends JpaRepository<Fiscalizacao, Long> {

    @Query("""
        SELECT f FROM Fiscalizacao f 
        WHERE f.periodoInicio < :data 
        AND f.periodoFim > :data""")
    Fiscalizacao findAtual(@Param("data") LocalDate data);

}
