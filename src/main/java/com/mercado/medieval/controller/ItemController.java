package com.mercado.medieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.mercado.medieval.model.Item;
import com.mercado.medieval.model.Raridade;
import com.mercado.medieval.model.TipoItem;
import com.mercado.medieval.repository.ItemRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemRepository repo;

    @GetMapping
    public List<Item> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Item criar(@RequestBody @Valid Item item) {
        return repo.save(item);
    }

    @PutMapping("/{id}")
    public Item atualizar(@PathVariable Long id, @RequestBody @Valid Item novo) {
        Item item = repo.findById(id).orElseThrow();
        item.setNome(novo.getNome());
        item.setTipo(novo.getTipo());
        item.setRaridade(novo.getRaridade());
        item.setPreco(novo.getPreco());
        item.setDono(novo.getDono());
        return repo.save(item);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @GetMapping("/buscar/nome")
    public List<Item> buscarPorNome(@RequestParam String nome) {
        return repo.findByNomeContainingIgnoreCase(nome);
    }

    @GetMapping("/buscar/tipo")
    public List<Item> buscarPorTipo(@RequestParam TipoItem tipo) {
        return repo.findByTipo(tipo);
    }

    @GetMapping("/buscar/raridade")
    public List<Item> buscarPorRaridade(@RequestParam Raridade raridade) {
        return repo.findByRaridade(raridade);
    }

    @GetMapping("/buscar/preco")
    public List<Item> buscarPorPrecoEntre(@RequestParam double min, @RequestParam double max) {
        return repo.findByPrecoBetween(min, max);
    }
}