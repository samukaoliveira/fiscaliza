package com.example.fiscaliza.services;

import com.example.fiscaliza.models.Sprint;
import com.example.fiscaliza.repository.SprintRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class SprintService {

    @Autowired
    private SprintRepository repository;

    public Long save(@Valid Sprint sprint){
        repository.save(sprint);
        return sprint.getId();
    }

    public Optional<Sprint> findById(Long id){
        return repository.findById(id);
    }

    public List<Sprint> findAll(){
        return repository.findAll();
    }

    public Sprint edit(Sprint sprint){
        repository.save(sprint);
        return sprint;
    }

    public void delete(Long id) throws RuntimeException {

        Sprint sprint = repository.findById(id)
                .orElseThrow(() ->
                    new EntityNotFoundException("Sprint não encontrada com o ID informado!"));

        repository.delete(sprint);

    }
}
