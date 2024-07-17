package gerenciarcontas;

import model.ContaDados;
import tools.IOJson;
import tools.ParametrosSistema;

import java.util.Scanner;

public class EntrarConta {

    private boolean DEBUGMODE = new ParametrosSistema().ativarDebug();

    private String contaInput;
    private String agenciaInput;
    private String senhaInput;


    public Boolean entrarConta(String contaInput, String agenciaInput, String senhaInput){
        Scanner scan = new Scanner(System.in);
        ContaDados conta;
        IOJson lerJson = new IOJson();

        if (DEBUGMODE){
            return true;
        }

        conta = lerJson.lerArquivoCliente(agenciaInput,contaInput);


        if (contaInput.equals(conta.getConta())){
            if (agenciaInput.equals(conta.getAgencia())){
                if (senhaInput.equals(conta.getSenha())){
                    return true;
                }else {
                    System.out.println("Dados incorretos!");
                }
            }else {
                System.out.println("Dados incorretos!");
            }
        } else {
            System.out.println("Dados incorretos!");
        }

        return false;
    }

}