package com.atividades.atividade_2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(DisciplinesController.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DisciplinesControllerIT {

    @Autowired
    private MockMvc mockMvc;

    // Testa retorno de lista com todoas as disciplinas
    @Test
    void testGetAllDisciplines() throws Exception{
        mockMvc.perform(get("/disciplines"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].name").value("Integração e entrega contínua"))
            .andExpect(jsonPath("$[1].name").value("Laboratório de desenvolvimento web"));
    }

    // Teste para retornar uma disciplina pelo id
    @Test
    void testGetDisciplineById() throws Exception{
        mockMvc.perform(get("/disciplines/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.name").value("Integração e entrega contínua"));
    }

    // Teste de pegar uma disciplina que não existe pelo id 
    @Test
    void testGetDisciplineById_NotFound() throws Exception{
        mockMvc.perform(get("/disciplines/999"))
            .andExpect(status().isOk())
            .andExpect(content().string(""));
    }

    // Teste para criar uma nova disciplina
    @Test
    void testCreateDiscipline() throws Exception{
        String newDiscipline = """
                {
                    "name": "Internet das coisas"
                }
                """;
        mockMvc.perform(post("/disciplines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newDiscipline))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("3"))
            .andExpect(jsonPath("$.name").value("Internet das coisas"));
    }
    
}
