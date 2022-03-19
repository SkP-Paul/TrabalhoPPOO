package simulacao;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Pedestre extends Item{
    //private Image imagem;

    public Pedestre(Localizacao localizacao) {
        super(localizacao);
        super.imagem = new ImageIcon(getClass().getResource("Imagens/pessoa.png")).getImage();
    }

    public void executarAcao(){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(super.getLocalizacaoDestino());
            setLocalizacaoAtual(proximaLocalizacao);
        }
    }
}
