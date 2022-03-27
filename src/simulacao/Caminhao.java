package simulacao;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Caminhao extends Item{
    private boolean mercadoriaPega;

    public Caminhao(Localizacao localizacao) {
        super(localizacao);
        super.imagem = new ImageIcon(getClass().getResource("Imagens/veiculo.jpg")).getImage();
    }
    
    public void executarAcao(Mapa mapa, Mercadoria mercadoria){
        Localizacao destino = getLocalizacaoDestino();
        Localizacao anterior = getLocalizacaoAtual();
        int opcoes = 4;
        Random rand = new Random();
        if(destino != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(super.getLocalizacaoDestino());
            try{
            while(mapa.getItem(proximaLocalizacao.getX(), proximaLocalizacao.getY()) != null && mapa.getItem(proximaLocalizacao.getX(), proximaLocalizacao.getY()) != mercadoria){
                if(rand.nextInt(opcoes) == 4){
                    proximaLocalizacao = new Localizacao(anterior.getX() + 1, anterior.getY());
                } else if(rand.nextInt(opcoes) == 3){
                    proximaLocalizacao = new Localizacao(anterior.getX() - 1, anterior.getY());
                } else if(rand.nextInt(opcoes) == 3){
                    proximaLocalizacao = new Localizacao(anterior.getX(), anterior.getY() + 1);
                } else {
                    proximaLocalizacao = new Localizacao(anterior.getX(), anterior.getY() - 1);
                }
            }
            } catch(ArrayIndexOutOfBoundsException e){
                if(rand.nextInt(opcoes) == 4){
                    proximaLocalizacao = new Localizacao(anterior.getX() + 1, anterior.getY());
                } else if(rand.nextInt(opcoes) == 3){
                    proximaLocalizacao = new Localizacao(anterior.getX() - 1, anterior.getY());
                } else if(rand.nextInt(opcoes) == 3){
                    proximaLocalizacao = new Localizacao(anterior.getX(), anterior.getY() + 1);
                } else {
                    proximaLocalizacao = new Localizacao(anterior.getX(), anterior.getY() - 1);
                }
            } finally{
              setLocalizacaoAtual(proximaLocalizacao);
            }
        }
    }

    public void setMercadoriaPega(boolean mercadoriaPega) {
        this.mercadoriaPega = mercadoriaPega;
    }

    public boolean getMercadoriaPega() {
        return mercadoriaPega;
    }
}
