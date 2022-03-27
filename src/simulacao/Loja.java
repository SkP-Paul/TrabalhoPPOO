package simulacao;

import javax.swing.ImageIcon;

public class Loja extends Item {
	public Loja(Localizacao loc) {
		super(loc);
		super.imagem = new ImageIcon(getClass().getResource("Imagens/store.jpg")).getImage();
		setLocalizacaoDestino(null);
	}
}
