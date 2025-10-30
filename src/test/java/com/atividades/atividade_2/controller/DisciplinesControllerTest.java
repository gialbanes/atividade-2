package com.atividades.atividade_2.controller;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class DisciplinesControllerTest {
    private final DisciplinesController disciplinesController = new DisciplinesController();

    // Teste para retornar todas as disciplinas
    @Test
    void getAllDisciplines() {
        List<Map<String, String>> disciplines = disciplinesController.getAllDisciplines();
        assertEquals(2, disciplines.size());
        assertEquals("Integração e entrega contínua", disciplines.get(0).get("name"));
    }

    // Teste para retornar uma disciplina pelo id
    @Test
    void getDisciplineById() {
        Map<String, String> discipline = disciplinesController.getDisciplineById(1);
        // Verifica os atributos da disciplina retornada
        assertEquals("1", discipline.get("id"));
        assertEquals("Integração e entrega contínua", discipline.get("name"));
    }

    // Teste de pegar uma disciplina que não existe
    @Test
    void getDisciplineById_NotFound() {
        Map<String, String> discipline = disciplinesController.getDisciplineById(999);
        assertEquals(null, discipline);
    }

    // Teste para criar uma nova disciplina
    @Test
    void createDiscipline() {
        Map<String, String> novaDisciplina = Map.of("name", "Internet das coisas");

        Map<String, String> discipline = disciplinesController.createDiscipline(novaDisciplina);

        assertEquals("Internet das coisas", discipline.get("name"));
        assertEquals("3", discipline.get("id"));
    }
}
