package com.example.fiscaliza.services;

import com.example.fiscaliza.models.Atividade;
import com.example.fiscaliza.repository.AtividadeRepository;
import jakarta.transaction.Transactional;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class AtividadeServiceTest {

    @Autowired
    AtividadeService service;

    @Autowired
    AtividadeRepository repository;

    @BeforeEach
    public void setup(){
        repository.deleteAll();
    }


    @Test
    public void quando_salva_atividade_com_dados_validos_entao_OK(){
        Atividade novaAtividade = new Atividade(null,"Atividade X", 15, null, null);
        val atividadeSalvaId = service.save(novaAtividade);
        val atividadeSalva = service.findById(atividadeSalvaId).get();

        assertEquals("Atividade X", atividadeSalva.getDescricao());
        assertEquals(15, atividadeSalva.getPrazo());

    }

    @Test
    public void quando_salva_atividade_com_dados_invalidos_entao_FALHA(){
        Atividade novaAtividade = new Atividade(35L,"Atividade X", 15, null, null);

        assertThrows(ObjectOptimisticLockingFailureException.class, () -> service.save(novaAtividade));

    }

    @Test
    public void quando_deleta_atividade_com_id_valido_entao_OK() throws Exception{
        Atividade novaAtividade = new Atividade(null,"Atividade X", 15, null, null);
        val atividadeSalvaId = service.save(novaAtividade);

        service.delete(atividadeSalvaId);

        assertFalse(
                service.findById(atividadeSalvaId).isPresent()
        );

    }

    @Test
    public void quando_deleta_atividade_com_id_invalido_entao_FALHA() throws Exception {
        Atividade novaAtividade = new Atividade(null,"Atividade X", 15, null, null);
        val atividadeSalvaId = service.save(novaAtividade);

        assertEquals(atividadeSalvaId, service.findById(atividadeSalvaId).get().getId());
        assertThrows(Exception.class, () -> service.delete(255L));

    }

    @Test
    public void quando_busca_todas_as_atividades_cadastradas_entao_OK() throws Exception {
        Atividade atividadeX = new Atividade(null,"Atividade X", 10, null, null);
        val atividadeSalvaXId = service.save(atividadeX);
        Atividade atividadeY = new Atividade(null,"Atividade Y", 15, null, null);
        val atividadeSalvaYId = service.save(atividadeY);
        Atividade atividadeZ = new Atividade(null,"Atividade Z", 20, null, null);
        val atividadeSalvaZId = service.save(atividadeZ);

        List<Atividade> lista = List.of(service.findById(atividadeSalvaXId).get(),
                service.findById(atividadeSalvaYId).get(),
                service.findById(atividadeSalvaZId).get());

        assertEquals(lista, service.findAll());


    }

    @Test
    public void quando_edita_atividade_com_dados_validos_entao_OK() throws Exception {
        Atividade atividadeNova = new Atividade(null,"Atividade X", 15, null, null);
        val atividadeSalvaXId = service.save(atividadeNova);

        Atividade atividadeAlterada = service.findById(atividadeSalvaXId).get();

        atividadeAlterada.setDescricao("Agora é Atividade K!");

        service.edit(atividadeAlterada);

        assertEquals(15, service.findById(atividadeSalvaXId).get().getPrazo());
        assertEquals("Agora é Atividade K!", service.findById(atividadeSalvaXId).get().getDescricao());


    }
}
