package simulacao;

import javax.swing.ImageIcon;
/**
 * Classe para montagem dos Pedestres
 * @author Danilo Aparecido Namitala and Pedro H. Marques Siqueira and Jonas Fernandes dos Reis and Paulo Eduardo Soares Rezende
 */
public class Pedestre extends ItemMovel {
	/**
	 * Construtor do Pedestre
	 * @param localizacao - Posição inicial de um Pedestre
	 */
    public Pedestre(Localizacao localizacao) {
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource("Imagens/pessoa.png")).getImage());
    }
    /**
     * Movimentação do Pedestre
     * Neste trecho, é realizado uma verificação com a posição de destino do Pedestre, ele não realiza colisões com um outro Pedestre, Ciclista ou Caminhão
     * 
     */
    @Override
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
                            System.out.println("VALOR FORA DA MATRIZ");
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
