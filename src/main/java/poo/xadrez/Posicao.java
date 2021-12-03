package poo.xadrez;

public class Posicao {
    // Atributos
    private int linha;
    private char coluna;
    private String cor;
    private boolean ocupado;
    public Peca peca;
    
    /** Construtor da classe Posicao
     *  @param linha linha da posicao
     *  @param coluna coluna da posicao
     */
    public Posicao (int linha, char coluna) {
        this.coluna = coluna;
        this.linha = linha;
        this.setOcupado(false);
        this.cor = calculaCor();     
    }
    
    /** Função de Desenho da Posicao
     *  @return desenho da Peca ou da posicao
     */
    public char desenha(){
        if(this.isOcupado())
            return this.peca.desenho();
        else 
           return "branco".equals(this.getCor()) ? 'X' : 'x';
    }
    
    /** Calcula qual vai ser a cor da Posicao
     * @return branco se for par, se não preto
     */
    private String calculaCor() {
        // [A,1] = Branco, [A,2] = Preto 
        return (((this.linha + this.coluna) % 2) == 0 ? "branco" : "preto");        
    }

    /** Obtém a cor da Posicao
     *  @return cor da posicao
     */
    public String getCor () {
        return (this.cor);
    }
    
    /** Verifica status de Ocupação da Posicao
     *  @return true se estiver ocupado, se não false
     */
    public boolean isOcupado() {
        return ocupado;
    }

    /** Configura o status de Ocupado da Posição
     *  @param ocupado status de ocupado
     */
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
    /** Configura a Peca da Posicao
     *  @param peca peca a ser inserida
     */
    public void setPeca (Peca peca) {
        this.peca = peca;
        this.setOcupado(true);
    }
    
    /** Obtém a peca da Posicao
     *  @return peca da posicao
     */
    public Peca getPeca () {
        return (this.peca);
    }
}