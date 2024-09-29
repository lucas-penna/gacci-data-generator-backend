package com.gacci.gacci_data_generator.repository;

import com.gacci.gacci_data_generator.model.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnexoRepository extends JpaRepository<Anexo, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM anexo WHERE pessoa_id = ?1")
    List<Anexo> findAllByPatient(Long id);
}