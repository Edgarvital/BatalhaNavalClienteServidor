package Servidor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("# - Iniciando Servidor...");
        Thread.sleep(2000);
        ServerSocket server = new ServerSocket(2525); //Ouve na porta 2525
        System.out.println("# - Servidor Iniciado!\n"
                + "# - Aguardando Conexão...");
        System.out.println("# - Conexão Estabelecida Com O Player 1!");
        System.out.println("# - Aguardando Conexão...");
        Socket socket = server.accept(); //Aguarda um cliente estabelecer conexão        
        System.out.println("# - Conexão Estabelecida Com O Player 2!\nIniciando Jogo Em:");
        System.out.println("3...");
        Thread.sleep(1000);
        System.out.println("2...");
        Thread.sleep(1000);
        System.out.println("1...");
        Thread.sleep(1000);
        InputStream input = socket.getInputStream(); //Gerencia o recebimento de dados do tipo byte
        OutputStream output = socket.getOutputStream(); //Gerencia o envio de dados do tipo byte
        BufferedReader in = new BufferedReader(new InputStreamReader(input)); //Converte byte para String
        PrintStream out = new PrintStream(output); //Envia Strings
        Scanner teclado = new Scanner(System.in);
        //Jogo
        int[][] tab1 = new int[11][11];
        int[][] tab2 = new int[11][11];
        Jogador j2 = new Jogador();
        Jogador j1 = new Jogador();
        Tabuleiro t1 = new Tabuleiro();
        Tabuleiro t2 = new Tabuleiro();
        Navio n1 = new Navio();
        Navio n2 = new Navio();
        t1.inicializaTabuleiro(tab1);
        t2.inicializaTabuleiro(tab2);
        System.out.println("Posicione Os Navios!");
        for (int i = 0; i < 3; i++) {
            System.out.println("Digite a Linha: " + (i + 1) + " Para Posicionar O Navio: " + (i + 1));
            int linha = teclado.nextInt();
            System.out.println("Digite a Coluna: " + (i + 1) + " Para Posicionar O Navio: " + (i + 1));
            int coluna = teclado.nextInt();
            if (linha < 0 || coluna < 0 || linha > 11 || coluna > 11) {
                System.out.println("Você Informou uma Posição Inferior a 1 Ou Superior A 10!"
                        + "\nDigite Novamente!");
                i--;
            } else if (tab1[linha][coluna] == 3) {
                System.out.println("Você Colocou Dois Barcos Na Mesma Posição!");
                i--;

            } else {
                n1.inicializaNavio(tab1, linha, coluna);
                System.out.println("Navio Alocado!");
            }
        }
        System.out.println("Aguarde O Player 2 Posicionar Os Navios...");
        for (int i = 0; i < 3; i++) {
            out.println("Posicione Os Navios!");
            out.println("Digite a Linha: " + (i + 1) + " Para Posicionar O Navio: " + (i + 1));
            out.println("digite");
            String mensagem;
            mensagem = in.readLine();
            int linha = Integer.parseInt(mensagem);
            out.println("Digite a Coluna: " + (i + 1) + " Para Posicionar O Navio: " + (i + 1));
            out.println("digite");
            mensagem = in.readLine();
            int coluna = Integer.parseInt(mensagem);
            if (linha < 0 || coluna < 0 || linha > 11 || coluna > 11) {
                out.println("Você Informou uma Posição Inferior a 1 Ou Superior A 10!"
                        + "\nDigite Novamente!");
                i--;
            } else if (tab1[linha][coluna] == 3) {
                out.println("Você Colocou Dois Barcos Na Mesma Posição!");
                i--;

            } else {
                n1.inicializaNavio(tab1, linha, coluna);
                out.println("Navio Alocado!");
            }
        }
        System.out.println("Tabuleiro:");
        System.out.println(t1.mostraTabuleiro(tab2));
        out.println("Tabuleiro:");
        out.println(t2.mostraTabuleiro(tab1));
        out.println("Aguarde O Tiro Do Player 1...");
        int vez = 0;
        while (true) {
            int acerto;
            j1.setContinua(0);
            j2.setContinua(0);
            if (j1.getAcertos() < 3 && vez == 0) {
                int verific = 0;
                for (int j = 0; j1.getContinua() < 1 && j1.getAcertos() < 3; j++) {
                    if (verific > 0) {
                        System.out.println("Acertou, Tente Novamente!!!");
                    }
                    System.out.println("Jogador 1:\nAtire!");
                    System.out.println("Digite a Linha Para Atirar!");
                    int linha = teclado.nextInt();
                    System.out.println("Digite a Coluna Para Atirar!");
                    int coluna = teclado.nextInt();
                    if (linha > 0 && coluna > 0 && linha < 11 && coluna < 11) {
                        j1.tiro(tab2, linha, coluna);
                        ++verific;
                    } else {
                        System.out.println("Você Digitou Uma Posição invalida, digite novamente!");
                    }
                }
                vez = 1;
                verific = 0;
                acerto = j1.getAcertos();
                System.out.println("\nTabuleiro:");
                System.out.println(t1.mostraTabuleiro(tab2));
                System.out.println("Você acertou " + acerto + " Vez(es)!");
                if (acerto < 3) {
                    System.out.println("Aguarde O Tiro Do Player 2...");
                } else {
                    System.out.println("Você Venceu!");
                }
            }

            if (j1.getAcertos() < 3 && vez == 1 && j2.getAcertos() < 3) {
                int verific = 0;
                while (j2.getContinua() < 1 && j2.getAcertos() < 3) {
                    if (verific > 0) {
                        out.println("Acertou, Tente Novamente!!!");
                    }
                    out.println("Jogado 2:\nAtire!");
                    out.println("Digite a Linha Para Atirar!");
                    out.println("digite");
                    String mensagem = in.readLine();
                    int linha = Integer.parseInt(mensagem);
                    out.println("Digite a Coluna Para Atirar!");
                    out.println("digite");
                    mensagem = in.readLine();
                    int coluna = Integer.parseInt(mensagem);
                    if (linha > 0 && coluna > 0 && linha < 11 && coluna < 11) {
                        j2.tiro(tab1, linha, coluna);
                        ++verific;
                    } else {
                        out.println("Você Digitou Uma Posição invalida, digite novamente!");
                    }
                }
                verific = 0;
                acerto = j2.getAcertos();
                vez = 0;
                out.println("\nTabuleiro:");
                out.println(t2.mostraTabuleiro(tab1));
                out.println("Você Acertou: " + acerto + " Vez(es)!");
                if (acerto < 3) {
                    out.println("Aguarde O Tiro Do Player 1...");
                } else {
                    out.println("Você Venceu!");
                }
            }
            if (j1.getAcertos() == 3 || j2.getAcertos() == 3) {
                System.out.println("O Jogo Acabou!");
                out.println("O Jogo Acabou!");
                out.println("fim");
                break;
            }
        }
        System.out.println("# - Encerrando Conexão...");
        in.close();
        out.close();
        socket.close();
    }
}
