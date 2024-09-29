package com.gacci.gacci_data_generator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gacci.gacci_data_generator.model.enums.Escolaridade;
import com.gacci.gacci_data_generator.model.enums.TipoAuxilio;
import com.gacci.gacci_data_generator.model.enums.EstadoCivil;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Auxilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoAuxilio tipoAuxilio;

    private String descricaoOutros;
    private Date data;

    @ManyToOne
    @JoinColumn(name = "pessoa_id") // Mapeando a relação com Pessoa
    @JsonIgnore
    private Pessoa pessoa;

    public Auxilio(TipoAuxilio tipoAuxilio, String descricaoOutros, Date data) {
        this.tipoAuxilio = tipoAuxilio;
        this.descricaoOutros = descricaoOutros;
        this.data = data;
    }


}
