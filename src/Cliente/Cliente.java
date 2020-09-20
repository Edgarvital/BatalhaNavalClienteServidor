package Cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JOptionPane;
public class Cliente {
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner teclado = new Scanner(System.in);        
        System.out.println("# - Iniciando Cliente...");
        Thread.sleep(2000);
        System.out.println("# - Iniciando Conexão Com O Servidor...\n");
        String ip = JOptionPane.showInputDialog("Digite O IP Do Servidor!");
        int porta = Integer.parseInt(JOptionPane.showInputDialog("Digite A Porta Do Servidor!"));
        Socket socket = new Socket(ip, porta); //Estabelece Conexão com o Servidor
        System.out.println("# - Conexão Estabelecida!\n Iniciando Jogo Em:");
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
        System.out.println("Aguardando O Player 1 Posicionar Os Navios...");
        while(true){
            String mensagem;
            mensagem = in.readLine();
            if(mensagem.equals("digite")){
                out.println(teclado.nextLine());
            }else if(mensagem.equals("fim")){
                break;
            }else
            System.out.println(mensagem);
            
        }
        System.out.println("Encerrando a Conexão...");
        in.close();
        out.close();
        socket.close();
    }
}
