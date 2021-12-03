package poo.xadrez;

public class Tabuleiro {
    public Posicao[][] tabuleiro = new Posicao[8][8]; // Índices de 0 a 7.
    
    public Tabuleiro (Peca[] pecasA,Peca[] pecasB) {
        for(int linha = 0; linha < 8; linha++)
            for(int coluna = 0; coluna < 8 ; coluna++)
                this.tabuleiro[linha][coluna] = new Posicao(linha, colunaString(coluna));
        
        this.configuracaoInicial(pecasA, pecasB);
    }
    
    private char colunaString (int coluna) {
        return switch (coluna) {
            case  0 -> 'a';
            case  1 -> 'b';
            case  2 -> 'c';
            case  3 -> 'd';
            case  4 -> 'e';
            case  5 -> 'f';
            case  6 -> 'g';
            case  7 -> 'h';
            default -> 'x';
        };
    }
    
    // Configuração inicial do tabuleiro.
    private void configuracaoInicial (Peca[] pecasA,Peca[] pecasB) {        
        
        for(int linha = 0; linha < 8; linha++)
            for(int coluna = 0; coluna < 8; coluna++)
                switch(linha){
                case 0:
                    /* TorreA1 - CavaloA1 - BispoA1 - DamaA - ReiA - BispoA2 - CavaloA2 - TorreA2*/
                    for (int indicePecasA = 0; indicePecasA < 8; indicePecasA++)
                        this.tabuleiro[linha][indicePecasA].setPeca(pecasA[indicePecasA]);
                    break;
                    
                case 1:
                    /* Conjunto de PeõesA */
                    for (int indicePecasPosicao = 0, indicePecasA = 8 ; indicePecasPosicao < 8; indicePecasPosicao++)
                        this.tabuleiro[linha][indicePecasPosicao].setPeca(pecasA[indicePecasA++]);
                    break;
                    
                case 6:
                    /* Conjunto de PeõesB*/
                    for (int indicePecasPosicao = 0, indicePecasB = 8 ; indicePecasPosicao < 8; indicePecasPosicao++)
                        this.tabuleiro[linha][indicePecasPosicao].setPeca(pecasB[indicePecasB++]);
                    break;
                
                case 7:
                    /* TorreB1 - CavaloB1 - BispoB1 - DamaB - ReiB - BispoB2 - CavaloB2 - TorreB2 */
                    for (int indicePecasB = 0; indicePecasB < 8; indicePecasB++)
                        this.tabuleiro[linha][indicePecasB].setPeca(pecasB[indicePecasB]);
                    break;
            }
    }
    /** Desenha o tabuleiro
     *  Cria a figura de um tabuleiro com as coordenadas
    */
    
    public void desenha() {
        System.out.println("    A B C D E F G H");
        System.out.println("    ---------------");
        for (int i = 0; i < 8; i++) {
            System.out.print((i+1) + " - ");
            for (int j = 0; j < 8; j++) {
                System.out.print(this.tabuleiro[i][j].desenha());
                System.out.print(" ");
            }
            System.out.println("");   
        }
    }   
    
