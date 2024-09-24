package com.gacci.gacci_data_generator.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
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
}