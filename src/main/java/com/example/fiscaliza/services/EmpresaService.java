package com.example.fiscaliza.services;

import com.example.fiscaliza.models.Empresa;
import com.example.fiscaliza.repository.EmpresaRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository repository;

    public Long save(Empresa empresa){
        repository.save(empresa);
        return empresa.getId();
    }

    public Optional<Empresa> findById(Long id){
        return repository.findById(id);
    }

    public List<Empresa> findAll(){
        return repository.findAll();
    }

    public Empresa edit(Empresa empresa){
        repository.save(empresa);
        return empresa;
    }

    public void delete(Optional<Empresa> empresa) throws Exception{

        if(findById(empresa.get().getId()).isPresent()) {
            repository.delete(empresa.get());
        } else {
            throw new Exception("Empresa não encontrada com o ID informado!");
        }

    }
}
