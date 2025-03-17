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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libreria.model.Autore;
import com.example.libreria.repository.AutoriRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/autori")
public class AutoriController {
    private final AutoriRepository autoriRepository;

    // recupera la lista di tutti gli autori
    @GetMapping
    public List<Autore> getAllAutori() {
        return autoriRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Autore>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(autoriRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Autore> createAutore(@RequestBody Autore autore) {
        Autore createAutore = autoriRepository.save(autore);
        URI location = URI.create(String.format("/libri/%d", createAutore.getId()));
        return ResponseEntity.created(location).body(createAutore);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autore> updateAutore(@PathVariable Long id, @RequestBody Autore autoreUpdate) {
        Optional<Autore> autoreOptional = autoriRepository.findById(id);
        if (!autoreOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Autore autore = autoreOptional.get();
        autore.setNome(autoreUpdate.getNome());
        autore.setCognome(autoreUpdate.getCognome());

        Autore updatedautore = autoriRepository.save(autore);

        return ResponseEntity.ok(updatedautore);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Autore> patchAutore(@PathVariable Long id, @RequestBody Autore autorePatch) {
        Optional<Autore> autoreOptional = autoriRepository.findById(id);
        if (!autoreOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Autore autore = autoreOptional.get();

        if (autorePatch.getNome() != null) {
            autore.setNome(autorePatch.getNome());
        }
        if (autorePatch.getCognome() != null) {
            autore.setCognome(autorePatch.getCognome());
        }

        Autore updatedAutore = autoriRepository.save(autore);

        return ResponseEntity.ok(updatedAutore);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutore(@PathVariable Long id) {
        Optional<Autore> autoreOptional = autoriRepository.findById(id);
        if (!autoreOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        autoriRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
