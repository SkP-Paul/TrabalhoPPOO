package simulacao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
/**
 * Classe para representação dos veículos (Caminhões)
 * @author Danilo Aparecido Namitala and Pedro H. Marques Siqueira and Jonas Fernandes dos Reis and Paulo Eduardo Soares Rezende

 */
public class Caminhao extends ItemMovel {

    private List<Mercadoria> mercadoriasPendentes; // Um Array armazenando as mercadorias pendentes de serem coletadas
    private boolean carga; // Indica se o caminhão está carregado (true) ou não (false)
    
    /**
     * Construtor do caminhão, colocando ícone do veículo na posição designada do mapa.
     * @param localizacao - uma Localização no mapa
     */
    public Caminhao(Localizacao localizacao) {
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource("Imagens/veiculo.jpg")).getImage());
        mercadoriasPendentes = new ArrayList<Mercadoria>();
        carga = false;
    }
    /**
     * Adiciona uma mercadoria ao ArrayList de mercadorias vinculadas ao caminhão
     * @param m - uma Mercadoria a ser adicionada
     */
    public void addMercadoria(Mercadoria m) {
        mercadoriasPendentes.add(m);
        if (this.getLocalizacaoDestino() == null) {
            this.setLocalizacaoDestino(m.getLocalizacaoAtual());
        }
    }
    
    /**
     * Descarrega a mercadoria na loja, declara posição destino para a próxima mercadoria do Array.
     */
    public void descarregar() {
        if (getProxMercadoria() != null) {
            setLocalizacaoDestino(getProxMercadoria().getLocalizacaoAtual());
        } else {
            setLocalizacaoDestino(null);
        }
        setCarga(false);
    }
    /**
     * Coleta uma mercadoria, declara a posição de destino para a Loja na qual a mercadoria se destina.
     * Remove o primeiro índice do Array de mercadorias Pendentes, para que a próxima mercadoria seja a primeira.
     * @return Mercadoria - uma Mercadoria
     */
    public Mercadoria carregar() {
        if (getProxMercadoria() != null) {
            setLocalizacaoDestino(getProxMercadoria().getLocalizacaoDestino());
            setCarga(true);
        }
        return mercadoriasPendentes.remove(0);
    }
    /**
     * Coleta o primeiro índice do Array de Mercadorias.
     * @return Mercadoria - uma Mercadoria
     */
    public Mercadoria getProxMercadoria() {
        if (!mercadoriasPendentes.isEmpty()) {
            return mercadoriasPendentes.get(0);
        } else {
            return null;
        }
    }
    /**
     * Retorna se o Caminhão está carregado ou vazio
     * @return boolean - Caminhão carregado (true) ou descarregado (false)
     */
    public boolean estaCarregado() {
        return carga;
    }
    
    /**
     * Declara o status de carregamento do caminhão 
     * @param bool - carregar Caminhão  (true) ou descarregar (false)
     */
    private void setCarga(boolean bool) {
        carga = bool;
    }
    /**
     * Realiza o movimento do Caminhão, se não houver um obstáculo.
     */
    @Override
    public void executarAcao(Mapa mapa) {
        Localizacao destino = getLocalizacaoDestino();
        if (destino != null) {
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(getLocalizacaoDestino());
            Item i = mapa.getItem(proximaLocalizacao);
            if ((i instanceof Ciclista || i instanceof Pedestre || i instanceof Caminhao) == false) {
                setLocalizacaoAtual(proximaLocalizacao);
            }
        }
    }
}
