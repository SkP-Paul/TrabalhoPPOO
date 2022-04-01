package simulacao;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
	private List <Caminhao> veiculos;
	private List <Pedestre> pedestres;
	private List <Ciclista> ciclistas;
	private List <Mercadoria> mercadorias;
	private List <Loja> lojas;
	private static final int qtdLojas = 1;
	private static final int qtdInicMerc = 1;
	private static final int qtdCiclistas = 1;
	private static final int qtdPedestres = 1;
	private static final int qtdCaminhoes = 1;
	private int contador = 0;
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    
    public Simulacao() {
        Random rand = new Random();
        mapa = new Mapa();
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();
        veiculos = new ArrayList<Caminhao>();
        pedestres = new ArrayList<Pedestre>();
        ciclistas = new ArrayList<Ciclista>();
        mercadorias = new ArrayList<Mercadoria>();
        lojas = new ArrayList<Loja>();
        for (int i =0; i< qtdLojas; i++) {
        	Loja loja = new Loja(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
        	lojas.add(loja);
        	mapa.adicionarItem(loja);
        }
        
        for (int i=0; i<qtdCiclistas; i++) {
        	Ciclista ciclista = new Ciclista(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
        	ciclista.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
        	ciclistas.add(ciclista);
        	mapa.adicionarItem(ciclista);
        }
        for (int i=0; i<qtdCaminhoes; i++) {
        	Caminhao caminhao = new Caminhao(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
        	veiculos.add(caminhao);
        }
        for (int i=0; i<qtdPedestres; i++) {
        	Pedestre pedestre = new Pedestre(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
        	pedestre.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
        	pedestres.add(pedestre);
        }
        
        for (int i=0; i<qtdInicMerc; i++) {
        	Mercadoria mercadoria = new Mercadoria(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)),lojas.get(rand.nextInt(lojas.size())));
        	mercadorias.add(mercadoria);
        	Caminhao c = veiculos.get(rand.nextInt(veiculos.size()));
        	c.addMercadoria(mercadoria);
        	mapa.adicionarItem(mercadoria);
        }
        
        janelaSimulacao = new JanelaSimulacao(mapa);
    }
    
    public void executarSimulacao(int numPassos){
        janelaSimulacao.executarAcao();
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            for (Caminhao caminhao : veiculos) {
            	mapa.adicionarItem(caminhao);
            }
            for (Ciclista ciclista : ciclistas) {
            	mapa.adicionarItem(ciclista);
            }
            for (Pedestre pedestre : pedestres) {
            	mapa.adicionarItem(pedestre);
            }
            for (Loja loja : lojas) {
            	mapa.adicionarItem(loja);
            }
            for (Mercadoria mercadoria : mercadorias) {
            	mapa.adicionarItem(mercadoria);
            }
            if (contador >= 10) {
            	criarNovaMercadoria();
            	contador=0;
            }
            esperar(100);
        }        
    }

	private void executarUmPasso() {
        Random rand = new Random();
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();
        for (Caminhao veiculo : veiculos) {
        	mapa.removerItem(veiculo);
        	if (veiculo.chegouDestino()) {
        		if (veiculo.estaCarregado()){
        			veiculo.descarregar();
        		} else {
        			Mercadoria carga = veiculo.carregar();
                    mercadorias.remove(carga);
                    mapa.removerItem(carga);
        		}
        	}
        	veiculo.executarAcao();
        	mapa.adicionarItem(veiculo);
        }
        
        for (Ciclista c : ciclistas) {
        	mapa.removerItem(c);
        	if(c.chegouDestino()){
                c.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
            }
            c.executarAcao();
            mapa.adicionarItem(c);
        }
        for (Pedestre p : pedestres) {
        	mapa.removerItem(p);
        	if(p.chegouDestino()){
                p.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
            }
            p.executarAcao();
            mapa.adicionarItem(p);
        }
        

        janelaSimulacao.executarAcao();
    }
    
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
            contador++;
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    private void criarNovaMercadoria() {
    	Random rand = new Random();
    	int altura = mapa.getAltura();
    	int largura = mapa.getLargura();
    	Mercadoria mercadoria = new Mercadoria(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)),lojas.get(rand.nextInt(lojas.size())));
    	mercadorias.add(mercadoria);
    	Caminhao c = veiculos.get(rand.nextInt(veiculos.size()));
    	c.addMercadoria(mercadoria);
    	mapa.adicionarItem(mercadoria);
    }
}
