package Servidor;

import java.util.Scanner;

public class Tabuleiro {
    int linha, coluna;
    Scanner sc = new Scanner(System.in);
    
    public void inicializaTabuleiro(int[][] tabuleiro){
        for(int linha=0 ; linha < 10 ; linha++ ){
            for(int coluna=0 ; coluna < 10 ; coluna++ ){
                tabuleiro[linha][coluna]=-1;
            }
        }
    }
    
    public String mostraTabuleiro(int[][] tabuleiro){
        String mostraTabu;
        mostraTabu = "\t1 \t2 \t3 \t4 \t5 \t6 \t7 \t8 \t9 \t10\n";
        
        for(int linha=0 ; linha < 10 ; linha++ ){
            mostraTabu = mostraTabu + (linha+1)+" ";
            for(int coluna=0 ; coluna < 10 ; coluna++ ){
                if(tabuleiro[linha][coluna]==-1){
                    mostraTabu = mostraTabu + "\t"+"~";
                }else if(tabuleiro[linha][coluna]==0){
                    mostraTabu = mostraTabu + "\t"+"*";
                }else if(tabuleiro[linha][coluna]==1){
                    mostraTabu = mostraTabu + "\t"+"X";
                } else if(tabuleiro[linha][coluna]==3){
                    mostraTabu = mostraTabu + "\t"+"~";
                }               
            }
            mostraTabu = mostraTabu + "\n";
        }
        return mostraTabu;
    }
    
    
}
