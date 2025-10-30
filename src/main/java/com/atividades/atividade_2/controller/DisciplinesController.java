package com.atividades.atividade_2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disciplines")
public class DisciplinesController {

    private final Map<Integer, Map<String,String>> disciplinesBD = new HashMap<>();

    public DisciplinesController(){
        disciplinesBD.put(1, Map.of("id", "1", "name", "Integração e entrega contínua"));
        disciplinesBD.put(2, Map.of("id", "2", "name", "Laboratório de desenvolvimento web"));
    }

    // Lista todas as disciplinas
    @GetMapping
    public List<Map<String, String>> getAllDisciplines() {
        return new ArrayList<>(disciplinesBD.values());
    }

    // Lista a disciplina pelo id
    @GetMapping("/{id}")
    public Map<String, String> getDisciplineById(@PathVariable int id){
        return disciplinesBD.get(id);
    }

    // Cria uma nova disciplina
    @PostMapping
    public Map<String, String> createDiscipline(@RequestBody Map<String, String> discipline) {
        Map<String, String> newDiscipline = new HashMap<>();

        newDiscipline.put("id", String.valueOf(disciplinesBD.size() + 1));
        newDiscipline.put("name", discipline.get("name"));

        disciplinesBD.put(disciplinesBD.size() + 1, newDiscipline);
        return newDiscipline;
    }
    
}
