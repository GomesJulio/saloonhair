package com.juliogomes.SaloonHairSystem.controllers;

import com.juliogomes.SaloonHairSystem.entity.fornecedor.Fornecedor;
import com.juliogomes.SaloonHairSystem.entity.fornecedor.FornecedorRequestDTO;
import com.juliogomes.SaloonHairSystem.entity.fornecedor.FornecedorResponseDTO;
import com.juliogomes.SaloonHairSystem.repository.FornecedorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    @Autowired
   FornecedorRepository repository;

    @PostMapping
    public ResponseEntity<?> postFornecedor(@RequestBody @Valid FornecedorRequestDTO body){
        Fornecedor newFornecedor = new Fornecedor();
        newFornecedor.setRazaoSocial(body.razaoSocial());
        newFornecedor.setFone(body.fone());
        newFornecedor.setCelular(body.celular());
        newFornecedor.setEmail(body.email());

        this.repository.save(newFornecedor);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<?> getAllFornecedor(){
        List<FornecedorResponseDTO> fornecedorList =
                this.repository.findAll().stream().map(FornecedorResponseDTO::new).toList();

        return ResponseEntity.ok(fornecedorList);
    }

    @PutMapping
    public ResponseEntity<?> updateFornecedor(@PathVariable UUID id, @RequestBody @Valid FornecedorRequestDTO body){

        if (repository.existsById(id)){

           Fornecedor fornecedor = repository.findById(id).get();

            fornecedor.setRazaoSocial(body.razaoSocial());
            fornecedor.setFone(body.fone());
            fornecedor.setCelular(body.celular());
            fornecedor.setEmail(body.email());

            repository.save(fornecedor);
            return ResponseEntity.ok().build();
        } else {
                return ResponseEntity.notFound().build();
        }
    }
}
