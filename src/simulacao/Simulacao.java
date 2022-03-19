package simulacao;

import java.util.Random;
/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
    private Item veiculo;
    private Item veiculo2;
    private Item ciclista;
    private Item pedestre;
    private Item mercadoria;
    private Item mercadoria2;
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    
    public Simulacao() {
        Random rand = new Random();
        mapa = new Mapa();
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();
        
        ciclista = new Ciclista(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Cria um veiculo em uma posicao aleatoria
        ciclista.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Define a posicao destino aleatoriamente
        mapa.adicionarItem(ciclista);//Inicializando o mapa com o veículo
        
        pedestre = new Pedestre(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Cria um veiculo em uma posicao aleatoria
        pedestre.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Define a posicao destino aleatoriamente
        mapa.adicionarItem(pedestre);//Inicializando o mapa com o veículo
        
        mercadoria = new Mercadoria(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)), 1);//Cria um veiculo em uma posicao aleatoria
        mercadoria.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Define a posicao destino aleatoriamente
        mapa.adicionarItem(mercadoria);//Inicializando o mapa com o veículo
        
        mercadoria2 = new Mercadoria(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)), 2);//Cria um veiculo em uma posicao aleatoria
        mercadoria2.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Define a posicao destino aleatoriamente
        mapa.adicionarItem(mercadoria2);//Inicializando o mapa com o veículo
        
        veiculo = new Caminhao(pedestre.getLocalizacaoAtual());//Cria um veiculo em uma posicao aleatoria
        veiculo.setLocalizacaoDestino(mercadoria.getLocalizacaoAtual());//Define a posicao destino aleatoriamente
        mapa.adicionarItem(veiculo);//Inicializando o mapa com o veículo
        
        veiculo2 = new Caminhao(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Cria um veiculo em uma posicao aleatoria
        veiculo2.setLocalizacaoDestino(mercadoria2.getLocalizacaoAtual());//Define a posicao destino aleatoriamente
        mapa.adicionarItem(veiculo2);//Inicializando o mapa com o veículo
        
        janelaSimulacao = new JanelaSimulacao(mapa);
    }
    
    public void executarSimulacao(int numPassos){
        janelaSimulacao.executarAcao();
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            mapa.adicionarItem(veiculo);
            mapa.adicionarItem(veiculo2);
            mapa.adicionarItem(ciclista);
            mapa.adicionarItem(pedestre);
            esperar(100);
        }        
    }

    private void executarUmPasso() {
        Random rand = new Random();
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();
        
        mapa.removerItem(veiculo);
        if(veiculo.chegouDestino()){
            veiculo.setLocalizacaoDestino(mercadoria.getLocalizacaoDestino());
        }
        veiculo.executarAcao();
        mapa.adicionarItem(veiculo);
        
        mapa.removerItem(veiculo2);
        veiculo2.executarAcao();
        if(veiculo2.chegouDestino()){
            veiculo2.setLocalizacaoDestino(mercadoria2.getLocalizacaoDestino());
        }
        mapa.adicionarItem(veiculo2);
        
        mapa.removerItem(ciclista);
        if(ciclista.chegouDestino()){
            ciclista.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
        }
        ciclista.executarAcao();
        mapa.adicionarItem(ciclista);
        
        mapa.removerItem(pedestre);
        if(pedestre.chegouDestino()){
            pedestre.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));
        }
        pedestre.executarAcao();
        mapa.adicionarItem(pedestre);
        
        janelaSimulacao.executarAcao();
    }
    
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
}
