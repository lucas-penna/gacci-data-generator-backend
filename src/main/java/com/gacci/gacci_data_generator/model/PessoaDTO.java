package com.gacci.gacci_data_generator.model;

import com.gacci.gacci_data_generator.model.enums.Escolaridade;
import com.gacci.gacci_data_generator.model.enums.EstadoCivil;
import com.gacci.gacci_data_generator.model.enums.TipoConstrucao;
import jakarta.persistence.*;
import lombok.Data;
import com.gacci.gacci_data_generator.model.enums.RemissaoEnum;

import java.util.Date;
import java.util.List;

@Data
public class PessoaDTO {

    private String nome;
    private Endereco endereco;
    private Date nascimento;
    private String telefone;
    private Double valorFinanciamento;
    private Double valorAluguel;
    private Double rendaMensal;
    private String rg;
    private String cpf;
    private String outroAuxilio;
    private Escolaridade escolaridade;
    private Entrevistador entrevistador;
    private EstadoCivil estadoCivil;
    private TipoConstrucao tipoConstrucao;
    private List<Auxilio> auxilios;
    private Saude saude;
    private Habitacao habitacao;
    private Boolean ativo;
    private RemissaoEnum remissao;
}
