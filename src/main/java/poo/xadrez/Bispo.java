package poo.xadrez;

public class Bispo extends Peca { 
    
     /** Construtor da Classe Bispo
     *  @param cor cor da peca
     */
    public Bispo (String cor) {
        this.cor = cor;
        this.capturado = false;        
    }
    
    /** Função que desenha a peça
    *  @return desenho da peca
    */
    @Override
    public char desenho () {
        return ("branco".equals(this.getCor ()) ? 'B' : 'b');                
    }

    /** Função responsável pela verificação da movimentacao da peca
     *  @param linhaOrigem linha da posicao/peca escolhida
     *  @param colunaOrigem coluna da posicao/peca escolhida
     *  @param linhaDestino linha da posicao destino
     *  @param colunaDestino coluna da posicao destino
     *  @return verdadeiro se pode se movimentar, se não falso.
     */
    @Override
    public boolean checaMovimento (int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        int colunaOrigemInt = colunaInt(colunaOrigem);
        int colunaDestinoInt = colunaInt(colunaDestino);
        int i = linhaOrigem, j = colunaOrigemInt;
        
        /* Mesma posição */
        //if (linhaOrigem ==)
        
        // Movimentação diagonal.
        // Caso - -
        if (linhaOrigem > linhaDestino && colunaOrigemInt > colunaDestinoInt)
            while (true) {
                i--;
                j--;
                if (i == linhaDestino && j == colunaDestinoInt) return (true);
                if (i == 1 || j == 1) return (false);
            }
       
        // Caso - +
        if (linhaOrigem > linhaDestino && colunaOrigemInt < colunaDestinoInt)
            while (true) {
                i--;
                j++;
                if (i == linhaDestino && j == colunaDestinoInt) return (true);
                if (i == 1 || j == 8) return (false);
            }
        
        // Caso + -
        if (linhaOrigem < linhaDestino && colunaOrigemInt > colunaDestinoInt)
            while (true) {
                i++;
                j--;
                if (i == linhaDestino && j == colunaDestinoInt) return (true);
                if (i == 8 || j == 1) return (false);
            }
                    
        // Caso + +
        if (linhaOrigem < linhaDestino && colunaOrigemInt < colunaDestinoInt) 
            while (true) {
                i++;
                j++;
                if (i == linhaDestino && j == colunaDestinoInt) return (true);
                if (i == 8 || j == 8) return (false);
            }
        return (false);
    }
}