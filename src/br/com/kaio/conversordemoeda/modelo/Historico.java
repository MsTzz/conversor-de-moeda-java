package br.com.kaio.conversordemoeda.modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Historico {
    private Scanner leitura;
    List<Moeda> historicoDeConversao = new ArrayList<>();

    public Historico(Scanner leitura) {
        this.leitura = leitura;
    }

    public void Adicionar(Moeda moeda){
        historicoDeConversao.add(moeda);
    }

    public void TemHistorico(){
        if (!historicoDeConversao.isEmpty()){
            System.out.println("\nExibindo histórico de conversões: \n");
            for (Moeda item : historicoDeConversao) {
                System.out.println("- " + item);
            }
            QuerSalvar();
        } else {
            System.out.println("\nO histórico está vazio!\n");
        }
    }

    private void QuerSalvar() {
        leitura.nextLine(); //Tive que colocar para limpar o Scannar!!
        String resposta = "";
        while (true) {
            System.out.println("\n\nDeseja salvar o histórico?\n" +
                    "1 - Salvar!     2 - Voltar ao Menu!");

            resposta = leitura.nextLine().trim();

            if (resposta.trim().equalsIgnoreCase("1")) {
                SalvandoHistorico();
                break;

            } else if (resposta.equalsIgnoreCase("2")) {
                leitura.nextLine();
                for (int i = 0; i < 15; i++) {
                    System.out.println("\n\n");
                }
                break;

            } else {
                System.out.println("\nOpção inválida. Tente novamente.");
            }
        }
    }

    private void SalvandoHistorico(){
        System.out.println("\nQual nome gostaria de salvar o arquivo?");
        String nomeArquivo = leitura.nextLine().trim() + ".txt";

        String caminhoArquivo = System.getProperty("user.home") + "/Desktop/" + nomeArquivo;

        try (FileWriter escreve = new FileWriter(caminhoArquivo)) {
            escreve.write(historicoDeConversao.toString());
            System.out.println("\nArquivo salvo com sucesso em: " + caminhoArquivo);

        } catch (IOException e) {
            System.err.println("\nErro ao salvar o arquivo: " + e.getMessage());
        }
    }

}
