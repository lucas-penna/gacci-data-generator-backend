package com.gacci.gacci_data_generator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gacci.gacci_data_generator.model.enums.TipoConstrucao;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Habitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoConstrucao tipoConstrucao;

    private Double valor;

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    @JsonIgnore
    private Pessoa pessoa;

    public Habitacao(TipoConstrucao tipoConstrucao, Double valor) {
        this.tipoConstrucao = tipoConstrucao;
        this.valor = valor;
    }


}
