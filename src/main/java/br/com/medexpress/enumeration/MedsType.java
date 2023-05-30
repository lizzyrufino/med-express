package br.com.medexpress.enumeration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MedsType {
    COMPRIMIDO("COMPRIMIDO"),
    GOTAS("GOTAS"),
    CREME("CREME"),
    INJETAVEL("INJETÁVEL");

    private final String value;
}
