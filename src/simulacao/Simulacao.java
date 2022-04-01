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
	private static final int qtdCiclistas = 10;
	private static final int qtdPedestres = 10;
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
        	ciclista.destinoAleatorio(largura, altura);
        	ciclistas.add(ciclista);
        	mapa.adicionarItem(ciclista);
        }
        for (int i=0; i<qtdCaminhoes; i++) {
        	Caminhao caminhao = new Caminhao(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
        	veiculos.add(caminhao);
        }
        for (int i=0; i<qtdPedestres; i++) {
        	Pedestre pedestre = new Pedestre(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
        	pedestre.destinoAleatorio(largura, altura);
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
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();

        for (Ciclista c : ciclistas) {
        	mapa.removerItem(c);
        	if(c.chegouDestino()){
                c.destinoAleatorio(largura, altura);
            }
            c.executarAcao(mapa);
            mapa.adicionarItem(c);
        }
        System.out.println("ciclista ok");
        for (Pedestre p : pedestres) {
        	mapa.removerItem(p);
        	if(p.chegouDestino()){
                p.destinoAleatorio(largura, altura);
            }
            p.executarAcao(mapa);
            mapa.adicionarItem(p);
        }
        System.out.println("pedestre ok");
        for (Caminhao veiculo : veiculos) {
            if(mapa.getItem(veiculo.getLocalizacaoAtual()) instanceof Caminhao){
        	    mapa.removerItem(veiculo);
            }
        	if (veiculo.chegouDestino()) {
        		if (veiculo.estaCarregado()){
        			veiculo.descarregar();
        		} else {
        			Mercadoria carga = veiculo.carregar();
                    mercadorias.remove(carga);
                    mapa.removerItem(carga);
        		}
        	}
        	veiculo.executarAcao(mapa);
            if(mapa.getItem(veiculo.getLocalizacaoAtual()) instanceof Loja == false){
        	    mapa.adicionarItem(veiculo);
            }
        }
        System.out.println("carro ok");
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
