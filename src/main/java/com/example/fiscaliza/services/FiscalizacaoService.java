package com.example.fiscaliza.services;

import com.example.fiscaliza.models.Fiscalizacao;
import com.example.fiscaliza.repository.FiscalizacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FiscalizacaoService {

    @Autowired
    private FiscalizacaoRepository repository;

    public Long save(Fiscalizacao fiscalizacao){
        repository.save(fiscalizacao);
        return fiscalizacao.getId();
    }

    public Optional<Fiscalizacao> findById(Long id){
        return repository.findById(id);
    }

    public Fiscalizacao findAtual(LocalDate data){

        return repository.findAtual(data);
    }

    public List<Fiscalizacao> findAll(){
        return repository.findAll();
    }

    public Fiscalizacao edit(Fiscalizacao fiscalizacao){
        repository.save(fiscalizacao);
        return fiscalizacao;
    }

    public void delete(Long id) throws RuntimeException {

        Fiscalizacao fiscalizacao = repository.findById(id)
                .orElseThrow(() ->
                    new EntityNotFoundException("Fiscalizacao não encontrada com o ID informado!"));

        repository.delete(fiscalizacao);

    }
}
