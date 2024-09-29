package com.gacci.gacci_data_generator.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Anexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeArquivo;
    private String caminhoS3;
    private Long pessoaId;

    public Anexo(String nomeArquivo, String caminhoS3, Long pessoa) {
        this.nomeArquivo = nomeArquivo;
        this.caminhoS3 = caminhoS3;
        this.pessoaId = pessoa;
    }

    public Anexo() {}
}