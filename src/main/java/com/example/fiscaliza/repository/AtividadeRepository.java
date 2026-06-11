package com.example.fiscaliza.repository;

import com.example.fiscaliza.models.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
}