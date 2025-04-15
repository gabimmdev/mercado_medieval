package com.mercado.medieval.mercado_api.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.mercado.medieval.mercado_api.model.Classe;
import com.mercado.medieval.mercado_api.model.Personagem;
import com.mercado.medieval.mercado_api.repository.PersonagemRepository;

import jakarta.validation.Valid;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    @Autowired
    private PersonagemRepository repo;

    @GetMapping
    public List<Personagem> listar() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Personagem> criar(@RequestBody @Valid Personagem p) {
        Personagem novoPersonagem = repo.save(p);
        return new ResponseEntity<>(novoPersonagem, HttpStatus.CREATED);
    }

    @PutMapping("/1")
    public ResponseEntity<Personagem> atualizar(@PathVariable Long id, @RequestBody @Valid Personagem novo) {
        Personagem p = repo.findById(id).orElseThrow(() -> new PersonagemNotFoundException(id));
        p.setNome(novo.getNome());
        p.setClasse(novo.getClasse());
        p.setNivel(novo.getNivel());
        p.setMoedas(novo.getMoedas());
        return new ResponseEntity<>(repo.save(p), HttpStatus.OK);
    }

    @DeleteMapping("/1")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome")
    public List<Personagem> buscarPorNome(@RequestParam String nome) {
        return repo.findByNomeContainingIgnoreCase(nome);
    }

    @GetMapping("/classe")
    public List<Personagem> buscarPorClasse(@RequestParam("classe") Classe classe) {
        return repo.findByClasse(classe);
    }

    @ControllerAdvice
    public static class GlobalExceptionHandler {
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(PersonagemNotFoundException.class)
        public ResponseEntity<String> handlePersonagemNotFound(PersonagemNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public static class PersonagemNotFoundException extends RuntimeException {
        public PersonagemNotFoundException(Long id) {
            super("Personagem não encontrado com o ID: " + id);
        }
    }
}