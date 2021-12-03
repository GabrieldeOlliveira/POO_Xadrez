package poo.xadrez;

public class Peao extends Peca {
    /* Atributo de Primeira Jogada */
    private boolean primeiraVez;


    /** Construtor da Classe Peao
     * @param cor cor da peca
     */
    public Peao (String cor) {
        this.cor = cor;
        this.capturado = false;
        this.setPrimeiraVez(true);
    }
    
    /** Função que desenha a Peca
     *  @return desenho da peca
     */
    @Override
    public char desenho () {
        return ("branco".equals(this.getCor ()) ? 'P' : 'p');                
    }
    
    /** Função que verifica se o movimento da Peca é válido
     * @param linhaOrigem linha original da peca
     * @param colunaOrigem coluna original da peca
     * @param linhaDestino linha destino da peca
     * @param colunaDestino coluna destino da peca
     */
    @Override
    public boolean checaMovimento (int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        int linhaMaior, linhaMenor;
        if(linhaOrigem > linhaDestino){
            linhaMaior = linhaOrigem;
            linhaMenor = linhaDestino;
        } else{
            linhaMaior = linhaDestino;
            linhaMenor = linhaOrigem;
        }        
      
        /* Verifica se é a primeira vez, se for a diferença só pode entre 1 e 2
        */  
        if(this.isPrimeiraVez())
            if((linhaMaior - linhaMenor) <= 2 && colunaOrigem == colunaDestino){
                this.setPrimeiraVez(false);
                return true;
            }    
        else
            if((linhaMaior - linhaMenor) <= 1 && colunaOrigem == colunaDestino){
                this.setPrimeiraVez(false);
                return true;
            }
        return false;
    }
    
    // Tratar quando o peão captura uma peça, na diagonal.
    public void peaoComePeca () {
        
    }
    
    /** Verifica se é o primeiro movimento da Peca
     *  @return true = primeira vez, se não false
     */
    public boolean isPrimeiraVez() {
        return primeiraVez;
    }

    /** Configura o atributo Primeira Vez
     *  @param primeiraVez status do atributo primeira vez
     */
    public void setPrimeiraVez(boolean primeraVez) {
        this.primeiraVez = primeraVez;
    }
}

