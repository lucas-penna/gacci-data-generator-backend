package com.gacci.gacci_data_generator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Saude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean inss;
    private boolean funeral;
    private boolean outra;
    private String descricao;
    private String diagnostico;   // Tipo de câncer (ou outra condição médica)
    private String providencia;   // Tratamento sugerido
    @OneToOne
    @JoinColumn(name = "pessoa_id")
    @JsonIgnore
    private Pessoa pessoa;

    public Saude(boolean inss, boolean funeral, boolean outra, String descricao, String diagnostico, String providencia) {
        this.inss = inss;
        this.funeral = funeral;
        this.outra = outra;
        this.descricao = descricao;
        this.diagnostico = diagnostico;
        this.providencia = providencia;
    }
}
