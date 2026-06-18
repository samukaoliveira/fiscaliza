package com.example.fiscaliza.services;

import com.example.fiscaliza.models.Atividade;
import com.example.fiscaliza.models.Fiscalizacao;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class DashboardService {

    @Autowired
    AtividadeService atividadeService;

    @Autowired
    FiscalizacaoService fiscalizacaoService;

}
