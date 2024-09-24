package com.gacci.gacci_data_generator.model;

import com.gacci.gacci_data_generator.model.enums.Escolaridade;
import com.gacci.gacci_data_generator.model.enums.TipoAuxilio;
import com.gacci.gacci_data_generator.model.enums.EstadoCivil;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Auxilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @Enumerated(EnumType.STRING)
    private Escolaridade escolaridade;

    private Double rendaMensal;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @Enumerated(EnumType.STRING)
    private TipoAuxilio tipoAuxilio;

    private String descricaoOutros;
    private String problemaSaude;
    private String diagnostico;
    private String providencia;
    private Date data;

    @ManyToOne
    @JoinColumn(name = "entrevistador_id")
    private Entrevistador entrevistador;

    private String observacao;
}
