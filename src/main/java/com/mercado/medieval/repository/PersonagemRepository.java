package com.mercado.medieval.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercado.medieval.model.Classe;
import com.mercado.medieval.model.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    List<Personagem> findByNomeContainingIgnoreCase(String nome);
    List<Personagem> findByClasse(Classe classe);
}
