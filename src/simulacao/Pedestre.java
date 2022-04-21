package simulacao;

import javax.swing.ImageIcon;

/**
 * Classe para montagem dos Pedestres
 *
 * @author Danilo Aparecido Namitala and Pedro H. Marques Siqueira and Jonas
 * Fernandes dos Reis and Paulo Eduardo Soares Rezende
 */
public class Pedestre extends ItemMovel {

    /**
     * Construtor do Pedestre
     *
     * @param localizacao: Posição inicial de um Pedestre
     */
    public Pedestre(Localizacao localizacao) {
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource("Imagens/pessoa.png")).getImage());
    }
}
