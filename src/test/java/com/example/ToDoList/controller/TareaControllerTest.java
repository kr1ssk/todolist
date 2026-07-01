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
    private TareaController tareaController;

    @MockBean
    private TareaService tareaService;

    @Test
    public void test1_CrearTareaExitosamente() {
        TareaDTO tareaInput = new TareaDTO(null, "Estudiar Spring Security", "Revisar la guía de JWT", false);
        TareaDTO tareaMocked = new TareaDTO(1, "Estudiar Spring Security", "Revisar la guía de JWT", false);

        Mockito.when(tareaService.guardar(Mockito.any(TareaDTO.class))).thenReturn(tareaMocked);

        ResponseEntity<TareaDTO> respuesta = tareaController.crear(tareaInput);

        assertNotNull(respuesta);
        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
        assertEquals("Estudiar Spring Security", respuesta.getBody().getTitulo());
        assertEquals(1, respuesta.getBody().getId());
    }

    @Test
    public void test2_ListarTodasLasTareas() {
        TareaDTO t1 = new TareaDTO(1, "Comprar pan", "En el negocio de la esquina", false);
        TareaDTO t2 = new TareaDTO(2, "Hacer la tarea", "Asignatura de Java", true);
        List<TareaDTO> listaSimulada = Arrays.asList(t1, t2);

        Mockito.when(tareaService.listarTodas()).thenReturn(listaSimulada);

        List<TareaDTO> respuesta = tareaController.listar();

        assertNotNull(respuesta);
        assertEquals(2, respuesta.size());
        assertEquals("Comprar pan", respuesta.get(0).getTitulo());
    }

    @Test
    public void test3_ObtenerTareaPorIdExistente() {
        Integer idBuscado = 5;
        TareaDTO tareaMocked = new TareaDTO(idBuscado, "Revisar Docker", "Verificar contenedores", false);

        Mockito.when(tareaService.obtenerPorId(idBuscado)).thenReturn(tareaMocked);

        ResponseEntity<TareaDTO> respuesta = tareaController.obtener(idBuscado);

        assertNotNull(respuesta);
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
        assertEquals("Revisar Docker", respuesta.getBody().getTitulo());
    }

    @Test
    public void test4_ObtenerTareaPorIdInexistente() {
        Integer idInexistente = 99;

        Mockito.when(tareaService.obtenerPorId(idInexistente)).thenReturn(null);

        ResponseEntity<TareaDTO> respuesta = tareaController.obtener(idInexistente);

        assertNotNull(respuesta);
        assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());
    }
}