package com.gacci.gacci_data_generator.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Saude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    private boolean inss;
    private boolean funeral;
    private boolean outra;
    private String descricao;
}
