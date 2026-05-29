package com.example.fiscaliza.services;

import com.example.fiscaliza.models.Empresa;
import com.example.fiscaliza.repository.EmpresaRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class empresaServiceTest {

    @Autowired
    EmpresaService service;

    @BeforeEach
    public void setup(){

    }


    @Test
    public void quando_salva_empresa_com_dados_validos_entao_OK(){
        Empresa novaEmpresa = new Empresa(null,"1234567890001-15", "Empresa X");
        val empresaSalvaId = service.save(novaEmpresa);
        val empresaSalva = service.findById(empresaSalvaId).get();

        assertEquals("1234567890001-15", empresaSalva.getCnpj());

    }

    @Test
    public void quando_salva_empresa_com_dados_invalidos_entao_FALHA(){
        Empresa novaEmpresa = new Empresa(35L,"1234567890001-15", "Empresa X");

        assertThrows(ObjectOptimisticLockingFailureException.class, () -> service.save(novaEmpresa));

    }

    @Test
    public void quando_deleta_empresa_com_id_valido_entao_OK() throws Exception{
        Empresa novaEmpresa = new Empresa(null,"1234567890001-15", "Empresa X");
        val empresaSalvaId = service.save(novaEmpresa);

        service.delete(Optional.of(service.findById(empresaSalvaId).get()));

        assertFalse(
                service.findById(empresaSalvaId).isPresent()
        );

    }

    @Test
    public void quando_deleta_empresa_com_id_invalido_entao_FALHA() throws Exception {
        Empresa novaEmpresa = new Empresa(null,"1234567890001-15", "Empresa X");
        val empresaSalvaId = service.save(novaEmpresa);

        assertThrows(Exception.class, () -> service.delete(Optional.of(service.findById(23L).get())));

    }
}
