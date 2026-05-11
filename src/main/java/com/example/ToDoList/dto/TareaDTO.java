package com.example.ToDoList.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TareaDTO {

    private Integer id;

    @NotBlank(message = "El título es obligatorio y no puede estar en blanco")
    private String titulo;

    private String descripcion;

    // Usamos @NotNull en booleanos para asegurar que el campo venga en el JSON
    @NotNull(message = "El estado de completada debe ser especificado (true/false)")
    private boolean completada;
}