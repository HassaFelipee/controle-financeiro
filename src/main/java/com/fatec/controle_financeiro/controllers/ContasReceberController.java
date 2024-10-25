package com.fatec.controle_financeiro.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.controle_financeiro.domain.contasreceber.ContasReceberRepository;
import com.fatec.controle_financeiro.entities.ContasReceber;

@RestController
@RequestMapping("/api/contasReceber")
public class ContasReceberController {
    
    @Autowired
    private ContasReceberRepository contasReceberRepository;

    //CREATE
    @PostMapping()
    public ResponseEntity<ContasReceber> create(@RequestBody ContasReceber contasReceber) {

        ContasReceber created = contasReceberRepository.save(contasReceber);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    } 

    //READ
    @GetMapping()
    public ResponseEntity<List<ContasReceber>> getAllContas() {
        List<ContasReceber> contasRecebers = contasReceberRepository.findAll();

        return new ResponseEntity<>(contasRecebers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ContasReceber> getById(@PathVariable long id) {
        Optional<ContasReceber> contasReceber = contasReceberRepository.findById(id);
        if (contasReceber.isPresent()) {
            return new ResponseEntity<>(contasReceber.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //UPDATE
    @PutMapping("{id}")
    public ResponseEntity<ContasReceber> updateContasReceber(@PathVariable long id, @RequestBody ContasReceber entity) {
        Optional<ContasReceber> contaAtual = contasReceberRepository.findById(id);
        if (contaAtual.isPresent()) {
            entity.setId(id);
            contasReceberRepository.save(entity);
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable long id) {
        Optional<ContasReceber> contaAtual = contasReceberRepository.findById(id);
        if (contaAtual.isPresent()) {
            contasReceberRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
