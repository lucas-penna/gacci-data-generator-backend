package com.gacci.gacci_data_generator.model;

import com.gacci.gacci_data_generator.model.enums.TipoConstrucao;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Habitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @Enumerated(EnumType.STRING)
    private TipoConstrucao tipoConstrucao;

    private Double valor;
}
