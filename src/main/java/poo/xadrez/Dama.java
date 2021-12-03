package poo.xadrez;

public class Dama extends Peca {
    
    /** Construtor da Classe Dama
     *  @param cor cor da peca
     */
    public Dama (String cor) {
        this.cor = cor;
        this.capturado = false;
    }
    
    /** Função que desenha a peça
    *  @return desenho da peca
    */
    @Override
    public char desenho () {
        return ("branco".equals(this.getCor ()) ? 'Q' : 'q');
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
        if((linhaOrigem == linhaDestino && colunaOrigem != colunaDestino) || (linhaOrigem != linhaDestino && colunaOrigem == colunaDestino) )
            return true;
        
    int eixoX, eixoY;
    
    eixoX = linhaDestino - linhaOrigem;
    eixoY = Peca.colunaInt(colunaDestino) - Peca.colunaInt(colunaOrigem);
    
    if(eixoX < 0) eixoX = eixoX * (-1);
    
    if(eixoY < 0) eixoY = eixoY * (-1);
    
    if(eixoX == eixoY) return true;
    
    return false;
    }
}
