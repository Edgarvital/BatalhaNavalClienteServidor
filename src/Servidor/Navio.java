package Servidor;

public class Navio {
    //3 é o valor do barco
    public void inicializaNavio(int[][] tabuleiro, int linha, int coluna){
            if(tabuleiro[linha][coluna] != 3){
                tabuleiro[linha][coluna] = 3;
            }
    }
}
