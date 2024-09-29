package com.gacci.gacci_data_generator;

import com.gacci.gacci_data_generator.model.*;
import com.gacci.gacci_data_generator.model.enums.TipoAuxilio;
import com.gacci.gacci_data_generator.repository.PessoaRepository;
import com.gacci.gacci_data_generator.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PessoaServiceTest {

    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testFindAll() {
        List<Pessoa> pessoasMock = new ArrayList<>();
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("Maria");
        pessoasMock.add(pessoa1);

        when(pessoaRepository.findAll()).thenReturn(pessoasMock);

        List<Pessoa> pessoas = pessoaService.findAll();

        assertNotNull(pessoas);
        assertEquals(1, pessoas.size());
        assertEquals("Maria", pessoas.get(0).getNome());

        verify(pessoaRepository, times(1)).findAll();
    }

    @Test
    void testFindByFilter() {
        List<Pessoa> pessoasMock = new ArrayList<>();
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("Ana");
        pessoa1.setCpf("12345678901");
        pessoasMock.add(pessoa1);

        when(pessoaRepository.findByNomeContainingIgnoreCaseOrCpfContainingIgnoreCase("Ana", "Ana"))
                .thenReturn(pessoasMock);

        List<Pessoa> pessoas = pessoaService.findByFilter("Ana");

        assertNotNull(pessoas);
        assertEquals(1, pessoas.size());
        assertEquals("Ana", pessoas.get(0).getNome());

        verify(pessoaRepository, times(1))
                .findByNomeContainingIgnoreCaseOrCpfContainingIgnoreCase("Ana", "Ana");
    }
}
