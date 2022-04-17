package simulacao;

import javax.swing.ImageIcon;

public class Ciclista extends ItemMovel {
	/**
	 * Construtor de Ciclista
	 * Cria uma imagem de ciclista no mapa na sua posição inicial
	 * @param localizacao
	 */
    public Ciclista(Localizacao localizacao) {
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource("Imagens/bicicleta.jpg")).getImage());
    }

    @Override
    public void executarAcao(Mapa mapa) {
        Localizacao destino = getLocalizacaoDestino();
        if (destino != null) {
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(getLocalizacaoDestino());
            Item i = mapa.getItem(proximaLocalizacao);
            if (i == null) {
                if ((i instanceof Ciclista || i instanceof Pedestre || i instanceof Caminhao) == false) {
                    setLocalizacaoAtual(proximaLocalizacao);
                }
            } else {
                for (int x = -1; x <= 1 || i != null; x++) {
                    for (int y = -1; y <= 1 || i != null; y++) {
                        int dX = getLocalizacaoAtual().getX() + x;
                        int dY = getLocalizacaoAtual().getY() + y;
                        proximaLocalizacao = new Localizacao(dX, dY);
                        try {
                            i = mapa.getItem(proximaLocalizacao);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Ciclista // VALOR FORA DA MATRIZ");
                            proximaLocalizacao = getLocalizacaoAtual();
                            i = mapa.getItem(proximaLocalizacao);
                        }
                    }
                }
                if (i == null) {
                    if ((i instanceof Ciclista || i instanceof Pedestre || i instanceof Caminhao) == false) {
                        setLocalizacaoAtual(proximaLocalizacao);
                    }
                }
            }
        }
    }
}
