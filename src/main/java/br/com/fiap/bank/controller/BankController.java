package br.com.fiap.bank.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/")
    public ResponseEntity<String> getNomes() {
        log.info("Buscando Informações do projeto...");
        var infoProjeto = "Nome do projeto: Bank \nNomes: Luisa Danielle e Michelle Pontenza";
        return ResponseEntity.ok(infoProjeto);
    }
    
}
 