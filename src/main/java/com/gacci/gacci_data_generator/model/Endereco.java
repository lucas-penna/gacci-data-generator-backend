package com.gacci.gacci_data_generator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rua;
    private String cidade;
    private String estado;
    private String cep;
    private String numero;
    private String complemento;
    private String bairro;
     @OneToOne(mappedBy = "endereco")
     @JsonIgnore
    private Pessoa pessoa;

    public Endereco(String rua, String cidade, String estado, String cep, String numero, String bairro, String complemento) {
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
    }
}