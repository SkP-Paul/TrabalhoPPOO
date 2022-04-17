package simulacao;

import javax.swing.ImageIcon;

public class Loja extends Item {
	/**
	 * Construtor da Loja
	 * Cria a imagem da Loja no mapa, em sua posição fixa declarada.
	 * @param localizacao
	 */
    public Loja(Localizacao localizacao) {
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource("Imagens/store.jpg")).getImage());
        setLocalizacaoDestino(null);
    }
}
