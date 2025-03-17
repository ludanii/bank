package br.com.fiap.bank.model;

public class Transferencias {
  private Long numero;
  private Long numeroOrigem;
  private Long numeroDestino;
  private double valor;
  
  public Transferencias(Long numero, Long numeroOrigem, Long numeroDestino, double valor) {
    this.numero = numero;
    this.numeroOrigem = numeroOrigem;
    this.numeroDestino = numeroDestino;
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

  public Long getNumeroOrigem() {
    return numeroOrigem;
  }

  public void setNumeroOrigem(Long numeroOrigem) {
    this.numeroOrigem = numeroOrigem;
  }

  public Long getNumeroDestino() {
    return numeroDestino;
  }

  public void setNumeroDestino(Long numeroDestino) {
    this.numeroDestino = numeroDestino;
  }
}
