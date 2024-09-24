package com.gacci.gacci_data_generator.model;

import com.gacci.gacci_data_generator.model.enums.EstadoCivil;
import com.gacci.gacci_data_generator.model.enums.Parentesco;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ComposicaoFamiliar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    private int sequencia;
    private String nome;

    @Enumerated(EnumType.STRING)
    private Parentesco parentesco;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    private String profissao;
    private Double rendaMensal;
}