    /** Função responsável pela verificação de toda a movimentação que ocorre dentro do tabuleiro
     *  @param linhaOrigem linha da posicao/peca escolhida
     *  @param colunaOrigem coluna da posicao/peca escolhida
     *  @param linhaDestino linha da posicao destino
     *  @param colunaDestino coluna da posicao destino
     *  @return verdadeiro se pode se movimentar, se não falso.
     */
    public boolean checarMovimento (int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        // Para ser compatível com os índices da matriz.
        linhaOrigem--;
        linhaDestino--;
     
        // Posição inicial a mesma da final.
        if ((linhaOrigem == linhaDestino && colunaOrigem == colunaDestino)){
            System.out.println("Posição inicial é a mesma da final.");
            return (false);
        }
        
        // Checar se o movimento está dentro dos limites do tabuleiro.
        if (((foraDoLimite(linhaDestino) || foraDoLimite(colunaDestino)))){
            System.out.println("Fora dos limites do tabuleiro.");
            return (false);
        }
        
        // Tratar peão quando ele se movimentar na diagonal.
        if (('p' == tabuleiro[linhaOrigem][Peca.colunaInt(colunaOrigem)].peca.desenho()) && ('P' == tabuleiro[linhaOrigem][Peca.colunaInt(colunaOrigem)].peca.desenho())) 
            if (peaoComePeca (linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) return (true);
                        
        // Conferir se o movimento não é válido para a peça específica.
        if (this.tabuleiro[linhaOrigem][Peca.colunaInt(colunaOrigem)].peca.checaMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino) == false) {
            return false;            
        }
        
        // Conferir se tem peças no caminho do movimento, desde que não seja uma peça do tipo Cavalo.
        if (!('H' == tabuleiro[linhaOrigem][Peca.colunaInt(colunaOrigem)].peca.desenho()) && ! ('h' == tabuleiro[linhaOrigem][Peca.colunaInt(colunaOrigem)].peca.desenho()))
            if (!this.caminhoLivre(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) {
                return (false);
            }
        
        // Conferir posição final. Se for peça aliada, o movimento é inválido. 
        if (this.posicaoFinal (linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) {
            System.out.println("Essa posição é de uma peça aliada!");
            return (false);
        }
       
        // Se o movimento for permitido, dispara a movimentação em si.
        return (true);
    }
    
    // Métodos responsáveis pela checagem do movimento em relação à outras peças. 
    // Iterar sobre todas as posições que fazem parte do caminho.
    public boolean caminhoLivre (int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {             
        // Movimento horizontal.
        if (linhaOrigem == linhaDestino) { 
            // É importante saber qual é a menor coluna para fazer o loop verificando as posições.
            char menorColuna = colunaOrigem;
            char maiorColuna = colunaDestino;
            
            // Qual é a menor coluna.
            if (colunaOrigem > colunaDestino) {
                menorColuna = colunaDestino;
                maiorColuna = colunaOrigem;
            }
            
            for (int i = Peca.colunaInt(menorColuna) + 1; i < Peca.colunaInt(maiorColuna); i++) // + 1 para não contar a própria peça.
                if (this.tabuleiro[linhaOrigem][i].isOcupado()) return (false);          
        }
        
        // Movimento vertical.
        if (colunaOrigem == colunaDestino) {
            int menorLinha = linhaOrigem;
            int maiorLinha = linhaDestino;
            
            // Qual é a menor linha.
            if (linhaOrigem > linhaDestino) {
                menorLinha = linhaDestino;
                maiorLinha = linhaOrigem;
            }
            
            for (int i = menorLinha + 1; i < maiorLinha; i++) 
                if (this.tabuleiro[i][Peca.colunaInt(colunaDestino)].isOcupado()) return false;
        }
        
        // Movimento diagonal.
        int i = linhaOrigem, j = Peca.colunaInt(colunaOrigem);
        // Caso - -
        if (linhaOrigem > linhaDestino && Peca.colunaInt(colunaOrigem) > Peca.colunaInt(colunaDestino))
            while (true) {
                i--;
                j--;
                if(i == linhaDestino && j == Peca.colunaInt(colunaDestino)) break;
                if (this.tabuleiro[i][j].isOcupado()) return (false);
                if (i == 0 || j == 0) break;
            } 

        // Caso - +
        if (linhaOrigem > linhaDestino && Peca.colunaInt(colunaOrigem) < Peca.colunaInt(colunaDestino))
            while (true) {
                i--;
                j++;
                if(i == linhaDestino && j == Peca.colunaInt(colunaDestino)) break;
                if (this.tabuleiro[i][j].isOcupado()) return (false);
                if (i == 0 || j == 7) break;
            }
        
        // Caso + -
        if (linhaOrigem < linhaDestino && Peca.colunaInt(colunaOrigem) > Peca.colunaInt(colunaDestino))
            while (true) {
                i++;
                j--;
                if(i == linhaDestino && j == Peca.colunaInt(colunaDestino)) break;
                if (this.tabuleiro[i][j].isOcupado()) return (false);
                if (i == 7 || j == 0) break;
            }
        
        // Caso + +
        if (linhaOrigem < linhaDestino && Peca.colunaInt(colunaOrigem) < Peca.colunaInt(colunaDestino)) 
            while (true) {
                i++;
                j++;
                if (i == linhaDestino && j == Peca.colunaInt(colunaDestino)) break;
                if (this.tabuleiro[i][j].isOcupado()) return (false);
                if (i == 7 || j == 7) break;
            }         
        return (true);        
    }
       
    /** Função que verifica se a posição final tem peça aliada
     *  @param linhaOrigem linha da peça que vai andar
     *  @param colunaOrigem coluna da peça que vai andar
     *  @param linhaDestino linha da peca que sera testada
     *  @param colunaDestino coluna da peça que sera testada
     *  @return true se for igual, se não false
     */ 
    private boolean posicaoFinal (int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        if (this.tabuleiro[linhaDestino][Peca.colunaInt(colunaDestino)].isOcupado()) 
            if(this.tabuleiro[linhaOrigem][Peca.colunaInt(colunaOrigem)].peca.getCor().equals(this.tabuleiro[linhaDestino][Peca.colunaInt(colunaDestino)].peca.getCor()))
                return true;
        return false;
    }
    
    
    public void moverPeca (int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        linhaOrigem--;
        linhaDestino--;
        
        // Armazenar a peça na posição nova.
        if (this.tabuleiro[linhaDestino][Peca.colunaInt(colunaDestino)].isOcupado()) // Se houver peça inimiga.
            this.tabuleiro[linhaDestino][Peca.colunaInt(colunaDestino)].peca.setCapturado(true);
        else 
            this.tabuleiro[linhaDestino][Peca.colunaInt(colunaDestino)].setOcupado(true); // Se a posição de destino não estiver ocupada.
        
        this.tabuleiro[linhaDestino][Peca.colunaInt(colunaDestino)].setPeca(this.tabuleiro[linhaOrigem][Peca.colunaInt(colunaOrigem)].getPeca());
        this.tabuleiro[linhaOrigem][Peca.colunaInt(colunaOrigem)].setOcupado(false);
    }
    
    /** Função responsável por determinar se a linha está fora dos limites do tabuleiro
     * @param linha linha para check
     * @return true se for for dos limites, se não false
     */
    private boolean foraDoLimite (int linha) {
        return linha < 0 || linha > 7;
    }
    
    
    /** Função responsável por determinar se a coluna está fora dos limites do tabuleiro
     * @param coluna linha para check
     * @return true se for for dos limites, se não false
     */
    private boolean foraDoLimite (char coluna) {
        return coluna != 'a' && coluna != 'b' && coluna != 'c' && coluna != 'd' && coluna != 'e' && coluna != 'f' && coluna != 'g' && coluna != 'h';
    }

    
    /** Função que trata a captura de uma peça adversária pelo Peão, que faz movimentos diagonais.
    */
    private boolean peaoComePeca (int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        // Tratar caso de peão capturar peça na diagonal dele.
        
        // Em caso de peça preta.
        if ("preto".equals(tabuleiro[linhaOrigem][Peca.colunaInt(colunaOrigem)].getCor())) {
            // Caso ++
            if (linhaOrigem == linhaDestino + 1 && Peca.colunaInt(colunaOrigem) == Peca.colunaInt(colunaOrigem) + 1) {
                // Se tiver peça inimiga.
                if (tabuleiro[linhaDestino][Peca.colunaInt(colunaDestino)].isOcupado() && "branco".equals(tabuleiro[linhaDestino][Peca.colunaInt(colunaDestino)].getCor())) return (true);                
                // Se tiver peça amiga ou nenhuma peça.
                return (false);
            }
            
            // Caso -+
            if (linhaOrigem == linhaDestino - 1 && Peca.colunaInt(colunaOrigem) == Peca.colunaInt(colunaOrigem) + 1) {
                // Se tiver peça inimiga.
                if (tabuleiro[linhaDestino][Peca.colunaInt(colunaDestino)].isOcupado() && "branco".equals(tabuleiro[linhaDestino][Peca.colunaInt(colunaDestino)].getCor())) return (true);
                // Se tiver peça amiga ou nenhuma peça.
                return (false);
            }
        }
        
        // Em caso de peça branca.
        if ("branco".equals(tabuleiro[linhaOrigem][Peca.colunaInt(colunaOrigem)].getCor())) {
            // Caso +-
            if (linhaOrigem == linhaDestino + 1 && Peca.colunaInt(colunaOrigem) == Peca.colunaInt(colunaOrigem) - 1) {
                // Se tiver peça inimiga.
                if (tabuleiro[linhaDestino][Peca.colunaInt(colunaDestino)].isOcupado() && "preto".equals(tabuleiro[linhaDestino][Peca.colunaInt(colunaDestino)].getCor())) return (true);                
                // Se tiver peça amiga ou nenhuma peça.
                return (false);
            }            
            
            // Caso --
            if (linhaOrigem == linhaDestino - 1 && Peca.colunaInt(colunaOrigem) == Peca.colunaInt(colunaOrigem) - 1) {
                // Se tiver peça inimiga.
                if (tabuleiro[linhaDestino][Peca.colunaInt(colunaDestino)].isOcupado() && "preto".equals(tabuleiro[linhaDestino][Peca.colunaInt(colunaDestino)].getCor())) return (true);                
                // Se tiver peça amiga ou nenhuma peça.
                return (false);
            }  
        }          
            
        return (false);
    } 
}