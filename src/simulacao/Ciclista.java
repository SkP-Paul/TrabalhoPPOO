package simulacao;

import javax.swing.ImageIcon;

/**
 * Classe para representação dos Ciclistas
 *
 * @author Danilo Aparecido Namitala and Pedro H. Marques Siqueira and Jonas
 * Fernandes dos Reis and Paulo Eduardo Soares Rezende
 *
 */
public class Ciclista extends ItemMovel {

    /**
     * Construtor de Ciclista Cria uma imagem de ciclista no mapa na sua posição
     * inicial
     *
     * @param localizacao: uma Localização no mapa
     */
    public Ciclista(Localizacao localizacao) {
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource("Imagens/bicicleta.jpg")).getImage());
    }
}
