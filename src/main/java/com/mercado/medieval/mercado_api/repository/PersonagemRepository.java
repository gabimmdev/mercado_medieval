package com.mercado.medieval.mercado_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercado.medieval.mercado_api.model.Classe;
import com.mercado.medieval.mercado_api.model.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    List<Personagem> findByNomeContainingIgnoreCase(String nome);
    List<Personagem> findByClasse(Classe classe);
}
