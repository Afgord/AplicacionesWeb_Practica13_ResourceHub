package com.example.resourcehub.service;

import com.example.resourcehub.model.Recurso;
import com.example.resourcehub.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

    // Consultamos todos los recursos
    public List<Recurso> listarTodos() {
        return recursoRepository.findAll();
    }

    // Buscamos un recurso por su ID
    public Optional<Recurso> buscarPorId(Long id) {
        return recursoRepository.findById(id);
    }

    // Creamos un recurso nuevo
    public Recurso crear(Recurso recurso) {
        recurso.setId(null);
        return recursoRepository.save(recurso);
    }

    // Eliminamos un recurso identificado por su ID
    public boolean eliminar(Long id) {
        return recursoRepository.deleteById(id);
    }

    // Actualizamos completamente un recurso existente.  Lo identificamos
    // por su id y le enviamos los nuevos datos
    public Optional<Recurso> actualizar(Long id, Recurso datos) {

        Optional<Recurso> recursoExistente = recursoRepository.findById(id);

        if (recursoExistente.isPresent()) {
            Recurso recurso = recursoExistente.get();

            recurso.setTitulo(datos.getTitulo());
            recurso.setUrl(datos.getUrl());
            recurso.setTipo(datos.getTipo());
            recurso.setCategoria(datos.getCategoria());

            return Optional.of(recursoRepository.save(recurso));
        }

        return Optional.empty();
    }

    // Actualizamos parcialmente los atributos de un recurso
    // existente, identificado por su ID.
    public Optional<Recurso> actualizarParcial(Long id, Recurso datos) {

        Optional<Recurso> recursoExistente = recursoRepository.findById(id);

        if (recursoExistente.isPresent()) {
            Recurso recurso = recursoExistente.get();

            if (datos.getTitulo() != null) {
                recurso.setTitulo(datos.getTitulo());
            }

            if (datos.getUrl() != null) {
                recurso.setUrl(datos.getUrl());
            }

            if (datos.getTipo() != null) {
                recurso.setTipo(datos.getTipo());
            }

            if (datos.getCategoria() != null) {
                recurso.setCategoria(datos.getCategoria());
            }

            return Optional.of(recursoRepository.save(recurso));
        }

        return Optional.empty();
    }

}
