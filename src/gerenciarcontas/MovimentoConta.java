package gerenciarcontas;

import tools.DataCustom;

import java.util.Scanner;

public class MovimentoConta {

    DataCustom data = new DataCustom();

    public void mostrarExtratoConta(String nome,Double contaCorrente, Double chequeEspecial,Double poupanca){
        double saldoTotal = chequeEspecial + contaCorrente+ poupanca;

        System.out.println("Olá, " + nome);
        System.out.println("Extrato do dia: " + data.mostrarDataEHora());
        System.out.println("---------------------");
        System.out.println("Conta Corrente: R$"+contaCorrente);
        System.out.println("Limite Cheque Especial: R$"+chequeEspecial);
        System.out.println("---------------------");
        if (poupanca>0){
            System.out.println("Total Poupança: R$" + poupanca);
            System.out.println("---------------------");
        }
        System.out.println("Saldo Total com investimentos R$"+saldoTotal);
        System.out.println("---------------------");
        if (contaCorrente < 0){
            System.out.println("Você tem cobranças pendentes, mantenha-se em dia com cheque especial\nEvite cobrança de Juros!");
        }

        System.out.println("*Fim");
        System.out.println("Pressione Enter!");
    }

    public Double sacarDinheiro(Double valorSaque, Double saldoContaCorrente, Double saldoChequeEspecial){
        Double saldoTotalDisponivelSaque = saldoContaCorrente+saldoChequeEspecial;

        if (valorSaque>saldoTotalDisponivelSaque){
            System.out.println("Saldo insuficiente!");
            return 0.0;
        } else {
            saldoContaCorrente -= valorSaque;
            System.out.println("Saque efetuado com sucesso!");
        }

        return saldoContaCorrente;
    }
    public Double depositarDinheiro(Double valorDeposito, Double saldoContaCorrente){

        if (valorDeposito < 0.0){
            System.out.println("Valor inválido né meu querido?");
        } else {
            System.out.println("Procedido deposito de R$" + valorDeposito);

            saldoContaCorrente += valorDeposito;
        }

        return saldoContaCorrente;
    }
    public Double transferirDinheiro(Double valorTranferencia, Double saldoContaCorrente, Double saldoChequeEspecial){
        Double saldoTotalDisponivel = saldoContaCorrente+saldoChequeEspecial;

        Scanner scan = new Scanner(System.in);

        System.out.println("Conta: ");
        String input1 = scan.nextLine();
        System.out.println("Agencia: ");
        String input2 = scan.nextLine();

        if (!input1.equals("")){
            if (valorTranferencia>saldoTotalDisponivel){
                System.out.println("Saldo insuficiente!");
                return 0.0;
            } else {
                saldoContaCorrente -= valorTranferencia;
                System.out.println("Transação feita com sucesso efetuado com sucesso!");
            }
        } else{
            System.out.println("Precisa preencher os dados!");
        }



        return saldoContaCorrente;
    }

}