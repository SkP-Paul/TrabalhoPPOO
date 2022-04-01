package simulacao;

import java.awt.Image;
import java.util.Random;

import javax.swing.plaf.synth.SynthStyleFactory;

/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Item {
    private Localizacao localizacaoAtual;
    private Localizacao localizacaoDestino;
    public Image imagem;

    public Item(Localizacao localizacao) {
        this.localizacaoAtual = localizacao;
        localizacaoDestino = null;
    }

    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }
    
    public Image getImagem(){
        return imagem;
    }

    protected void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    protected void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }

    public void destinoAleatorio(int largura, int altura){
        Random rand = new Random();
        setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
    }
    
    public void executarAcao(Mapa mapa){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(localizacaoDestino);
            Item i = mapa.getItem(proximaLocalizacao);
            if( i == null) {
                setLocalizacaoAtual(proximaLocalizacao);
            } else {
                for(int x = -1; x <= 1 || i != null; x++){
                    for(int y = -1; y <= 1 || i != null; y++){
                        int dX = localizacaoAtual.getX()+x;
                        int dY = localizacaoAtual.getY()+y;
                        proximaLocalizacao = new Localizacao(dX,dY);
                        try {
                            i = mapa.getItem(proximaLocalizacao); 
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(e.getMessage());
                            proximaLocalizacao = localizacaoAtual;
                            i = mapa.getItem(proximaLocalizacao);
                        }
                        
                    }
                }
                if( i == null) {
                    setLocalizacaoAtual(proximaLocalizacao);
                }
            }
        }
    } 
    
    public boolean chegouDestino(){
        return getLocalizacaoAtual() == getLocalizacaoDestino();
    }
}
