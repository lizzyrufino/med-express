package br.com.medexpress.enumeration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TagType {
    PRETA("PRETA"),
    VERMELHA("VERMELHA"),
    AMARELA("AMARELA"),
    SEM_TARJA("SEM TARJA");

    //final torna a propriedade obrigatória
    private final String value;
}
