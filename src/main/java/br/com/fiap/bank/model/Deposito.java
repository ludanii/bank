package br.com.fiap.bank.model;

public class Deposito {
  private Long numero;
  private double valor;
  
  public Deposito(Long numero, double valor) {
    this.numero = numero;
    this.valor = valor;
  }
  public Long getNumero() {
    return numero;
  }
  public void setNumero(Long numero) {
    this.numero = numero;
  }
  public double getValor() {
    return valor;
  }
  public void setValor(double valor) {
    this.valor = valor;
  }

  
  
}
