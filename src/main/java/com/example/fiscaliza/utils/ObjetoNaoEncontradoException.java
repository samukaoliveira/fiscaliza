package com.example.fiscaliza.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ObjetoNaoEncontradoException extends RuntimeException {

    private final String redirectUrl;

    private final String flashMessage;

    public ObjetoNaoEncontradoException(String message,
                                        String redirectUrl) {
        super(message);
        this.redirectUrl = redirectUrl;
        this.flashMessage = "Objeto não encontrado!";
    }


}
