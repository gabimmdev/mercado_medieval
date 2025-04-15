package com.mercado.medieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.mercado.medieval.model.Classe;
import com.mercado.medieval.model.Personagem;
import com.mercado.medieval.repository.PersonagemRepository;

import jakarta.validation.Valid;

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
    public Personagem criar(@RequestBody @Valid Personagem p) {
        return repo.save(p);
    }

    @PutMapping("/{id}")
    public Personagem atualizar(@PathVariable Long id, @RequestBody @Valid Personagem novo) {
        Personagem p = repo.findById(id).orElseThrow();
        p.setNome(novo.getNome());
        p.setClasse(novo.getClasse());
        p.setNivel(novo.getNivel());
        p.setMoedas(novo.getMoedas());
        return repo.save(p);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @GetMapping("/buscar/nome")
    public List<Personagem> buscarPorNome(@RequestParam String nome) {
        return repo.findByNomeContainingIgnoreCase(nome);
    }

    @GetMapping("/buscar/classe")
    public List<Personagem> buscarPorClasse(@RequestParam Classe classe) {
        return repo.findByClasse(classe);
    }
}
