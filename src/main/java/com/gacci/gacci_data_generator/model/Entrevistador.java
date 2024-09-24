package com.gacci.gacci_data_generator.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Entrevistador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    private boolean ativo;

}
