package poo.xadrez;


/** Classe Jogador
* @author Gabriel Givigi de Oliveira
* @author Paula Caires Silva
*/
public class Jogador {
    
    /* Constantes */
    private final static int
            MAX_PECAS = 16;
    
    
    /* num de pecas Vivas */
    
    private int numTorre = 0;
    private int numCavalo = 0;
    private int numBispo = 0;
    private int numPeao = 8;
  
    // Atributos
    private String nome;
    
    /* Conjunto de Peças usadas por cada jogador.
    */
    public Peca[] pecas = new Peca[MAX_PECAS];
    
    /** Construtor jogador
    *  Esse construtor 
    * @param nome, nome do Gogador
    * @param pecas, pecas do jogador
    */
    public Jogador(String nome,
                    Peca pecas[]) {
        /* Nome */
        this.setNome(nome);
      
        /* Peças */
        this.setPecas(pecas);
    }   
    
    /* Métodos Funcionais */
    /** Contabiliza o número de peças vivas, e quais são elas.
     * @return pecasVivas, numero de peças vivas
    */
    private int pecasVivas(){
        int pecasVivas = 0;

        for(int i = 0; i < MAX_PECAS; i++)
            if(!this.pecas[i].getCapturado()){
                this.pecas[i].desenho();
               pecasVivas++;
            }
        return pecasVivas;        
    }
    
    
    /** Obtém nome 
     * @return nome, nome da classe Jogador
     */
    public String getNome() {
        return nome;
    }
    /** Configura as peças do Jogador 
     *  @param pecas Pecas a serem inseridas
     */
    public void setPecas(Peca[] pecas) {
        this.pecas = pecas;
    }
    
    /** Configura nome
     * @param nome, nome da classe jogador
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    /*** Obtém a Peca baseado no desenho
     *      @param peca desenho peca
     */
    
    public Peca getPeca(char peca){
        
        switch(peca){
            /* Torre */
            case 'T':
                if(this.numTorre == 0){
                    this.numTorre++;
                    return this.pecas[0];
                } else
                    return this.pecas[7];
            case 't':
                if(this.numTorre == 0){
                    this.numTorre++;
                    return this.pecas[0];
                } else
                    return this.pecas[7];
                
            /* Cavalo */
            case 'H':
                if(this.numCavalo == 0){
                    this.numCavalo++;
                    return this.pecas[1];
                } else
                    return this.pecas[6];
            case 'h':
                if(this.numCavalo == 0){
                    this.numCavalo++;
                    return this.pecas[1];
                } else
                    return this.pecas[6];
                
            /* Bispo */
            case 'B':
                if(this.numBispo == 0){
                    this.numBispo++;
                    return this.pecas[2];
                } else
                    return this.pecas[5];
            case 'b':
                if(this.numBispo == 0){
                    this.numBispo++;
                    return this.pecas[2];
                } else
                    return this.pecas[5];
            
            /* Rei */
            case 'R':
                return this.pecas[4];
            case 'r':
                return this.pecas[4];
                
            /* Dama */
            case 'Q':
                return this.pecas[3];
            case 'q':
                return this.pecas[3];
            
            /* Peao */    
            case 'P':
                return this.pecas[this.numPeao++];
            case 'p':
                return this.pecas[this.numPeao++];
            default:
                return null;
        }
    }
}

