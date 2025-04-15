package com.mercado.medieval.mercado_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercado.medieval.mercado_api.model.Classe;
import com.mercado.medieval.mercado_api.model.Personagem;
import com.mercado.medieval.mercado_api.repository.PersonagemRepository;

@Service
public class PersonagemService {
    @Autowired
    private PersonagemRepository personagemRepository;

    public List<Personagem> listarTodos() {
        return personagemRepository.findAll();
    }

    public Optional<Personagem> buscarPorId(Long id) {
        return personagemRepository.findById(id);
    }

    public Personagem criar(Personagem personagem) {
        return personagemRepository.save(personagem);
    }

    public Personagem atualizar(Long id, Personagem dadosAtualizados) {
        return personagemRepository.findById(id).map(p -> {
            p.setNome(dadosAtualizados.getNome());
            p.setClasse(dadosAtualizados.getClasse());
            p.setNivel(dadosAtualizados.getNivel());
            p.setMoedas(dadosAtualizados.getMoedas());
            return personagemRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
    }

    public void deletar(Long id) {
        personagemRepository.deleteById(id);
    }

    public List<Personagem> buscarPorNome(String nome) {
        return personagemRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Personagem> buscarPorClasse(String classe) {
        return personagemRepository.findByClasse(Classe.valueOf(classe.toUpperCase()));
    }
}
