package com.mercado.medieval.model;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Personagem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Enumerated(EnumType.STRING)
    private Classe classe;

    @Min(1)
    @Max(99)
    private int nivel;

    @Min(0)
    private double moedas;

    @OneToMany(mappedBy = "dono")
    private List<Item> itens;
}
