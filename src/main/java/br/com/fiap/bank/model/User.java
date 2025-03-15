package br.com.fiap.bank.model;

import java.time.LocalDate;
import java.util.Random;

public class User {
    private Long numero;
    private double saldoInicial;
    private int agencia;
    private String nomeTitular, status, cpf;
    private LocalDate dataAbertura;
    private Tipo tipo;

    public User(Long numero, double saldoInicial, int agencia, String cpf, String nomeTitular, LocalDate dataAbertura, String status, Tipo tipo) {
        this.numero = Math.abs(new Random().nextLong());
        this.saldoInicial = saldoInicial;
        this.agencia = agencia;
        this.cpf = cpf;
        this.nomeTitular = nomeTitular;
        this.dataAbertura = dataAbertura;
        this.status = status;
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String toString() {
        return agencia + " " + nomeTitular + " " + tipo + " - " + saldoInicial;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
}

