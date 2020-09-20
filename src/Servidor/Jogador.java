package Servidor;

import java.util.Scanner;

public class Jogador {
    private Tabuleiro tab;
    private Navio nav;
    private int linha, coluna, acertos = 0;
    private int continua = 0;
    Scanner sc = new Scanner(System.in);
    
    public Jogador(){
    }
    
    public void tiro(int[][] tabu, int linha, int coluna){        
                if(tabu[linha][coluna] == -1 || tabu[linha][coluna] == 0 ){
                    tabu[linha-1][coluna-1] = 0;
                    continua = 1;
                } else if(tabu[linha][coluna] == 3 && acertos < 3){
                    tabu[linha-1][coluna-1] = 1;
                    acertos +=1;
                    continua = 0;
                } 
    }

    public int getAcertos() {
        return acertos;
    }

    public int getContinua() {
        return continua;
    }

    public void setContinua(int continua) {
        this.continua = continua;
    }   
    
 
}
