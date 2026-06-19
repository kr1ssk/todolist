package com.example.ToDoList.controller;

import com.example.ToDoList.dto.TareaDTO;
import com.example.ToDoList.service.TareaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TareaControllerTest {

    @Autowired
    private TareaController tareaController;

    @MockBean
    private TareaService tareaService;

    @Test
    public void testCrearTarea() {
        // 1. Arrange (Preparación de la simulación)
        TareaDTO tareaInput = new TareaDTO(null, "Estudiar Spring Security", "Revisar la guía de JWT", false);
        TareaDTO tareaMocked = new TareaDTO(1, "Estudiar Spring Security", "Revisar la guía de JWT", false);
        
        // Mockito intercepta la llamada al servicio y devuelve el objeto simulado sin ir a la BD
        Mockito.when(tareaService.guardar(Mockito.any(TareaDTO.class))).thenReturn(tareaMocked);

        // 2. Act (Ejecución del método del controlador)
        ResponseEntity<TareaDTO> respuesta = tareaController.crear(tareaInput);

        // 3. Assert (Verificación del resultado esperado)
        assertNotNull(respuesta);
        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
        assertEquals("Estudiar Spring Security", respuesta.getBody().getTitulo());
        assertEquals(1, respuesta.getBody().getId());
    }
}