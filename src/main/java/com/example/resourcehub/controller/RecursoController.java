package com.example.resourcehub.controller;

import com.example.resourcehub.model.Recurso;
import com.example.resourcehub.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.resourcehub.model.Recurso;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recursos")
public class RecursoController {

    @Autowired
    private RecursoService recursoService;

    // Consultamos todos los recursos
    @GetMapping
    public List<Recurso> listarTodos() {
        return recursoService.listarTodos();
    }

    // Buscamos un recurso mediante su ID
    @GetMapping("/{id}")
    public ResponseEntity<Recurso> buscarPorId(@PathVariable Long id) {

        return recursoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Creamos un recurso nuevo
    @PostMapping
    public Recurso crear(@RequestBody Recurso recurso) {
        return recursoService.crear(recurso);
    }

    // Actualizamos completamente un recurso existente
    @PutMapping("/{id}")
    public ResponseEntity<Recurso> actualizar(
            @PathVariable Long id,
            @RequestBody Recurso datos) {

        return recursoService.actualizar(id, datos)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizamos parcialmente un recurso existente
    @PatchMapping("/{id}")
    public ResponseEntity<Recurso> actualizarParcial(
            @PathVariable Long id,
            @RequestBody Recurso datos) {

        return recursoService.actualizarParcial(id, datos)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminamos un recurso mediante su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (recursoService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
