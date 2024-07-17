package model;

import java.io.Serializable;

public class ContaDados {
    String agencia,conta,senha,nome;
    Double saldo;
    int serasaScore;
    Boolean isContaCorrente = true;
    Boolean isPoupanca = false;

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public int getSerasaScore() {
        return serasaScore;
    }

    public void setSerasaScore(int serasaScore) {
        this.serasaScore = serasaScore;
    }

    public Boolean getContaCorrente() {
        return isContaCorrente;
    }

    public void setContaCorrente(Boolean contaCorrente) {
        isContaCorrente = contaCorrente;
    }

    public Boolean getPoupanca() {
        return isPoupanca;
    }

    public void setPoupanca(Boolean poupanca) {
        isPoupanca = poupanca;
    }
}