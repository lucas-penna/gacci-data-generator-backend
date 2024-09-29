package com.gacci.gacci_data_generator.repository;

import com.gacci.gacci_data_generator.model.Auxilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuxilioRepository extends JpaRepository<Auxilio, Long> {
}
