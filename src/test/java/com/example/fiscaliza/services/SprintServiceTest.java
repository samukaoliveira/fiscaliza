package com.example.fiscaliza.services;

import com.example.fiscaliza.models.Sprint;
import com.example.fiscaliza.repository.SprintRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class SprintServiceTest {

    @Autowired
    SprintService service;

    @Autowired
    SprintRepository repository;

    @BeforeEach
    public void setup(){
        repository.deleteAll();
    }


    @Test
    public void quando_salva_sprint_com_dados_validos_entao_OK(){
        Sprint novaSprint = new Sprint(null,"Sprint #19", LocalDate.of(2026, 04, 10), 28);
        val sprintSalvaId = service.save(novaSprint);
        val sprintSalva = service.findById(sprintSalvaId).get();

        assertEquals("Sprint #19", sprintSalva.getDescricao());
        assertEquals(28, sprintSalva.getDuracao());

    }

    @Test
    public void quando_salva_sprint_com_dados_invalidos_entao_FALHA(){
        Sprint novaSprint = new Sprint(null,"Sprint #19", LocalDate.of(2026, 04, 10), null);

        assertThrows(RuntimeException.class, () -> service.save(novaSprint));

    }

    @Test
    public void quando_deleta_sprint_com_id_valido_entao_OK() throws Exception{
        Sprint novaSprint = new Sprint(null,"Sprint #19", LocalDate.of(2026, 04, 10), 28);
        val sprintSalvaId = service.save(novaSprint);

        service.delete(sprintSalvaId);

        assertFalse(
                service.findById(sprintSalvaId).isPresent()
        );

    }

    @Test
    public void quando_deleta_sprint_com_id_invalido_entao_FALHA() throws Exception {
        Sprint novaSprint = new Sprint(null,"Sprint #19", LocalDate.of(2026, 04, 10), 28);
        val sprintSalvaId = service.save(novaSprint);

        assertEquals(sprintSalvaId, service.findById(sprintSalvaId).get().getId());
        assertThrows(Exception.class, () -> service.delete(255L));

    }

    @Test
    public void quando_busca_todas_as_sprints_cadastradas_entao_OK() throws Exception {
        Sprint sprintX = new Sprint(null,"Sprint #19", LocalDate.of(2026, 4, 10), 28);
        val sprintSalvaXId = service.save(sprintX);
        Sprint sprintY = new Sprint(null,"Sprint #20", LocalDate.of(2026, 5, 8), 28);
        val sprintSalvaYId = service.save(sprintY);
        Sprint sprintZ = new Sprint(null,"Sprint #21", LocalDate.of(2026, 6, 5), 28);
        val sprintSalvaZId = service.save(sprintZ);

        List<Sprint> lista = List.of(service.findById(sprintSalvaXId).get(),
                service.findById(sprintSalvaYId).get(),
                service.findById(sprintSalvaZId).get());

        assertEquals(lista, service.findAll());


    }

    @Test
    public void quando_edita_sprint_com_dados_validos_entao_OK() throws Exception {
        Sprint sprintNova = new Sprint(null,"Sprint #19", LocalDate.of(2026, 04, 10), 28);
        val sprintSalvaXId = service.save(sprintNova);

        Sprint sprintAlterada = service.findById(sprintSalvaXId).get();

        sprintAlterada.setDescricao("Agora é Sprint #19e meio!");
        sprintAlterada.setDuracao(14);

        service.edit(sprintAlterada);

        assertEquals(LocalDate.of(2026, 04, 10), service.findById(sprintSalvaXId).get().getDataInicio());
        assertEquals("Agora é Sprint #19e meio!", service.findById(sprintSalvaXId).get().getDescricao());
        assertEquals(14, service.findById(sprintSalvaXId).get().getDuracao());


    }
}
