package tools;

import model.ContaDados;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOJson {

    public void registrarArquivoCliente(ContaDados conta){
        //Cria um Objeto JSON
        JSONObject jsonObject = new JSONObject();

        FileWriter writeFile = null;

        //Armazena dados em um Objeto JSON
        jsonObject.put("nome", conta.getNome());
        jsonObject.put("senha", conta.getSenha());
        jsonObject.put("agencia", conta.getAgencia());
        jsonObject.put("conta", conta.getConta());
        jsonObject.put("score", conta.getSerasaScore());
        jsonObject.put("ispoupanca", conta.getPoupanca());


        try{
            writeFile = new FileWriter(conta.getAgencia()+conta.getConta()+".json");
            //Escreve no arquivo conteudo do Objeto JSON
            writeFile.write(jsonObject.toJSONString());
            writeFile.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        //Imprimne na Tela o Objeto JSON para vizualização
        System.out.println(jsonObject);
    }

    public ContaDados lerArquivoCliente(String codagencia, String codConta){
        JSONObject jsonObject;
        //Cria o parse de tratamento
        JSONParser parser = new JSONParser();
        //Variaveis que irao armazenar os dados do arquivo JSON

        String nome,senha,agencia,conta;
        Long scoreSerasa;
        Boolean isPoupanca;

        ContaDados contaDados = new ContaDados();

        try {
            //Salva no objeto JSONObject o que o parse tratou do arquivo
            jsonObject = (JSONObject) parser.parse(new FileReader(
                    codagencia+codConta+".json"));

            //Salva nas variaveis os dados retirados do arquivo
            nome = (String) jsonObject.get("nome");
            senha = (String) jsonObject.get("senha");
            agencia = (String) jsonObject.get("agencia");
            conta = (String) jsonObject.get("conta");
            scoreSerasa = (long) jsonObject.get("score");
            isPoupanca = (boolean) jsonObject.get("ispoupanca");

            contaDados.setNome(nome);
            contaDados.setSenha(senha);
            contaDados.setAgencia(agencia);
            contaDados.setConta(conta);
            contaDados.setSerasaScore(Integer.parseInt(Long.toString(scoreSerasa)));
            contaDados.setPoupanca(isPoupanca);

        }
        //Trata as exceptions que podem ser lançadas no decorrer do processo
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return contaDados;
    }

}