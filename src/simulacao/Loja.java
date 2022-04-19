package simulacao;

import javax.swing.ImageIcon;
/**
 * Classe para representação das Lojas
 * @author Danilo Aparecido Namitala and Pedro H. Marques Siqueira and Jonas Fernandes dos Reis and Paulo Eduardo Soares Rezende 
 *
 */
public class Loja extends Item {
	/**
	 * Construtor da Loja
	 * Cria a imagem da Loja no mapa, em sua posição fixa declarada.
	 * @param localizacao - uma Localização no mapa
	 */
    public Loja(Localizacao localizacao) {
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource("Imagens/store.jpg")).getImage());
        setLocalizacaoDestino(null);
    }
}
