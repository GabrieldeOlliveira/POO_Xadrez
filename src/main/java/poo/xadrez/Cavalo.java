package poo.xadrez;

public class Cavalo extends Peca {
    
    
     /** Construtor da Classe Cavalo
     *  @param cor cor da peca
     */
    public Cavalo (String cor) {
        this.cor = cor;
        this.capturado = false;
    }
     /** Função que desenha a peça
     *  @return desenho da peca
     */
    @Override
    public char desenho () {
        return ("branco".equals(this.getCor ()) ? 'H' : 'h');                
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
        /* Quando ocorre o incremento ou decremento de uma (01) unidade na coluna e 
        a linha incrementa ou decrementa duas (02) unidades, o movimento é válido. O 
        mesmo ocorre da forma inversa. */
        int colunaOrigemInt = colunaInt(colunaOrigem);
        int colunaDestinoInt = colunaInt(colunaDestino);
        
        if (linhaDestino == linhaOrigem + 2 || linhaDestino == linhaOrigem - 2) 
            if (colunaDestinoInt == colunaOrigemInt - 1 || colunaDestinoInt == colunaOrigemInt + 1) return (true);
        
        if (linhaDestino == linhaOrigem - 1 || linhaDestino == linhaOrigem + 1) 
            if (colunaDestinoInt == colunaOrigemInt + 2 || colunaDestinoInt == colunaOrigemInt - 2) return (true);       
        
        return (false);
    }
}