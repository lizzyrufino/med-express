package br.com.medexpress.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

import java.lang.reflect.Type;
import java.util.List;

public class JsonParser {
    private static final ObjectMapper MAPPER = new ObjectMapper().registerModule(new JavaTimeModule()); //ObjectMapper = biblioteca do java para fazer mapeamento de objetos usando json

    @SneakyThrows
    public static String objectToStringJson(Object value) { //esse metodo converte um objeto qualquer em uma string json
        return MAPPER.writeValueAsString(value);
    }

    @SneakyThrows
    public static <T> T stringJsonToObject(String json, Class<T> clazz) { //converte qualquer json em um objeto
        return MAPPER.readValue(json, clazz);
    }

    @SneakyThrows
    public static <T> List<T> stringJsonToList(String json, Class<T> clazz) { //transforma um objeto em lista
        return MAPPER.readValue(json, new TypeReference<>() {
            @Override
            public Type getType() {
                return MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);
            }
        });
    }
}
