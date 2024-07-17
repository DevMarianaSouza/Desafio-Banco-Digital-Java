package gerenciarcontas;

public class MovimentoPoupanca {

    public Double depositarPoupanca(Double valorDepositoPoupanca, Double saldoContaCorrente, Double saldoChequeEspecial){
        Double saldoTotalDisponivelSaque = saldoContaCorrente+saldoChequeEspecial;

        if (valorDepositoPoupanca>saldoTotalDisponivelSaque){
            System.out.println("Saldo insuficiente!");
            return 0.0;
        } else {
            System.out.println("Deposito na poupança efetuado com sucesso!");
        }

        return valorDepositoPoupanca;
    }

    // TODO: 12/10/2022 sacar poupança
    public Double sacarPoupanca(Double valorSaquePoupanca, Double saldopoupanca){


        if (valorSaquePoupanca>saldopoupanca){
            System.out.println("Saldo insuficiente!");
            return 0.0;
        } else {
            System.out.println("Saque da poupança efetuado com sucesso!");
        }

        return valorSaquePoupanca;
    }
}