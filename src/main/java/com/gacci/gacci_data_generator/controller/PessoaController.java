package com.gacci.gacci_data_generator.controller;

import com.gacci.gacci_data_generator.model.Anexo;
import com.gacci.gacci_data_generator.model.Pessoa;
import com.gacci.gacci_data_generator.model.PessoaDTO;
import com.gacci.gacci_data_generator.service.PessoaService;
import com.gacci.gacci_data_generator.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @Autowired
    private S3Service s3Service;


    @PostMapping("/save")
    public ResponseEntity<Pessoa> salvarPessoa(@RequestPart PessoaDTO pessoa, @RequestParam("file") MultipartFile file) {
        Pessoa pessoaSalva = service.save(pessoa);

        if (file != null && !file.isEmpty()) {
            String filePath = service.uploadFile(file);
            Anexo anexo = new Anexo(file.getOriginalFilename(), filePath, pessoaSalva.getId());
            service.saveAnexo(anexo);
        }

        return ResponseEntity.ok(pessoaSalva);
    }


    @GetMapping("/findAll")
    public ResponseEntity<List<Pessoa>> findAll() {
        List<Pessoa> pessoas = service.findAll();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/findByFilter")
    public ResponseEntity<List<Pessoa>> findByFilter(@RequestParam("filter") String filter) {
        List<Pessoa> pessoas = service.findByFilter(filter);
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/getAnexos")
    public ResponseEntity<List<Anexo>> findAnexos(@RequestParam("filter") String id) {
        List<Anexo> anexos = service.getAnexos(id);
        return ResponseEntity.ok(anexos);
    }

    @GetMapping("/details")
    public ResponseEntity<String> getDetails(@RequestParam("filter") String id) {
        String details = service.getDetails(id);
        return ResponseEntity.ok(details);
    }


    @GetMapping("/download/{id}")
    public ResponseEntity<Map<String, Object>> downloadAnexo(@PathVariable Long id) {
        Anexo anexo = service.getAnexoById(id);
        byte[] fileContent = s3Service.downloadFile(anexo.getCaminhoS3());

        Map<String, Object> response = new HashMap<>();
        response.put("fileName", anexo.getNomeArquivo());
        response.put("fileContent", Base64.getEncoder().encodeToString(fileContent));  // Codifica o arquivo em base64

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
