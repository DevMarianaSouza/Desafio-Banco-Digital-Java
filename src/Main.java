/*
 * Samuel Michels 04-oct-2022
 *
 * Proposta:Um banco oferece aos seus clientes dois tipos de contas (corrente e poupança),
 * as quais possuem as funcionalidades de
 * depósito,
 * saque
 * e transferência
 *
 * (entre contas da própria instituição).
 * */

import gerenciarcontas.CriarConta;
import gerenciarcontas.EntrarConta;
import gerenciarcontas.MovimentoConta;
import gerenciarcontas.MovimentoPoupanca;
import model.ContaDados;
import tools.IOJson;
import tools.ParametrosSistema;

import java.util.Random;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Boolean DEBUGMODE = new ParametrosSistema().ativarDebug();
        Boolean isContaEntrarValida = false;
        String decisao,
                agenciaInput = "",
                contaInput = "",
                senhaInput = "";

        double saldoTotal = 0,
                saldoContaCorrente = 0,
                saldoChequeEspecial = 0,
                saldoInicialChequeEspecial = 0,
                saldoPoupanca = 0;

        Scanner scan = new Scanner(System.in);
        ContaDados dadosConta;
        IOJson ioJson = new IOJson();
        MovimentoConta movimentoConta = new MovimentoConta();
        MovimentoPoupanca movimentoPoupanca = new MovimentoPoupanca();

        System.out.println("BEM VINDO AO BANCO DIGITAL TIAMO \n 1.Entrar \n 2.cadastrar\n->");
        decisao = scan.nextLine();

        //Interface de Boas vindas
        switch (decisao){
            case "1":

                System.out.println("ENTRAR");
                System.out.println("Conta: ");
                contaInput = scan.nextLine();
                System.out.println("Agencia: ");
                agenciaInput = scan.nextLine();
                System.out.println("Senha: ");
                senhaInput = scan.nextLine();

                isContaEntrarValida = new EntrarConta().entrarConta(contaInput,agenciaInput,senhaInput);
                break;

            case "2":
                System.out.println("CADASTRAR");
                new CriarConta().criarConta();

                System.out.println("ENTRAR");
                System.out.println("Conta: ");
                contaInput = scan.nextLine();
                System.out.println("Agencia: ");
                agenciaInput = scan.nextLine();
                System.out.println("Senha: ");
                senhaInput = scan.nextLine();
                isContaEntrarValida = new EntrarConta().entrarConta(contaInput,agenciaInput,senhaInput);
                break;

        }

        //Se o Usuário foi validado
        if (isContaEntrarValida){


            //Carregando dados conta usuário
            if(DEBUGMODE){
                dadosConta = ioJson.lerArquivoCliente("debug","");
            } else {
                dadosConta = ioJson.lerArquivoCliente(agenciaInput,contaInput);
            }

            System.out.println("Bem vindo! "+ dadosConta.getNome());

            saldoChequeEspecial = calcularCalcularLimiteInicialChequeEspecial(dadosConta.getSerasaScore());

            //looping dados conta
            while(isContaEntrarValida){

                decisao = "0";

                saldoTotal = saldoContaCorrente + saldoChequeEspecial;


                System.out.println("Saldo:R$"+saldoContaCorrente+"\n1.Extrato Detalhado\n2.Sacar\n3.Depositar\n4.transferir");
                if(dadosConta.getPoupanca()){
                    System.out.println("5.Depositar Poupança\n6.Retirar Poupança");
                }
                System.out.println("-->");
                decisao = scan.nextLine();


                switch (decisao){
                    case "1":
                        // 1.Extrato Detalhado
                        movimentoConta.mostrarExtratoConta(dadosConta.getNome(),saldoContaCorrente,saldoChequeEspecial,saldoPoupanca);
                        decisao = scan.nextLine();
                        break;

                    case "2":
                        // 2.Sacar
                        System.out.println("Qual valor do Saque?\n Valor máximo disponível para saque R$"+saldoTotal);
                        Double valorSaque = scan.nextDouble();

                        saldoContaCorrente = movimentoConta.sacarDinheiro(valorSaque,saldoContaCorrente,saldoChequeEspecial);
                        break;
                    case "3":
                        // 3.Depositar
                        System.out.println("Qual valor do Depósito?\n ->");
                        Double valorDeposito = scan.nextDouble();

                        saldoContaCorrente = movimentoConta.depositarDinheiro(valorDeposito,saldoContaCorrente);
                        break;
                    case "4":
                        // 4.Transferir
                        System.out.println("Qual valor do Saque?\n Valor máximo disponível para saque R$"+saldoTotal);
                        Double valorTransferencia = scan.nextDouble();

                        saldoContaCorrente = movimentoConta.transferirDinheiro(valorTransferencia,saldoContaCorrente,saldoChequeEspecial);
                        break;
                }
                if (dadosConta.getPoupanca()){
                    switch (decisao){
                        case "5":
                            // 5.Depositar Poupança
                            System.out.println("Quer depositar quanto na poupança?\n Valor máximo disponível para deposito R$"+saldoTotal);
                            Double valorDepositoPoupanca = scan.nextDouble();

                            saldoPoupanca = movimentoPoupanca.depositarPoupanca(valorDepositoPoupanca,saldoContaCorrente,saldoChequeEspecial);

                            saldoContaCorrente -= saldoPoupanca;

                            System.out.println("Saldo Poupança: R$"+saldoPoupanca);

                            break;
                        case "6":
                            // TODO: 10/10/2022 fazer classe e metodo de Saque da poupança
                            System.out.println("Quer sacar quanto da poupança?\n Valor máximo disponível para saque R$"+saldoPoupanca);
                            Double valorSaldoSaquePoupanca = scan.nextDouble();

                            valorSaldoSaquePoupanca = movimentoPoupanca.sacarPoupanca(valorSaldoSaquePoupanca,saldoPoupanca);

                            saldoContaCorrente += valorSaldoSaquePoupanca;

                            saldoPoupanca -= valorSaldoSaquePoupanca;

                            System.out.println("Saldo Poupança: R$"+saldoPoupanca);
                            break;
                    }
                }


            }
        }


    }

    public static Double calcularCalcularLimiteInicialChequeEspecial(int scoreSerasa){
        Double valorDoChequeEspecial = 0.0;

        Random random = new Random();

        if (scoreSerasa <= 300){
            valorDoChequeEspecial =+ 300.0;
        } else if(scoreSerasa > 300 && scoreSerasa <=500){
            valorDoChequeEspecial =+ 550.0;
        } else if(scoreSerasa > 500 && scoreSerasa <=700){
            valorDoChequeEspecial =+ 1000.0;
        } else {
            valorDoChequeEspecial =+ 3500.0;
        }

        return valorDoChequeEspecial;
    }

}