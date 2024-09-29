package com.gacci.gacci_data_generator.repository;

import com.gacci.gacci_data_generator.model.Habitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacaoRepository extends JpaRepository<Habitacao, Long> {
}
