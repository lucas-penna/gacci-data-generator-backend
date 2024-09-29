package com.gacci.gacci_data_generator.repository;

import com.gacci.gacci_data_generator.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findByNomeContainingIgnoreCaseOrCpfContainingIgnoreCase(String nome, String cpf);
}
