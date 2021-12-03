package poo.xadrez;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Jogo {
    
    /* Mensagens de Erro */
    private static final String
            ERRO_VEZ_JOGADOR = "Turno de Jogador invalido",
            ERRO_STATUS_JOGO = "Status de Jogo invalido",
            ERRO_MESMO_JOGADOR = "O mesmo jogador não pode mexer duas vezes!";
            
    
    /* Constantes */
    private static final int
            ESTADO_OCIOSO = 1,  // Estado comum
            ESTADO_XEQUE = 2, // Xeque
            ESTADO_XEQUE_MATE = 3, // Xeque mate
            JOGADOR_REI_A = 4,
            JOGADOR_REI_B = 5;
    
    private static final boolean
            VEZ_JOGADOR_A = true,
            VEZ_JOGADOR_B = false;
    
    
    
    private static final int
            MAX_PECAS = 16;
    
    /* Determina quem está em check*/ 
    private static final int
            NO_CHECK = 0,
            CHECK_JOGADOR_A = 1,
            CHECK_JOGADOR_B = 2;            
    
    /* Atributos */
    
    public Tabuleiro tabuleiro;
    public Jogador jogadorA;
    public Jogador jogadorB;
    private int status;
    private boolean vezJogador;
    private int numJogadas = 0;
    
    /* Peças do Jogador A */
    private Peca[] pecasA  = new Peca[MAX_PECAS];
    
    /* Peças do Jogador B */
    private Peca[] pecasB = new Peca[MAX_PECAS];
    
    /** Construtor Jogo
    * É inicializado na tanto o tabuleiro quanto as peças dos jogadores e os jogadores
    * @param nomeJogadorA, nome do JogadorA
    * @param nomeJogadorB, nome do JogadorB
    */
    public Jogo (String nomeJogadorA, String nomeJogadorB) {
        
       /* Peças A*/
       this.pecasA[0] = new Torre ("branco");
       this.pecasA[1] = new Cavalo ("branco");
       this.pecasA[2] = new Bispo ("branco");
       this.pecasA[3] = new Dama ("branco");
       this.pecasA[4] = new Rei ("branco");
       this.pecasA[5] = new Bispo ("branco");
       this.pecasA[6] = new Cavalo ("branco");
       this.pecasA[7] = new Torre ("branco");
       for(int i = 8; i < MAX_PECAS; i++)
           this.pecasA[i] = new Peao ("branco");
       
        
        /* Jogador A */
        this.jogadorA = new Jogador(nomeJogadorA, pecasA);
        
        /* Peças B*/
       this.pecasB[0] = new Torre ("preto");
       this.pecasB[1] = new Cavalo ("preto");
       this.pecasB[2] = new Bispo ("preto");
       this.pecasB[3] = new Dama ("preto");
       this.pecasB[4] = new Rei ("preto");
       this.pecasB[5] = new Bispo ("preto");
       this.pecasB[6] = new Cavalo ("preto");
       this.pecasB[7] = new Torre ("preto");
       for(int i = 8; i < MAX_PECAS; i++)
           this.pecasB[i] = new Peao ("preto");
       
        
        /* Jogador A */
        this.jogadorB = new Jogador(nomeJogadorB, pecasB);
        
        /* Tabuleiro */
        this.tabuleiro = new Tabuleiro(pecasA, pecasB);
        
        /* Status de começo de Jogo */
        this.setStatus(ESTADO_OCIOSO);
        
        /* Jogador A sempre começa */
        this.setVezJogador(VEZ_JOGADOR_A);    
    }
    
    /** Função principal que mantém o jogo em andamento
     *  Fica em loop até que o status do jogo seja xeque_mate,
     *  e chama outras funções locais
     */
    public void rodando(){        
        while(this.getStatus() != ESTADO_XEQUE_MATE){
            this.fazJogada();
            if(numJogadas >= 15)
                if (this.arquivar()) System.exit(0);       
            this.alternaVez();
        }
    }
    
    /** Menu de interface da Jogada
     *
     */
    private void fazJogada(){
        /* Variaveis Locais */
        Scanner entrada = new Scanner(System.in);
        int check;
        int linhaOrigem, linhaDestino; // 1-8
        char colunaOrig, colunaDest;
        
        this.tabuleiro.desenha();    
        
        if (this.getVezJogador ()) { // Vez do jogador A.  

            do { // Enquanto a linha e coluna de ORIGEM E DESTINO resultarem em um movimento inválido.
                // Receber linha e coluna de ORIGEM do jogador A.
                do {
                    System.out.println("Vez do Jogador: " + this.jogadorA.getNome());

                    System.out.println("Escolha a LINHA da Peça que deseja mover:");                
                    linhaOrigem = recebeLinha (entrada);

                    System.out.println("Escolha a COLUNA da Peça que deseja mover:");
                    colunaOrig = recebeColuna (entrada);

                    try { // Garante que a posição digitada para ORIGEM é do próprio jogador.
                        if ((this.tabuleiro.tabuleiro[linhaOrigem -1][Peca.colunaInt(colunaOrig)]).isOcupado()) break;
                        } catch (NumberFormatException n) {
                            System.out.println("Digite um dígito inteiro de 1 a 8.");
                        } catch (ArrayIndexOutOfBoundsException a) {
                            System.out.println("Digite coordenadas dentro do intervalo.");
                        } 

                } while (true); 

                // Receber linha e coluna de DESTINO do jogador A.
                do {
                    System.out.println("Escolha a LINHA no qual deseja mover a peça:");
                    linhaDestino = recebeLinha (entrada);

                    System.out.println("Escolha a COLUNA no qual deseja mover a peça:");
                    colunaDest = recebeColuna (entrada);

                    try {
                    if ((this.tabuleiro.tabuleiro[linhaOrigem -1][Peca.colunaInt(colunaOrig)]).isOcupado()) break;
                    } catch (NumberFormatException n) {
                        System.out.println("Digite um dígito inteiro de 1 a 8.");
                    } catch (ArrayIndexOutOfBoundsException a) {
                        System.out.println("Digite um número de 1 a 8.");
                    } 

                } while (true);
            } while (!this.tabuleiro.checarMovimento(linhaOrigem, colunaOrig, linhaDestino, colunaDest));
            
        } else { // Vez do jogador B.
            do {
                // Receber a linha e coluna de ORIGEM do jogador B.
                do {
                    System.out.println("Vez do Jogador: " + this.jogadorB.getNome());

                    System.out.println("Escolha a linha da Peça que deseja mover:");
                    linhaOrigem = recebeLinha (entrada);

                    System.out.println("Escolha a coluna da Peça que deseja mover:");
                    colunaOrig = recebeColuna (entrada);

                    try {
                    if ((this.tabuleiro.tabuleiro[linhaOrigem -1][Peca.colunaInt(colunaOrig)]).isOcupado()) break;
                    } catch (NumberFormatException n) {
                        System.out.println("Digite um dígito inteiro de 1 a 8.");
                    } catch (ArrayIndexOutOfBoundsException a) {
                        System.out.println("Digite um número de 1 a 8.");
                    }                

                } while (true);

                // Receber a linha e coluna de DESTINO do jogador B.
                do {
                    System.out.println("Escolha a linha no qual deseja mover a peça:");
                    linhaDestino = recebeLinha (entrada);

                    System.out.println("Escolha a coluna no qual deseja mover a peça:");
                    colunaDest = recebeColuna (entrada);

                    try {
                    if ((this.tabuleiro.tabuleiro[linhaOrigem -1][Peca.colunaInt(colunaOrig)]).isOcupado()) break;
                    } catch (NumberFormatException n) {
                        System.out.println("Digite um dígito inteiro de 1 a 8.");
                    } catch (ArrayIndexOutOfBoundsException a) {
                        System.out.println("Digite um número de 1 a 8.");
                    }

                } while (true);
            } while (!this.tabuleiro.checarMovimento(linhaOrigem, colunaOrig, linhaDestino, colunaDest));
        }
        
        // Efetivar o movimento
        this.tabuleiro.moverPeca(linhaOrigem, colunaOrig, linhaDestino, colunaDest);
        
        // Verificar se o movimento criou abertura para CHEQUE.
        check = this.isCheck();
        if (check == 1)
            System.out.println("O jogador " + this.jogadorA.getNome() + " está em cheque!" );
        if (check == 2)
            System.out.println("O jogador " + this.jogadorB.getNome() + " está em cheque!" );
        if (check ==  4){
            System.out.println("Derrota do Jogador " + this.jogadorA.getNome());
            return;
        }
        if (check ==  5){
            System.out.println("Derrota do Jogador " + this.jogadorB.getNome());
            return;
        }
            
        // Desenhar o tabuleiro após a jogada.
        this.tabuleiro.desenha();
        this.numJogadas++;
        System.out.println("");
    }
    
    /** Função responsável lógica do Cheque
     *  Varre todo o tabuleiro checando se uma pessoa pode atacar um rei
     *  @return quem está em cheque ou se está em cheque
     */
    private int isCheck(){
        /**/
        boolean reiA = false, reiB = false;
        int linhaDestinoA = 9 , linhaDestinoB = 9;
        char colunaDestinoA = 'x', colunaDestinoB = 'x';
        
        /* Pesquisa no Tabuleiro até achar a posição do Rei e armazena as coordenadas */
        for(int linha = 0; linha < 8 && !(reiA && reiB); linha++)
            for(int coluna = 0; coluna < 8 && !(reiA && reiB); coluna++)
                if(this.tabuleiro.tabuleiro[linha][coluna].isOcupado()){
                    /* Rei do Jogador A*/
                    if(this.tabuleiro.tabuleiro[linha][coluna].desenha() == 'K'){
                        linhaDestinoA = linha;
                        colunaDestinoA = Peca.colunaChar(coluna + 1);
                        reiA = true;
                    }
                    /* Rei do Jogador B*/
                    if(this.tabuleiro.tabuleiro[linha][coluna].desenha() == 'k'){
                        linhaDestinoB = linha;
                        colunaDestinoB = Peca.colunaChar(coluna + 1);
                        reiB = true;
                    }
                }
        
        /* Verifica se não foi achado nenhum dos dois Reis*/
        if(!reiA || !reiB){
            this.setStatus(ESTADO_XEQUE_MATE);
            if(!reiA) return JOGADOR_REI_A;
            if(!reiB) return JOGADOR_REI_B;
        }
        
        
        /* Verifica as posições e determina */ 
        for(int linha = 0; linha < 8 ; linha++)
            for(int coluna = 0; coluna < 8; coluna++)
                if(this.tabuleiro.tabuleiro[linha][coluna].isOcupado()){
                    /* Se a peça for branca, faz a checagem com o rei do jogador B */
                    if("branco".equals(this.tabuleiro.tabuleiro[linha][coluna].peca.getCor()))
                        if(this.tabuleiro.checarMovimento(linha + 1, Peca.colunaChar(coluna + 1),linhaDestinoB + 1 ,colunaDestinoB)){
                            this.setStatus(ESTADO_XEQUE);
                                return CHECK_JOGADOR_B;
                        }
                    
                    /* Se a peça for preta, faz a checagem com o rei do jogador A */
                    if("preto".equals(this.tabuleiro.tabuleiro[linha][coluna].peca.getCor()))
                        if(this.tabuleiro.checarMovimento(linha + 1, Peca.colunaChar(coluna + 1),linhaDestinoA + 1,colunaDestinoA)){
                            this.setStatus(ESTADO_XEQUE);
                                return CHECK_JOGADOR_A;
                        }
                    }
        /* Não foi encontrado nada */
        return NO_CHECK;
    }
    
    /** Função que alterna a vez dos jogadores
     */
    private void alternaVez(){
        if(this.getVezJogador())
            this.setVezJogador(false);
        else
            this.setVezJogador(true);
    }
    
    /** Obtém o status do Jogo
    *   @return status
    */
    private int getStatus() {
        return status;
    }
    /** Configura o status do Jogo
    *   @param status
    */
    private void setStatus(int status) {
        if(status >= 0 && status <= 3)
            this.status = status;
        else
            System.out.println(ERRO_STATUS_JOGO);
    }
    /** Obtém a vez do jogador
    *   @return vezJogador
    */    
    private boolean getVezJogador() {
        return vezJogador;
    }
    
    /** Configura a vez do jogador
    *   @param vezJogador
    */
    private void setVezJogador(boolean vezJogador) {
        if(vezJogador == this.getVezJogador())
            System.out.println("Movimento invalido!");
        else
            this.vezJogador = vezJogador;
    }
    
    private int recebeLinha (Scanner entrada) {
        boolean linhaNaoValida = true;
        int linha = -1;
        
        do {
            System.out.println("Digite o número da linha: ");      
            
            try {
                linha = Integer.parseInt (entrada.nextLine());
                linhaNaoValida = false;
            } catch (NumberFormatException n) {
                System.out.println("Digite apenas um dígito inteiro.");
            }
            
        } while (linhaNaoValida);       
        return linha;
    }
    
    private char recebeColuna (Scanner entrada) {
        boolean colunaNaoValida = true;
        char colunaAux = ' ';
        String coluna;
        
        do {
            System.out.println("Digite o número da coluna: ");
            
            try {
                coluna = entrada.nextLine();
                colunaAux = coluna.charAt(0);
                colunaNaoValida = false;                
            } catch (StringIndexOutOfBoundsException s) {
                System.out.println("Escreva um caractere em letra minúscula");                
            }
            
        } while (colunaNaoValida);        
        return colunaAux;
    }
    
        /** Função que armazena as peças do arquivo no jogo
     *  @param linha linha da peca
     *  @param char coluna da peca
     *  @param peca desenho da peca
     *
     */
    private void armazenaPeca(int linha, char coluna ,char peca){
       
        /* Testar outro metodo depois */
        
        switch(peca){

            case 'T' -> {
                this.tabuleiro.tabuleiro[linha][Peca.colunaInt(coluna)].setPeca(this.jogadorA.getPeca(peca));
                break;
            }
            case 'B' -> {
                this.tabuleiro.tabuleiro[linha][Peca.colunaInt(coluna)].setPeca(this.jogadorA.getPeca(peca));
                break;
            }
            case 'H' -> {
                this.tabuleiro.tabuleiro[linha][Peca.colunaInt(coluna)].setPeca(this.jogadorA.getPeca(peca));
                break;
            }
            case 'R' -> {
                this.tabuleiro.tabuleiro[linha][Peca.colunaInt(coluna)].setPeca(this.jogadorA.getPeca(peca));
                break;
            }
            case 'Q' -> {
                 this.tabuleiro.tabuleiro[linha][Peca.colunaInt(coluna)].setPeca(this.jogadorA.getPeca(peca));
                break;
            }
            case 'P' -> {
                 this.tabuleiro.tabuleiro[linha][Peca.colunaInt(coluna)].setPeca(this.jogadorA.getPeca(peca));
                 break;
            }
                
                
            case 't' -> {
                this.tabuleiro.tabuleiro[linha][Peca.colunaInt(coluna)].setPeca(this.jogadorB.getPeca(peca));
                break;
            }
            case 'b' -> {
                 this.tabuleiro.tabuleiro[linha][Peca.colunaInt(coluna)].setPeca(this.jogadorB.getPeca(peca));
                 break;
            }
            case 'h' -> {
                 this.tabuleiro.tabuleiro[linha][Peca.colunaInt(coluna)].setPeca(this.jogadorB.getPeca(peca));
                 break;
            }
            case 'r' -> {
                 this.tabuleiro.tabuleiro[linha][Peca.colunaInt(coluna)].setPeca(this.jogadorB.getPeca(peca));
                 break;
            }
            case 'q' -> {
                this.tabuleiro.tabuleiro[linha][Peca.colunaInt(coluna)].setPeca(this.jogadorB.getPeca(peca));
                break;
            }
            case 'p' -> {
                this.tabuleiro.tabuleiro[linha][Peca.colunaInt(coluna)].setPeca(this.jogadorB.getPeca(peca));
                break;
            }
            default -> {
                return;
            }
        }
        this.tabuleiro.tabuleiro[linha][Peca.colunaInt(coluna)].setOcupado(true);
    }

    
    
    /** Arquiva as configurações do jogo.
    *   @return true se o usuário deseja arquivar o jogo.
    */
    private boolean arquivar () {                 
        String s;
        Scanner ler = new Scanner(System.in);
        
        System.out.println("Você quer interromper a partida? [S/N]");
        s = ler.next();         
        if ("N".equals(s)) return (false);

        System.out.println("Digite o nome do arquivo txt que será utilizado para salvar: ");
        s = ler.next();   
        
        /* Quando manipular arquivos:
        * Corre risco de comportamento anormal da aplicação.
        * Obrigatório tratamento de exceção.
        */        
        try {
            // Criar arquivo para ser escrito.
            FileOutputStream arquivo = new FileOutputStream (s);
            PrintWriter pr = new PrintWriter (arquivo);
           
            for (int i = 0; i < 8; i++) 
                for (int j = 0; j < 8; j++)
                    pr.println (tabuleiro.tabuleiro[i][j].desenha());
            
            pr.println (jogadorA.getNome());
            pr.println (jogadorB.getNome());
            pr.println (vezJogador);
            
            pr.close();
            arquivo.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever o arquivo.");
        } catch (Exception ex) {
            System.out.println("Erro ao escrever o arquivo.");
        }
        
        return (true);
    }
    
     boolean desarquivar () {  
        String s;
        Scanner ler = new Scanner(System.in);
        System.out.println("Você quer jogar uma partida salva em um arquivo?");
        s = ler.next();         
        if ("N".equals(s)) return (false);
        
        System.out.println("Qual o nome do arquivo no qual a partida está salva?");
        s = ler.next();          
        
        try {
            FileInputStream arquivo = new FileInputStream (s);
            InputStreamReader input = new InputStreamReader (arquivo);
            BufferedReader br =  new BufferedReader (input);
            
            String linha;
            int numLinha = 0; // Armazena em qual linha do ARQUIVO está.
            int numColunaTab = 0; // Armazena qual é a COLUNA do tabuleiro que deve armazenar as peças. 
            int numLinhaTab = 0; // Armazena qual é a LINHA do tabuleiro que deve armazenar as peças.
            
            do {
                numLinha++;
                linha = br.readLine();
                numColunaTab = numLinha % 8;
                numLinhaTab = numLinha / 8 ;
                if (numLinha <= 64){
                    if(numLinha == 64)
                        this.armazenaPeca(7, Peca.colunaChar(numColunaTab),linha.charAt(0));
                    else
                        this.armazenaPeca(numLinhaTab, Peca.colunaChar(numColunaTab),linha.charAt(0));
                }
                
                 switch (numLinha) {
                    case 65 -> jogadorA.setNome (linha);
                    case 66 -> jogadorB.setNome (linha);
                    case 67 -> this.vezJogador = "true".equals(linha);
                    default -> {
                    }
                }
            } while (linha != null);
            
        } catch (IOException e) {
            System.out.println("Não foi encontrado nenhum arquivo ou ele não pode ser aberto.");
        } catch (Exception ex)  {
           System.out.println("Não foi encontrado nenhum arquivo ou ele não pode ser aberto.");
        }
        return (true);        
    } 


}









