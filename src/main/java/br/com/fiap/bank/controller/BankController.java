package br.com.fiap.bank.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.bank.exceptions.ValidationUtils;
import br.com.fiap.bank.model.Deposito;
import br.com.fiap.bank.model.Tipo;
import br.com.fiap.bank.model.User;
@Validated
@RestController
public class BankController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private List<User> repository = new ArrayList<>();
    
    private void validarCampos(User user) {
    ValidationUtils.validate(user.getNomeTitular().isEmpty(), HttpStatus.BAD_REQUEST, "O nome do titular é obrigatório!");
    ValidationUtils.validate(user.getCpf().isEmpty(), HttpStatus.BAD_REQUEST, "CPF é obrigatório!");
    ValidationUtils.validate(user.getDataAbertura().isAfter(LocalDate.now()), HttpStatus.BAD_REQUEST, "A data de abertura não pode ser no futuro!");
    ValidationUtils.validate(user.getSaldoInicial() < 0, HttpStatus.BAD_REQUEST, "O saldo inicial deve ser positivo!");
    ValidationUtils.validate(!Stream.of(Tipo.values()).anyMatch(t -> t == user.getTipo()), HttpStatus.BAD_REQUEST, "O tipo é inválido! (Corrente, Poupança ou Salário)");
}

    @GetMapping("/")
    public ResponseEntity<String> getNomes() {
        log.info("Buscando Informações do projeto...");
        var infoProjeto = "Nome do projeto: Bank \nNomes: Luisa Danielle e Michelle Pontenza";
        return ResponseEntity.ok(infoProjeto);
    }
    

    @PostMapping("/bank")
    public ResponseEntity<User> cadastro(@RequestBody User user){
        validarCampos(user);
        log.info("Cadastrando " + user);
        repository.add(user);
        return ResponseEntity.ok(user);
        
    } 
    @GetMapping("/bank")
    public List<User> visualizadorAll(){
        log.info("Buscando todas os usuarios");
        return repository;
    }

    @GetMapping("/bank/{numero}")
    public  ResponseEntity<Object> visualizadorNumero(@PathVariable Long numero){
        log.info("Buscando usuario de numero " + numero);
        var user = getUser(numero, null);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/bank/cpf/{cpf}")
    public ResponseEntity<Object> visualizadorCpf(@PathVariable String cpf){
        log.info("Buscando usuario pelo cpf " + cpf);
        var user = getUser(null, cpf);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/bank/{numero}")
    public ResponseEntity<Object> encerrarUsuario(@PathVariable Long numero){
        log.info("Encerrando usuario " + numero);
        var userFiltrado = getUser(numero, null);
        userFiltrado.setStatus("Inativo");
        return ResponseEntity.ok(userFiltrado);
    }

    @PutMapping("/bank/deposito")
    public ResponseEntity<Object> deposito(@RequestBody Deposito deposito){
        var userFiltrado = getUser(deposito.getNumero(), null);
        ValidationUtils.validate(deposito.getValor() <= 0, HttpStatus.BAD_REQUEST, "O valor do depósito deve ser maior que 0!");
        log.info("Debitando R$ " + deposito.getValor() + " na conta do usurio " + userFiltrado.getNomeTitular());
        userFiltrado.setSaldoInicial(userFiltrado.getSaldoInicial() + deposito.getValor());
        return ResponseEntity.ok(userFiltrado);
    }

   private User getUser(Long numero, String cpf) {
    return repository.stream()
            .filter(u -> (numero == null || u.getNumero().equals(numero)) &&
                         (cpf == null || u.getCpf().equals(cpf)))
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Usuário não encontrado!"
            ));
}
}
 