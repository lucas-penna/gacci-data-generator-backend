package com.gacci.gacci_data_generator.model;

import com.gacci.gacci_data_generator.model.enums.Escolaridade;
import com.gacci.gacci_data_generator.model.enums.EstadoCivil;
import com.gacci.gacci_data_generator.model.enums.TipoConstrucao;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.gacci.gacci_data_generator.model.enums.RemissaoEnum;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    @ToString.Exclude
    private Endereco endereco;
    private Date nascimento;
    private String telefone;
    private Double rendaMensal;
    private String rg;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private Escolaridade escolaridade;
    @ManyToOne
    @JoinColumn(name = "entrevistador_id")
    private Entrevistador entrevistador;
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Auxilio> auxilios;
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Saude saude;
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Habitacao habitacao;
    private Boolean ativo;
    private RemissaoEnum remissao;


    public Pessoa(long l, String ana, String number, Object o, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6) {
    }
}
