package poo.xadrez;

import java.util.Scanner;

/**
 *
 * @author Gabriel
 * @author Paula
 */
public class Gerenciador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String jogadorNomeA, jogadorNomeB;
        Scanner entrada = new Scanner(System.in);
        
        /* Nome dos Jogadores */
        System.out.println("Escreva o nome do Jogador A (Peças Brancas)");
        jogadorNomeA = entrada.nextLine();
        
        System.out.println("Escreva o nome do Jogador B (Peças Brancas)");
        jogadorNomeB = entrada.nextLine();
        
        Jogo jogo = new Jogo(jogadorNomeA, jogadorNomeB);
        
        jogo.desarquivar();
        jogo.rodando();
        
    }
    
}
