package poo.xadrez;

public abstract class Peca {
    /*
    * Interface para os métodos:
    * desenha e checaMovimento.
    */    
    protected abstract char desenho ();   
    
    /** Checa se o movimento que o usuário deseja fazer é válido para a peça.
    * @param linhaOrigem Linha onde a peça está.
    * @param colunaOrigem Coluna onde a peça está.
    * @param linhaDestino Linha onde o usuário deseja colocar a peça.
    * @param colunaDestino Coluna onde o usuário deseja colocar a peça.
    * @return 1 (um) se movimento válido. Caso contrário, retorna 0 (zero).
    */
    protected abstract boolean checaMovimento (int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino);
   
    // Atributos e métodos comuns a todas as peças
    protected boolean capturado; 
    protected String cor;
    
    
    /** Função que converte a Coluna de Char para Int
     *  @param coluna char da coluna
     *  @return coluna int da coluna
     */
    static public int colunaInt (char coluna) {
        return switch (coluna) {
            case 'a' -> 0;
            case 'b' -> 1;
            case 'c' -> 2;
            case 'd' -> 3;
            case 'e' -> 4;
            case 'f'-> 5;
            case 'g' -> 6;
            case 'h' -> 7;
            default -> -1;
        };
    }
    
      /** Função que converte a Coluna de Int para Char
     *  @param coluna char da coluna
     *  @return coluna int da coluna
     */
    static public char colunaChar (int coluna) {
        return switch (coluna) {
            case 1 -> 'a';
            case 2 -> 'b';
            case 3 -> 'c';
            case 4 -> 'd';
            case 5 -> 'e';
            case 6 -> 'f';
            case 7 -> 'g';
            case 8 -> 'h';
            case 0 -> 'h';
            default -> 'x';
        };
    } 
    
    /** Obtém a cor da Peca
     *  @return cor da peca
     */
    protected String getCor () {
        return this.cor;
    }
      
    /** Obtém status de captura da peca 
     *  @return true = capturado, se não false
     */
    public boolean getCapturado () {
        return this.capturado;
    }
    
    /** Configura status de Captura da peca
     * @param capturado status da peca
     **/
    public void setCapturado (boolean capturado) {
        this.capturado = capturado;
    } 
}
