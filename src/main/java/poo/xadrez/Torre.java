package poo.xadrez;

public class Torre extends Peca {
    
    /** Construtor da Classe Torre
     *  @param cor cor da peca
     */
    public Torre (String cor) {
        this.cor = cor;
        this.capturado = false;
    }
    
    /** Função que desenha a peça
    *  @return desenho da peca
    */
    @Override
    public char desenho () {
        return ("branco".equals(this.getCor()) ? 'T' : 't');
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
        return (linhaDestino == linhaOrigem || colunaInt(colunaDestino) == colunaInt(colunaOrigem));
    }  
}
