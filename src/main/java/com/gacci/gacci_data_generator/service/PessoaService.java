package com.gacci.gacci_data_generator.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.gacci.gacci_data_generator.model.*;
import com.gacci.gacci_data_generator.model.enums.TipoAuxilio;
import com.gacci.gacci_data_generator.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private AnexoRepository anexoRepository;

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private AuxilioRepository auxilioRepository;

    @Autowired
    private SaudeRepository saudeRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private HabitacaoRepository habitacaoRepository;

    @Autowired
    private S3Service s3Service;


    @Transactional
    public Pessoa save(PessoaDTO pessoa) {

        Pessoa pessoaToSave = new Pessoa();
        try {
            Endereco endereco = new Endereco(pessoa.getEndereco().getRua(), pessoa.getEndereco().getCidade(), pessoa.getEndereco().getEstado(), pessoa.getEndereco().getCep(), pessoa.getEndereco().getNumero(), pessoa.getEndereco().getBairro(), pessoa.getEndereco().getComplemento());

            endereco.setPessoa(pessoaToSave);
            pessoaToSave.setEndereco(endereco);
            Saude saude = new Saude(pessoa.getSaude().isInss(), pessoa.getSaude().isFuneral(), pessoa.getSaude().isOutra(), pessoa.getSaude().getDescricao(), pessoa.getSaude().getDiagnostico(), pessoa.getSaude().getProvidencia());

            saude.setPessoa(pessoaToSave);
            pessoaToSave.setSaude(saude);


            List<Auxilio> list = pessoa.getAuxilios();
            pessoaToSave.setAuxilios(new ArrayList<Auxilio>());

            for (Auxilio auxilio : list) {
                Auxilio aux = new Auxilio(auxilio.getTipoAuxilio(), auxilio.getDescricaoOutros(), new Date());


                aux.setPessoa(pessoaToSave);
                pessoaToSave.getAuxilios().add(aux);
            }

            double valorAluguelFinanciamento = 0.0;
            if (pessoa.getValorFinanciamento() != null) {
                valorAluguelFinanciamento = pessoa.getValorFinanciamento();
            } else if (pessoa.getValorAluguel() != null) {
                valorAluguelFinanciamento = pessoa.getValorAluguel();
            }

            Habitacao habitacao = new Habitacao(pessoa.getTipoConstrucao(), valorAluguelFinanciamento);

            habitacao.setPessoa(pessoaToSave);
            pessoaToSave.setHabitacao(habitacao);
            pessoaToSave.setNome(pessoa.getNome());
            pessoaToSave.setNascimento(pessoa.getNascimento());
            pessoaToSave.setTelefone(pessoa.getTelefone());
            pessoaToSave.setRendaMensal(pessoa.getRendaMensal());
            pessoaToSave.setRg(pessoa.getRg());
            pessoaToSave.setCpf(pessoa.getCpf());
            pessoaToSave.setEscolaridade(pessoa.getEscolaridade());
            pessoaToSave.setEstadoCivil(pessoa.getEstadoCivil());

            repository.save(pessoaToSave);
        } catch (Exception e) {
            String text = "";
        }
        return pessoaToSave;
    }

    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    public List<Pessoa> findByFilter(String filter) {
        return repository.findByNomeContainingIgnoreCaseOrCpfContainingIgnoreCase(filter, filter);
    }

    public List<Anexo> getAnexos ( String id) {
        List<Anexo> list = new ArrayList<>();
        list = anexoRepository.findAllByPatient(Long.valueOf(id));
        return list;
    }

    public Anexo getAnexoById(Long id) {
        Optional<Anexo> anexo = anexoRepository.findById(id);
        return anexo.get();
    }

    public String getDetails(String id) {
        String response = "";
        Optional<Pessoa> pessoa = repository.findById(Long.valueOf(id));
        if (pessoa.isPresent()) {
            response = montaDetalhes(pessoa.get());
        } else {
            response = "Dados não encontrados";
        }
        return response;
    }

    private String montaDetalhes(Pessoa pessoa) {
        StringBuilder detalhes = new StringBuilder();

        detalhes.append("<strong>Nome:</strong> ").append(pessoa.getNome()).append("<br>");

        if (pessoa.getEndereco() != null) {
            Endereco endereco = pessoa.getEndereco();
            detalhes.append("<strong>Endereço:</strong> ")
                    .append(endereco.getRua()).append(", ")
                    .append(endereco.getNumero()).append(" - ")
                    .append(endereco.getBairro()).append(" - ")
                    .append(endereco.getCidade()).append(" - ")
                    .append(endereco.getEstado()).append(" - ")
                    .append(endereco.getCep()).append("<br>");
        }

        detalhes.append("<strong>Data de Nascimento:</strong> ").append(pessoa.getNascimento()).append("<br>");
        detalhes.append("<strong>Telefone:</strong> ").append(pessoa.getTelefone()).append("<br>");
        detalhes.append("<strong>Renda Mensal:</strong> ").append(pessoa.getRendaMensal()).append("<br>");
        detalhes.append("<strong>RG:</strong> ").append(pessoa.getRg()).append("<br>");
        detalhes.append("<strong>CPF:</strong> ").append(pessoa.getCpf()).append("<br>");
        detalhes.append("<strong>Escolaridade:</strong> ").append(pessoa.getEscolaridade()).append("<br>");
        detalhes.append("<strong>Estado Civil:</strong> ").append(pessoa.getEstadoCivil()).append("<br>");

        if (pessoa.getSaude() != null) {
            Saude saude = pessoa.getSaude();
            detalhes.append("<strong>Informações de Saúde:</strong><br>");

            if (saude.isInss()) {
                detalhes.append(" - <strong>Possui auxílio INSS</strong><br>");
            }

            if (saude.isFuneral()) {
                detalhes.append(" - <strong>Possui auxílio funeral</strong><br>");
            }

            if (saude.isOutra()) {
                detalhes.append(" - <strong>Possui outro tipo de auxílio</strong><br>");
            }

            if (saude.getDescricao() != null && !saude.getDescricao().isEmpty()) {
                detalhes.append(" - <strong>Descrição:</strong> ").append(saude.getDescricao()).append("<br>");
            }

            if (saude.getDiagnostico() != null && !saude.getDiagnostico().isEmpty()) {
                detalhes.append(" - <strong>Diagnóstico:</strong> ").append(saude.getDiagnostico()).append("<br>");
            }

            if (saude.getProvidencia() != null && !saude.getProvidencia().isEmpty()) {
                detalhes.append(" - <strong>Providência/Terapia:</strong> ").append(saude.getProvidencia()).append("<br>");
            }
        }

        if (pessoa.getHabitacao() != null) {
            Habitacao habitacao = pessoa.getHabitacao();
            detalhes.append("<strong>Informações da Habitação:</strong><br>");

            if (habitacao.getTipoConstrucao() != null) {
                detalhes.append(" - <strong>Tipo de Habitação:</strong> ").append(habitacao.getTipoConstrucao()).append("<br>");
            }

            if (habitacao.getValor() != null && habitacao.getValor() > 0) {
                detalhes.append(" - <strong>Valor:</strong> ").append(habitacao.getValor()).append("<br>");
            }
        }

        if (pessoa.getAuxilios() != null && !pessoa.getAuxilios().isEmpty()) {
            detalhes.append("<strong>Auxílios solicitados:</strong> <br>");
            for (Auxilio auxilio : pessoa.getAuxilios()) {
                detalhes.append(" - <strong>Tipo de Auxílio:</strong> ").append(auxilio.getTipoAuxilio());

                if (auxilio.getDescricaoOutros() != null && !auxilio.getDescricaoOutros().isEmpty()) {
                    detalhes.append(", <strong>Descrição:</strong> ").append(auxilio.getDescricaoOutros());
                }

                detalhes.append("<br>");
            }
        }

        return detalhes.toString();
    }

    public String uploadFile(MultipartFile file) {
        return s3Service.uploadFile(file);
    }

    public void saveAnexo(Anexo anexo) {
        anexoRepository.save(anexo);
    }

}
