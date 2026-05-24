package com.example.ToDoList.controller;

import com.example.ToDoList.dto.WeatherDTO;
import com.example.ToDoList.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clima")
public class WeatherController {

    private static final Logger log = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private WeatherService weatherService;

    /**
     * Devuelve el clima actual para las coordenadas indicadas.
     * Por defecto usa Santiago de Chile (lat=-33.45, lon=-70.65).
     */
    @GetMapping
    public ResponseEntity<WeatherDTO> clima(
            @RequestParam(defaultValue = "-33.45") double lat,
            @RequestParam(defaultValue = "-70.65") double lon) {

        log.info("[WeatherController] -> Consultando clima externo para lat={}, lon={}", lat, lon);
        WeatherDTO resultado = weatherService.obtenerClima(lat, lon);
        return ResponseEntity.ok(resultado);
    }
}