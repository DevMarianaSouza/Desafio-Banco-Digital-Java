package gerenciarcontas;

import model.ContaDados;
import tools.IOJson;

import java.util.Random;
import java.util.Scanner;


public class CriarConta{

    String nome,senha,decisaoPoupanca;
    Boolean isOptouPoupanca = false;



    public void criarConta(){
        Scanner scan = new Scanner(System.in);
        ContaDados conta = new ContaDados();

        System.out.println("Digite o nome:");
        nome = scan.nextLine();
        System.out.println("Senha:");
        senha = scan.nextLine();
        System.out.println("Optar por conta poupança? [S/N]");
        decisaoPoupanca = scan.nextLine();
        if (decisaoPoupanca.equals("S")){
            isOptouPoupanca = true;
        }

        conta.setNome(nome);
        conta.setSenha(senha);
        conta.setAgencia(gerarAgencia());
        conta.setConta(gerarConta());
        conta.setSerasaScore(gerarScoreSerada());
        conta.setPoupanca(isOptouPoupanca);

        new IOJson().registrarArquivoCliente(conta);

        //System.out.println("Nome: "+conta.getNome() +"\nSenha: "+ conta.getSenha() +"\nAgencia: "+ conta.getAgencia() +"\nConta: "+ conta.getConta() +"\nScore Serasa: "+ conta.getSerasaScore() +"\nUsa poupança?: "+ conta.getPoupanca());

    }

    private String gerarAgencia(){
        Random sortear = new Random();
        int codAgencia = sortear.nextInt(9999);
        String convertedAgencia = Integer.toString(codAgencia);
        return convertedAgencia;
    }
    private String gerarConta(){
        Random sortear = new Random();
        int codConta = sortear.nextInt(999999);
        String convertedConta = Integer.toString(codConta);
        return convertedConta;
    }
    private int gerarScoreSerada(){
        Random sortear = new Random();
        int scoreSerasa = sortear.nextInt(1000);
        return scoreSerasa;
    }

}