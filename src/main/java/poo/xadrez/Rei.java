package poo.xadrez;

public class Rei extends Peca {
    
    /** Construtor da Classe Rei
     *  @param cor cor da peca
     */
    public Rei (String cor) {
        this.cor = cor;
        this.capturado = false;
    }
    
    /** Função que desenha a peça
    *  @return desenho da peca
    */
    @Override
    public char desenho () {
        return ("branco".equals(this.getCor ()) ? 'K' : 'k');                
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
        if (linhaDestino == linhaOrigem - 1 || linhaDestino == linhaOrigem || linhaDestino == linhaOrigem + 1) 
            if (colunaInt(colunaDestino) == colunaInt(colunaOrigem) - 1 || colunaInt(colunaDestino) == colunaInt(colunaOrigem) || colunaInt(colunaDestino) == colunaInt(colunaOrigem) + 1) return (true);
        
        return (false);
    }    
}
