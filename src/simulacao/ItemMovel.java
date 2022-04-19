package simulacao;
/**
 * Classe pai para os itens móveis do mapa (Pedestre, Ciclista e Caminhão)
 * @author Danilo Aparecido Namitala and Pedro H. Marques Siqueira and Jonas Fernandes dos Reis and Paulo Eduardo Soares Rezende
 *
 */
public abstract class ItemMovel extends Item {
	/**
	 * Construtor de um item Móvel
	 * @param localizacao - uma Localização no Mapa
	 */
    public ItemMovel(Localizacao localizacao) {
        super(localizacao);
    }
    /**
     * Método a ser sobrescrito nas subclasses
     * @param mapa - o mapa da simulação
     */
    public abstract void executarAcao(Mapa mapa);
}
