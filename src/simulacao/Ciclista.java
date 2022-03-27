package simulacao;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Ciclista extends Item{
    //private Image imagem;

    public Ciclista(Localizacao localizacao) {
        super(localizacao);
        super.imagem = new ImageIcon(getClass().getResource("Imagens/bicicleta.jpg")).getImage();
    }

    public void executarAcao(Mapa mapa){
        Localizacao destino = getLocalizacaoDestino();
        Localizacao anterior = getLocalizacaoAtual();
        if(destino != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(super.getLocalizacaoDestino());
            while(mapa.getItem(proximaLocalizacao.getX(), proximaLocalizacao.getY()) != null){
                proximaLocalizacao = new Localizacao(anterior.getX(), anterior.getY());
            }
            setLocalizacaoAtual(proximaLocalizacao);
        }
    }
}
