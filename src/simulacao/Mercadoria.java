package simulacao;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Mercadoria extends Item{
    private Loja loja;

    public Mercadoria(Localizacao localizacao, Loja loja) {
        super(localizacao);
        this.setLoja(loja);
        //if(loja == 1){
        super.imagem = new ImageIcon(getClass().getResource("Imagens/pacoteML.png")).getImage();
        super.setLocalizacaoDestino(loja.getLocalizacaoAtual());
        //} else {
        //    super.imagem = new ImageIcon(getClass().getResource("Imagens/pacoteCB.png")).getImage();
        //}
    }

    public void executarAcao(){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(super.getLocalizacaoDestino());
            setLocalizacaoAtual(proximaLocalizacao);
        }
    }

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}
}
