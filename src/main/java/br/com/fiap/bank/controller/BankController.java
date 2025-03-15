package br.com.fiap.bank.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale.Category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.bank.model.Tipo;
import br.com.fiap.bank.model.User;

@RestController
public class BankController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private List<User> repository = new ArrayList<>();
    
    private void validarCampos(User user) {
        if (user.getNomeTitular().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Categoria não encontrada!");
        }
        if (user.getCpf().isEmpty()) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"CPF é obrigatório!");
        }
        if (user.getDataAbertura().isAfter(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"A data de abertura não pode ser no futuro!");
        }
        if (user.getSaldoInicial() < 0){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"O saldo inicial deve ser positivo!");
        }
        if (!Arrays.asList(Tipo.values()).contains(user.getTipo())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"O tipo inválido! (Corrente, Poupança ou Salário)");
        }
    }

    @GetMapping("/")
    public ResponseEntity<String> getNomes() {
        log.info("Buscando Informações do projeto...");
        var infoProjeto = "Nome do projeto: Bank \nNomes: Luisa Danielle e Michelle Pontenza";
        return ResponseEntity.ok(infoProjeto);
    }
    

    @PostMapping("/bank")
    public ResponseEntity<Object> cadastro(@RequestBody User user){
        validarCampos(user);
        log.info("Cadastrando " + user);
        repository.add(user);
        return ResponseEntity.ok(user);
        
    }
    
}
 