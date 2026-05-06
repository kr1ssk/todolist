package com.example.ToDoList.controller;

import com.example.ToDoList.dto.TareaDTO;
import com.example.ToDoList.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tareas")
@CrossOrigin(origins = "*")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public List<TareaDTO> listar() {
        return tareaService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<TareaDTO> crear(@Valid @RequestBody TareaDTO tareaDTO) {
        return new ResponseEntity<>(tareaService.guardar(tareaDTO), HttpStatus.CREATED);
    }

    @PostMapping("/muchas")
    public ResponseEntity<List<TareaDTO>> crearMuchas(@RequestBody List<TareaDTO> listaDTO) {
        return new ResponseEntity<>(tareaService.guardarMuchas(listaDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaDTO> obtener(@PathVariable Integer id) {
        TareaDTO dto = tareaService.obtenerPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaDTO> editar(@PathVariable Integer id, @Valid @RequestBody TareaDTO tareaDTO) {
        TareaDTO actualizada = tareaService.actualizar(id, tareaDTO);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tareaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}