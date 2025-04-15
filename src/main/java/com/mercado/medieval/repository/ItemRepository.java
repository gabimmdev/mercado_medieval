package com.mercado.medieval.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercado.medieval.model.Item;
import com.mercado.medieval.model.Raridade;
import com.mercado.medieval.model.TipoItem;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByNomeContainingIgnoreCase(String nome);
    List<Item> findByTipo(TipoItem tipo);
    List<Item> findByRaridade(Raridade raridade);
    List<Item> findByPrecoBetween(double min, double max);
}
