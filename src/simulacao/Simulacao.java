package simulacao;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsavel pela simulacao.
 *
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann and Danilo
 * Aparecido Namitala and Pedro H. Marques Siqueira and Jonas Fernandes dos Reis
 * and Paulo Eduardo Soares Rezende
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
    private JanelaSimulacao janelaSimulacao;
    private int contador = 0;
    private int velocidade = 0;
    private Mapa mapa;
    private Random rand;
    private int largura;
    private int altura;

    /**
     * Inicialização dos Atributos e execução do Programa
     */
    public Simulacao() {
        mapa = new Mapa();
        largura = mapa.getLargura() - 1;
        altura = mapa.getAltura() - 1;
        rand = new Random();

        veiculos = new ArrayList<Caminhao>();
        pedestres = new ArrayList<Pedestre>();
        ciclistas = new ArrayList<Ciclista>();
        mercadorias = new ArrayList<Mercadoria>();
        lojas = new ArrayList<Loja>();
        /**
         * Inicialização das Lojas, atribuindo uma posição aleatória e fixa no
         * mapa. Dentro do mapa podem existir várias Lojas, e estas são
         * adicionadas a um arrayList separado, sendo que cada uma tera um ID
         * definido pela variavel "i" utilizada no loop.
         */
        for (int i = 0; i < qtdLojas; i++) {
            Loja loja = new Loja(new Localizacao(rand.nextInt(largura), 30), i);
            Item item = mapa.getItem(loja.getLocalizacaoAtual());
            while (item != null) {
                loja.setLocalizacaoAtual(new Localizacao(rand.nextInt(largura), 30));
                item = mapa.getItem(loja.getLocalizacaoAtual());
            }
            lojas.add(loja);
            mapa.adicionarItem(loja);
        }
        /**
         * Inicialização dos Ciclistas, atribuindo uma posição aleatória
         * inicial, e um destino aleatório inicial. Dentro do mapa podem existir
         * vários Ciclistas, e estes são adicionados a um arrayList separado.
         */
        for (int i = 0; i < qtdCiclistas; i++) {
            Ciclista ciclista = new Ciclista(localizacaoAleatoria());
            ciclista.setLocalizacaoDestino(localizacaoAleatoria());
            Item item = mapa.getItem(ciclista.getLocalizacaoAtual());
            while (item != null) {
                ciclista.setLocalizacaoAtual(localizacaoAleatoria());
                item = mapa.getItem(ciclista.getLocalizacaoAtual());
            }
            ciclistas.add(ciclista);
            mapa.adicionarItem(ciclista);
        }
        /**
         * Inicialização dos Caminhões, atribuindo uma posição aleatória
         * inicial, sem um destino atual. Dentro do mapa podem existir vários
         * Caminhões, e estes são adicionados a um arrayList separado.
         */
        for (int i = 0; i < qtdCaminhoes; i++) {
            Caminhao caminhao = new Caminhao(localizacaoAleatoria());
            Item item = mapa.getItem(caminhao.getLocalizacaoAtual());
            while (item != null) {
                caminhao.setLocalizacaoAtual(localizacaoAleatoria());
                item = mapa.getItem(caminhao.getLocalizacaoAtual());
            }
            veiculos.add(caminhao);
        }
        /**
         * Inicialização dos Pedestres, atribuindo uma posição aleatória
         * inicial, e um destino aleatório. Dentro do mapa podem existir vários
         * Pedestres, e estes são adicionados a um arrayList separado.
         */
        for (int i = 0; i < qtdPedestres; i++) {
            Pedestre pedestre = new Pedestre(localizacaoAleatoria());
            pedestre.setLocalizacaoDestino(localizacaoAleatoria());
            Item item = mapa.getItem(pedestre.getLocalizacaoAtual());
            while (item != null) {
                pedestre.setLocalizacaoAtual(localizacaoAleatoria());
                item = mapa.getItem(pedestre.getLocalizacaoAtual());
            }
            pedestres.add(pedestre);
        }
        /**
         * Inicialização das Mercadorias, atribuindo uma posição aleatória fixa,
         * um caminhão para coletá-la e uma Loja Destino. Dentro do mapa podem
         * existir vários Pedestres, e estes são adicionados a um arrayList
         * separado.
         */
        for (int i = 0; i < qtdInicMerc; i++) {
            Mercadoria mercadoria = new Mercadoria(localizacaoAleatoria(), lojas.get(rand.nextInt(lojas.size())));
            Item item = mapa.getItem(mercadoria.getLocalizacaoAtual());
            while (item != null) {
                mercadoria.setLocalizacaoAtual(localizacaoAleatoria());
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
     *
     * @param numPassos - número de passos a serem executados
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

    /**
     * Função para executar um movimento de cada elemento (Caminhão, Ciclista e
     * Pedestre) Faz uso de um contador (velocidade) em que o resto de sua
     * divisão determina qual elemento deve ser movimentado
     */
    private void executarUmPasso() {
        if (velocidade % 2 == 0) {
            for (Ciclista c : ciclistas) {
                mapa.removerItem(c);
                if (c.chegouDestino()) {
                    c.setLocalizacaoDestino(localizacaoAleatoria());
                }
                c.executarAcao(mapa);
                mapa.adicionarItem(c);
            }
        }

        if (velocidade % 3 == 0) {
            for (Pedestre p : pedestres) {
                mapa.removerItem(p);
                if (p.chegouDestino()) {
                    p.setLocalizacaoDestino(localizacaoAleatoria());
                }
                p.executarAcao(mapa);
                mapa.adicionarItem(p);
            }
        }

        for (Caminhao veiculo : veiculos) {
            if (mapa.getItem(veiculo.getLocalizacaoAtual()) instanceof Caminhao) {
                mapa.removerItem(veiculo);
            }
            if (veiculo.chegouDestino()) {
                if (veiculo.estaCarregado()) { // verifica se o Veiculo está carregado, caso esteja e tenha alcançado seu destino, esvazia a carga
                    veiculo.descarregar();
                } else { // caso contrário, ele chegou até a mercadoria, e deve ser carregado
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

        velocidade++;
        contador++;
        janelaSimulacao.executarAcao();
    }

    /**
     * Função para realizar uma espera em milisegundos entre cada ação no mapa
     *
     * @param milisegundos - tempo em milisegundos
     */
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
        Mercadoria mercadoria = new Mercadoria(localizacaoAleatoria(), lojas.get(rand.nextInt(lojas.size())));
        Item item = mapa.getItem(mercadoria.getLocalizacaoAtual());
        while (item != null) {
            mercadoria.setLocalizacaoAtual(localizacaoAleatoria());
            item = mapa.getItem(mercadoria.getLocalizacaoAtual());
        }
        mercadorias.add(mercadoria);
        Caminhao c = veiculos.get(rand.nextInt(veiculos.size()));
        c.addMercadoria(mercadoria);
        mapa.adicionarItem(mercadoria);
    }

    /**
     * Recepção de configuração realizada pelo usuário, determina a quantidade
     * de cada elemento móvel na simulação
     *
     * @param pedestres - Quantidade de Pedestres
     * @param ciclistas - Quantidade de Ciclistas
     * @param caminhoes - Quantidade de Caminhões
     */
    public static void ajustarConfiguracoes(int pedestres, int ciclistas, int caminhoes) {
        qtdPedestres = pedestres;
        qtdCiclistas = ciclistas;
        qtdCaminhoes = caminhoes;
    }

    /**
     * Gera uma localização aleatória
     */
    private Localizacao localizacaoAleatoria() {
        return new Localizacao(rand.nextInt(largura), rand.nextInt(altura));
    }
}
