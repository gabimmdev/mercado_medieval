package com.mercado.medieval.service;

import com.mercado.medieval.model.Item;
import com.mercado.medieval.model.Raridade;
import com.mercado.medieval.model.TipoItem;
import com.mercado.medieval.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> listarTodos() {
        return itemRepository.findAll();
    }

    public Optional<Item> buscarPorId(Long id) {
        return itemRepository.findById(id);
    }

    public Item criar(Item item) {
        return itemRepository.save(item);
    }

    public Item atualizar(Long id, Item dadosAtualizados) {
        return itemRepository.findById(id).map(i -> {
            i.setNome(dadosAtualizados.getNome());
            i.setTipo(dadosAtualizados.getTipo());
            i.setRaridade(dadosAtualizados.getRaridade());
            i.setPreco(dadosAtualizados.getPreco());
            i.setDono(dadosAtualizados.getDono());
            return itemRepository.save(i);
        }).orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }

    public void deletar(Long id) {
        itemRepository.deleteById(id);
    }

    public List<Item> buscarPorNomeParcial(String nome) {
        return itemRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Item> buscarPorTipo(String tipo) {
        return itemRepository.findByTipo(TipoItem.valueOf(tipo.toUpperCase()));
    }

    public List<Item> buscarPorRaridade(String raridade) {
        return itemRepository.findByRaridade(Raridade.valueOf(raridade.toUpperCase()));
    }

    public List<Item> buscarPorPrecoEntre(double min, double max) {
        return itemRepository.findByPrecoBetween(min, max);
    }
}
