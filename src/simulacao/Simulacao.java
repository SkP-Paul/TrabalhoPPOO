package simulacao;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsavel pela simulacao.
 *
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {

    private List<Caminhao> veiculos;
    private List<Pedestre> pedestres;
    private List<Ciclista> ciclistas;
    private List<Mercadoria> mercadorias;
    private List<Loja> lojas;
    private static final int qtdLojas = 3;
    private static final int qtdInicMerc = 5;
    private static int qtdCaminhoes = 1;
    private static int qtdCiclistas = 1;
    private static int qtdPedestres = 1;
    private final int bordaEsquerda;
    private final int bordaDireita;
    private JanelaSimulacao janelaSimulacao;
    private int contador = 0;
    private int velocidade = 0;
    private Mapa mapa;
    private Random rand;
    private int largura;
    /**
     * Inicialização dos Atributos e execução do Programa
     */
    public Simulacao() {
        mapa = new Mapa();
        largura = mapa.getLargura() - 1;
        altura = mapa.getAltura() - 1;
        rand = new Random();
        bordaEsquerda = 0;
        bordaDireita = largura;

        veiculos = new ArrayList<Caminhao>();
        pedestres = new ArrayList<Pedestre>();
        ciclistas = new ArrayList<Ciclista>();
        mercadorias = new ArrayList<Mercadoria>();
        lojas = new ArrayList<Loja>();
        /**
         * Inicialização das Lojas, atribuindo uma posição aleatória e fixa no mapa.
         * Dentro do mapa podem existir várias Lojas, e estas são adicionadas a um arrayList separado.
         */
        for (int i = 0; i < qtdLojas; i++) {
            Loja loja = new Loja(new Localizacao(rand.nextInt(largura), 30));
            Item item = mapa.getItem(loja.getLocalizacaoAtual());
            while (item != null) {
                loja.setLocalizacaoAtual(new Localizacao(rand.nextInt(largura), 30));
                item = mapa.getItem(loja.getLocalizacaoAtual());
            }
            lojas.add(loja);
            mapa.adicionarItem(loja);
        }
        /**
         * Inicialização dos Ciclistas, atribuindo uma posição aleatória inicial, e um destino aleatório inicial.
         * Dentro do mapa podem existir vários Ciclistas, e estes são adicionados a um arrayList separado.
         */
        for (int i = 0; i < qtdCiclistas; i++) {
            Ciclista ciclista = new Ciclista(new Localizacao(bordaEsquerda, rand.nextInt(25)+5));
            ciclista.setLocalizacaoDestino(new Localizacao(bordaDireita, rand.nextInt(25)+5));
            Item item = mapa.getItem(ciclista.getLocalizacaoAtual());
            while (item != null) {
                ciclista.setLocalizacaoAtual(new Localizacao(rand.nextInt(largura), rand.nextInt(25)+5));
                item = mapa.getItem(ciclista.getLocalizacaoAtual());
            }
            ciclistas.add(ciclista);
            mapa.adicionarItem(ciclista);
        }
        /**
         * Inicialização dos Caminhões, atribuindo uma posição aleatória inicial, sem um destino atual.
         * Dentro do mapa podem existir vários Caminhões, e estes são adicionados a um arrayList separado.
         */
        for (int i = 0; i < qtdCaminhoes; i++) {
            Caminhao caminhao = new Caminhao(new Localizacao(rand.nextInt(largura), rand.nextInt(25)+5));
            Item item = mapa.getItem(caminhao.getLocalizacaoAtual());
            while (item != null) {
                caminhao.setLocalizacaoAtual(new Localizacao(rand.nextInt(largura), rand.nextInt(25)+5));
                item = mapa.getItem(caminhao.getLocalizacaoAtual());
            }
            veiculos.add(caminhao);
        }
        /**
         * Inicialização dos Pedestres, atribuindo uma posição aleatória inicial, e um destino aleatório.
         * Dentro do mapa podem existir vários Pedestres, e estes são adicionados a um arrayList separado.
         */
        for (int i = 0; i < qtdPedestres; i++) {
            Pedestre pedestre = new Pedestre(new Localizacao(bordaDireita, rand.nextInt(25)+5));
            pedestre.setLocalizacaoDestino(new Localizacao(bordaEsquerda, rand.nextInt(25)+5));
            Item item = mapa.getItem(pedestre.getLocalizacaoAtual());
            while (item != null) {
                pedestre.setLocalizacaoAtual(new Localizacao(rand.nextInt(largura), rand.nextInt(25)+5));
                item = mapa.getItem(pedestre.getLocalizacaoAtual());
            }
            pedestres.add(pedestre);
        }
        /**
         * Inicialização das Mercadorias, atribuindo uma posição aleatória fixa, um caminhão para coletá-la e uma Loja Destino.
         * Dentro do mapa podem existir vários Pedestres, e estes são adicionados a um arrayList separado.
         */
        for (int i = 0; i < qtdInicMerc; i++) {
            Mercadoria mercadoria = new Mercadoria(new Localizacao(rand.nextInt(33)+1, rand.nextInt(15)+1), lojas.get(rand.nextInt(lojas.size())));
            Item item = mapa.getItem(mercadoria.getLocalizacaoAtual());
            while (item != null) {
                mercadoria.setLocalizacaoAtual(new Localizacao(rand.nextInt(33)+1, rand.nextInt(15)+1));
                item = mapa.getItem(mercadoria.getLocalizacaoAtual());
            }
            mercadorias.add(mercadoria);
            Caminhao c = veiculos.get(rand.nextInt(veiculos.size()));
            c.addMercadoria(mercadoria);
            mapa.adicionarItem(mercadoria);
        }

        janelaSimulacao = new JanelaSimulacao(mapa);
    }
    /**
     * Inicialização da Execução do Programa
     * @param numPassos
     */
    public void executarSimulacao(int numPassos) {
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
                contador = 0;
            }
            esperar(100);
        }
    }

    private void executarUmPasso() {
        if (velocidade % 2 == 0) {
            for (Ciclista c : ciclistas) {
                mapa.removerItem(c);
                if (c.chegouDestino()) {
                    c.setLocalizacaoAtual(new Localizacao(bordaEsquerda, rand.nextInt(25)+5));
                }
                c.executarAcao(mapa);
                mapa.adicionarItem(c);
            }
            System.out.println("ciclista ok");
        }

        if (velocidade % 3 == 0) {
            for (Pedestre p : pedestres) {
                mapa.removerItem(p);
                if (p.chegouDestino()) {
                    p.setLocalizacaoAtual(new Localizacao(bordaDireita, rand.nextInt(25)+5));
                }
                p.executarAcao(mapa);
                mapa.adicionarItem(p);
            }
            System.out.println("pedestre ok");
        }

        for (Caminhao veiculo : veiculos) {
            if (mapa.getItem(veiculo.getLocalizacaoAtual()) instanceof Caminhao) {
                mapa.removerItem(veiculo);
            }
            if (veiculo.chegouDestino()) {
                if (veiculo.estaCarregado()) {
                    veiculo.descarregar();
                } else {
                    Mercadoria carga = veiculo.carregar();
                    mercadorias.remove(carga);
                    mapa.removerItem(carga);
                }
            }
            veiculo.executarAcao(mapa);
            if (mapa.getItem(veiculo.getLocalizacaoAtual()) instanceof Loja == false) {
                mapa.adicionarItem(veiculo);
            }
        }
        System.out.println("carro ok");

        velocidade++;
        contador++;
        janelaSimulacao.executarAcao();
    }

    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Inserção de nova mercadoria ao Mapa
     */
    private void criarNovaMercadoria() {
        Mercadoria mercadoria = new Mercadoria(new Localizacao(rand.nextInt(33)+1, rand.nextInt(15)+1), lojas.get(rand.nextInt(lojas.size())));
        mercadorias.add(mercadoria);
        Caminhao c = veiculos.get(rand.nextInt(veiculos.size()));
        c.addMercadoria(mercadoria);
        mapa.adicionarItem(mercadoria);
    }

    public static void ajustarConfiguracoes(int pedestres, int ciclistas, int caminhoes) {
        qtdPedestres = pedestres;
        qtdCiclistas = ciclistas;
        qtdCaminhoes = caminhoes;
    }
}
