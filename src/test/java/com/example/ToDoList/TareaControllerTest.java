package com.example.ToDoList.controller;

import com.example.ToDoList.dto.TaskDTO;
import com.example.ToDoList.service.TaskService;
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
    private TaskController taskController; // Cambiado a tu controlador real TaskController

    @MockBean
    private TaskService taskService; // Cambiado a tu servicio real TaskService

    @Test
    public void testCrearTarea() {
        // 1. Arrange (Preparación de la simulación con tus DTOs reales)
        TaskDTO taskInput = new TaskDTO(null, "Estudiar Spring Security", "Revisar la guía de JWT", false, null);
        TaskDTO taskMocked = new TaskDTO(1L, "Estudiar Spring Security", "Revisar la guía de JWT", false, null);
        
        // Mockito intercepta la llamada al servicio real
        Mockito.when(taskService.guardar(Mockito.any(TaskDTO.class))).thenReturn(taskMocked);

        // 2. Act (Ejecución del método real de tu controlador)
        ResponseEntity<TaskDTO> respuesta = taskController.crear(taskInput);

        // 3. Assert (Verificación)
        assertNotNull(respuesta);
        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
        assertEquals("Estudiar Spring Security", respuesta.getBody().getTitulo());
        assertEquals(1L, respuesta.getBody().getId());
    }
}