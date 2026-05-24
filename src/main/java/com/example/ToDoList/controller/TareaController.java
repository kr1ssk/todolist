package com.example.ToDoList.controller;

import com.example.ToDoList.dto.TareaDTO;
import com.example.ToDoList.service.TareaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tareas")
@CrossOrigin(origins = "*")
public class TareaController {

    private static final Logger log = LoggerFactory.getLogger(TareaController.class);

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public List<TareaDTO> listar() {
        log.info("[TareaController] -> Petición GET para listar todas las tareas.");
        return tareaService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<TareaDTO> crear(@Valid @RequestBody TareaDTO tareaDTO) {
        log.info("[TareaController] -> Petición POST para crear una tarea: {}", tareaDTO.getTitulo());
        return new ResponseEntity<>(tareaService.guardar(tareaDTO), HttpStatus.CREATED);
    }

    @PostMapping("/muchas")
    public ResponseEntity<List<TareaDTO>> crearMuchas(@RequestBody List<TareaDTO> listaDTO) {
        log.info("[TareaController] -> Petición POST masiva para crear {} tareas.", listaDTO.size());
        return new ResponseEntity<>(tareaService.guardarMuchas(listaDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaDTO> obtener(@PathVariable Integer id) {
        log.info("[TareaController] -> Petición GET para buscar tarea con ID: {}", id);
        TareaDTO dto = tareaService.obtenerPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaDTO> editar(@PathVariable Integer id, @Valid @RequestBody TareaDTO tareaDTO) {
        log.info("[TareaController] -> Petición PUT para actualizar la tarea con ID: {}", id);
        TareaDTO actualizada = tareaService.actualizar(id, tareaDTO);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        log.warn("[TareaController] -> Petición DELETE para eliminar la tarea con ID: {}", id);
        tareaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // === NUEVOS ENDPOINTS (TOTAL EN EL PROYECTO CON ESTOS: 10) ===

    @GetMapping("/completadas")
    public List<TareaDTO> listarCompletadas() {
        log.info("[TareaController] -> Petición GET para listar tareas COMPLETADAS.");
        return tareaService.listarCompletadas();
    }

    @GetMapping("/pendientes")
    public List<TareaDTO> listarPendientes() {
        log.info("[TareaController] -> Petición GET para listar tareas PENDIENTES.");
        return tareaService.listarPendientes();
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminarTodas() {
        log.warn("[TareaController] -> ¡ADVERTENCIA! Petición DELETE masiva para eliminar TODAS las tareas.");
        tareaService.eliminarTodas();
        return ResponseEntity.noContent().build();
    }
}