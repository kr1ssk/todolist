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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TareaControllerTest {

    @Autowired
    private TareaController tareaController; // 100% alineado a tu TareaController real

    @MockBean
    private TareaService tareaService; // 100% alineado a tu TareaService real

    // ==========================================
    // TEST 1: CREAR TAREA (HTTP 201 CREATED)
    // ==========================================
    @Test
    public void test1_CrearTareaExitosamente() {
        // Arrange
        TareaDTO tareaInput = new TareaDTO(null, "Estudiar Spring Security", "Revisar la guía de JWT", false);
        TareaDTO tareaMocked = new TareaDTO(1, "Estudiar Spring Security", "Revisar la guía de JWT", false);
        
        // Mapeado exacto a tu método: tareaService.guardar(tareaDTO)
        Mockito.when(tareaService.guardar(Mockito.any(TareaDTO.class))).thenReturn(tareaMocked);

        // Act
        ResponseEntity<TareaDTO> respuesta = tareaController.crear(tareaInput);

        // Assert
        assertNotNull(respuesta);
        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
        assertEquals("Estudiar Spring Security", respuesta.getBody().getTitulo());
        assertEquals(1, respuesta.getBody().getId());
    }

    // ==========================================
    // TEST 2: LISTAR TODAS LAS TAREAS (HTTP 200 OK)
    // ==========================================
    @Test
    public void test2_ListarTodasLasTareas() {
        // Arrange
        TareaDTO t1 = new TareaDTO(1, "Comprar pan", "En el negocio de la esquina", false);
        TareaDTO t2 = new TareaDTO(2, "Hacer la tarea", "Asignatura de Java", true);
        List<TareaDTO> listaSimulada = Arrays.asList(t1, t2);

        // Mapeado exacto a tu método: tareaService.listarTodas()
        Mockito.when(tareaService.listarTodas()).thenReturn(listaSimulada);

        // Act
        List<TareaDTO> respuesta = tareaController.listar();

        // Assert
        assertNotNull(respuesta);
        assertEquals(2, respuesta.size());
        assertEquals("Comprar pan", respuesta.get(0).getTitulo());
    }

    // ==========================================
    // TEST 3: OBTENER TAREA EXISTENTE (HTTP 200 OK)
    // ==========================================
    @Test
    public void test3_ObtenerTareaPorIdExistente() {
        // Arrange
        Integer idBuscado = 5;
        TareaDTO tareaMocked = new TareaDTO(idBuscado, "Revisar Docker", "Verificar contenedores", false);

        // Mapeado exacto a tu método: tareaService.obtenerPorId(id)
        Mockito.when(tareaService.obtenerPorId(idBuscado)).thenReturn(tareaMocked);

        // Act
        ResponseEntity<TareaDTO> respuesta = tareaController.obtener(idBuscado);

        // Assert
        assertNotNull(respuesta);
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
        assertEquals("Revisar Docker", respuesta.getBody().getTitulo());
    }

    // ==========================================
    // TEST 4: OBTENER TAREA INEXISTENTE (HTTP 404 NOT FOUND)
    // ==========================================
    @Test
    public void test4_ObtenerTareaPorIdInexistente() {
        // Arrange
        Integer idInexistente = 99;

        // Si la tarea no existe, tu servicio retorna 'null'
        Mockito.when(tareaService.obtenerPorId(idInexistente)).thenReturn(null);

        // Act
        ResponseEntity<TareaDTO> respuesta = tareaController.obtener(idInexistente);

        // Assert
        assertNotNull(respuesta);
        assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());
    }
}