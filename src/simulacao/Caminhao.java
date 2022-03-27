package simulacao;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Caminhao extends Item{
    private List<Mercadoria> mercadorias;
    private boolean carga;
    public Caminhao(Localizacao localizacao) {
        super(localizacao);
        super.imagem = new ImageIcon(getClass().getResource("Imagens/veiculo.jpg")).getImage();
        mercadorias = new ArrayList<Mercadoria>();
        carga = false;
    }
    
    public void executarAcao(){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(super.getLocalizacaoDestino());
            setLocalizacaoAtual(proximaLocalizacao);
        }
    }
    
    public void addMercadoria(Mercadoria m) {
    	mercadorias.add(m);
    	if (this.getLocalizacaoDestino()==null) {
    		this.setLocalizacaoDestino(m.getLocalizacaoAtual());
    	}
    }
    
    public void removeMercadoria() {
    	mercadorias.remove(0);
    }
    
    public Mercadoria getProxMercadoria() {
    	Mercadoria m = null;
    	if (mercadorias.size()>0) {
    		m = mercadorias.get(0);
    	}
    	return m;
    }
    public boolean getCarga() {
    	return carga;
    }
    
    public void setCarga(boolean bool) {
    	carga = bool;
    }
    
    /*
        Função de Açao com verificação de colisão v1.0
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
    */
}