package simulacao;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Mercadoria extends Item{
    private int loja;

    public Mercadoria(Localizacao localizacao, int loja) {
        super(localizacao);
        this.loja = loja;
        if(loja == 1){
            super.imagem = new ImageIcon(getClass().getResource("Imagens/pacoteML.png")).getImage();
        } else {
            super.imagem = new ImageIcon(getClass().getResource("Imagens/pacoteCB.png")).getImage();
        }
    }

    public void executarAcao(){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(super.getLocalizacaoDestino());
            setLocalizacaoAtual(proximaLocalizacao);
        }
    }
}
