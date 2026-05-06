package com.example.ToDoList.service;

import com.example.ToDoList.dto.TareaDTO;
import com.example.ToDoList.model.Tarea;
import com.example.ToDoList.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    private TareaDTO pasarADTO(Tarea tarea) {
        return new TareaDTO(tarea.getId(), tarea.getTitulo(), tarea.getDescripcion(), tarea.isCompletada());
    }

    private Tarea pasarAEntidad(TareaDTO dto) {
        Tarea tarea = new Tarea();
        tarea.setId(dto.getId());
        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setCompletada(dto.isCompletada());
        return tarea;
    }


    public List<TareaDTO> listarTodas() {
        return tareaRepository.findAll().stream()
                .map(this::pasarADTO)
                .collect(Collectors.toList());
    }

    public TareaDTO guardar(TareaDTO tareaDTO) {
        Tarea tarea = pasarAEntidad(tareaDTO);
        return pasarADTO(tareaRepository.save(tarea));
    }
    public List<TareaDTO> guardarMuchas(List<TareaDTO> listaDTO) {
        return listaDTO.stream()
                .map(this::guardar)
                .collect(Collectors.toList());
    }

    public TareaDTO obtenerPorId(Integer id) {
        return tareaRepository.findById(id)
                .map(this::pasarADTO)
                .orElse(null);
    }

    public TareaDTO actualizar(Integer id, TareaDTO dto) {
        return tareaRepository.findById(id).map(tarea -> {
            tarea.setTitulo(dto.getTitulo());
            tarea.setDescripcion(dto.getDescripcion());
            tarea.setCompletada(dto.isCompletada());
            return pasarADTO(tareaRepository.save(tarea));
        }).orElse(null);
    }

    public void eliminar(Integer id) {
        tareaRepository.deleteById(id);
    }
}