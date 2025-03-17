package com.example.libreria.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libreria.model.Libro;

import com.example.libreria.repository.LibriRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/libri")
public class LibriController {

    private final LibriRepository libriRepository;

    // riceve la lista di libri
    @GetMapping
    public List<Libro> getAllLibri() {
        return libriRepository.findAll();
    }

    // Ricerca tramite id
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Libro>> getLibroById(@PathVariable Long id) {
        return ResponseEntity.ok(libriRepository.findById(id));
    }

    // per postare un nuovo libro
    @PostMapping
    public ResponseEntity<Libro> createLibro(@RequestBody Libro libro) {
        Libro createLibro = libriRepository.save(libro);
        URI location = URI.create(String.format("/libri/%d", createLibro.getId()));
        return ResponseEntity.created(location).body(createLibro);
    }

    // modificare un libro
    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libroUpdate) {
        Optional<Libro> libroOptional = libriRepository.findById(id);
        if (!libroOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Libro libro = libroOptional.get();
        libro.setTitolo(libroUpdate.getTitolo());
        libro.setAnnoPubblicazione(libroUpdate.getAnnoPubblicazione());

        Libro updatedLibro = libriRepository.save(libro);

        return ResponseEntity.ok(updatedLibro);
    }

    // modificare una parte del libro
    @PatchMapping("/{id}")
    public ResponseEntity<Libro> patchLibro(@PathVariable Long id, @RequestBody Libro libroPatch) {
        Optional<Libro> libroOptional = libriRepository.findById(id);
        if (!libroOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Libro libro = libroOptional.get();

        if (libroPatch.getTitolo() != null) {
            libro.setTitolo(libroPatch.getTitolo());
        }
        if (libroPatch.getAnnoPubblicazione() != null) {
            libro.setAnnoPubblicazione(libroPatch.getAnnoPubblicazione());
        }

        Libro updatedLibro = libriRepository.save(libro);

        return ResponseEntity.ok(updatedLibro);
    }

    // eliminare un libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        Optional<Libro> libroOptional = libriRepository.findById(id);
        if (!libroOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        libriRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
