package com.example.fiscaliza.services;

import com.example.fiscaliza.models.Atividade;
import com.example.fiscaliza.repository.AtividadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository repository;

    public Long save(Atividade atividade){
        repository.save(atividade);
        return atividade.getId();
    }

    public Optional<Atividade> findById(Long id){
        return repository.findById(id);
    }

    public Optional<Atividade> findByFiscalizacaoId(Long id){

        return repository.findById(id);
    }

    public List<Atividade> findAll(){
        return repository.findAll();
    }

    public Atividade edit(Atividade atividade){
        repository.save(atividade);
        return atividade;
    }

    public void delete(Long id) throws RuntimeException {

        Atividade atividade = repository.findById(id)
                .orElseThrow(() ->
                    new EntityNotFoundException("Atividade não encontrada com o ID informado!"));

        repository.delete(atividade);

    }
}
