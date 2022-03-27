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
    
    /*
    Função de Açao com verificação de colisão v1.0
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
    */
}
