package com.example.fiscaliza.services;

import com.example.fiscaliza.utils.ObjetoNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Service
public class EntityValidator {

    public <T> T getOrTrhow(Optional<T> object, String url){
        return object.orElseThrow(
                () -> new ObjetoNaoEncontradoException("Objeto não encontrado", url)
        );
    }
}
