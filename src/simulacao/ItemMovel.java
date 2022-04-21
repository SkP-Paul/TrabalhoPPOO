package simulacao;

/**
 * Classe pai para os itens móveis do mapa (Pedestre, Ciclista e Caminhão)
 *
 * @author Danilo Aparecido Namitala and Pedro H. Marques Siqueira and Jonas
 * Fernandes dos Reis and Paulo Eduardo Soares Rezende
 *
 */
public class ItemMovel extends Item {

    /**
     * Construtor de um item Móvel
     *
     * @param localizacao - uma Localização no Mapa
     */
    public ItemMovel(Localizacao localizacao) {
        super(localizacao);
    }

    /**
     * Movimentação dos objetos Neste trecho, é realizado uma verificação com a
     * posição de destino do objeto, ele não realiza colisões com um outro
     * Ciclista, Pedestre ou Caminhão
     *
     * @param mapa - o mapa da simulação
     */
    public void executarAcao(Mapa mapa) {
        Localizacao destino = getLocalizacaoDestino();
        if (destino != null) {
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(getLocalizacaoDestino());
            Item i = mapa.getItem(proximaLocalizacao);
            if ((i == null) || ((i instanceof Ciclista || i instanceof Pedestre || i instanceof Caminhao) == false)) {
                setLocalizacaoAtual(proximaLocalizacao);
            } else { // Em caso de colisão, gera uma nova posição destino
                for (int x = -1; x <= 1 || i != null; x++) { // Verificador de nova posição válida
                    for (int y = -1; y <= 1 || i != null; y++) {
                        int dX = getLocalizacaoAtual().getX() + x;
                        int dY = getLocalizacaoAtual().getY() + y;
                        proximaLocalizacao = new Localizacao(dX, dY);
                        try {
                            i = mapa.getItem(proximaLocalizacao);
                        } catch (IndexOutOfBoundsException e) { // Valor fora da Matriz do mapa
                            proximaLocalizacao = getLocalizacaoAtual();
                            i = mapa.getItem(proximaLocalizacao);
                        }
                    }
                }
                if ((i == null) || ((i instanceof Ciclista || i instanceof Pedestre || i instanceof Caminhao) == false)) {
                    setLocalizacaoAtual(proximaLocalizacao); // Ir para a próxima posição
                }
            }
        }
    }
}
