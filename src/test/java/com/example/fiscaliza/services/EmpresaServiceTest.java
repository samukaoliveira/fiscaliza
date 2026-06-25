package com.example.fiscaliza.services;

import com.example.fiscaliza.models.Empresa;
import com.example.fiscaliza.repository.EmpresaRepository;
import jakarta.transaction.Transactional;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class EmpresaServiceTest {

    @Autowired
    EmpresaService service;

    @Autowired
    EmpresaRepository repository;

    @BeforeEach
    public void setup(){
        repository.deleteAll();
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

        service.delete(empresaSalvaId);

        assertFalse(
                service.findById(empresaSalvaId).isPresent()
        );

    }

    @Test
    public void quando_deleta_empresa_com_id_invalido_entao_FALHA() throws Exception {
        Empresa novaEmpresa = new Empresa(null,"1234567890001-15", "Empresa X");
        val empresaSalvaId = service.save(novaEmpresa);

        assertEquals(empresaSalvaId, service.findById(empresaSalvaId).get().getId());
        assertThrows(Exception.class, () -> service.delete(255L));

    }

    @Test
    public void quando_busca_todas_as_empresas_cadastradas_entao_OK() throws Exception {
        Empresa empresaX = new Empresa(null,"1234567890001-14", "Empresa X");
        val empresaSalvaXId = service.save(empresaX);
        Empresa empresaY = new Empresa(null,"2034567450001-15", "Empresa Y");
        val empresaSalvaYId = service.save(empresaY);
        Empresa empresaZ = new Empresa(null,"3034566790001-16", "Empresa Z");
        val empresaSalvaZId = service.save(empresaZ);

        List<Empresa> lista = List.of(service.findById(empresaSalvaXId).get(),
                service.findById(empresaSalvaYId).get(),
                service.findById(empresaSalvaZId).get());

        assertEquals(lista, service.findAll());


    }

    @Test
    public void quando_edita_empresa_com_dados_validos_entao_OK() throws Exception {
        Empresa empresaNova = new Empresa(null,"1234567890001-14", "Empresa X");
        val empresaSalvaXId = service.save(empresaNova);

        Empresa empresaAlterada = service.findById(empresaSalvaXId).get();

        empresaAlterada.setNome("Agora é Empresa K!");

        service.edit(empresaAlterada);

        assertEquals("1234567890001-14", service.findById(empresaSalvaXId).get().getCnpj());
        assertEquals("Agora é Empresa K!", service.findById(empresaSalvaXId).get().getNome());


    }
}
