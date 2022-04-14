package simulacao;

import simulacao.Localizacao;
import javax.swing.ImageIcon;

public class Pedestre extends ItemMovel {

    public Pedestre(Localizacao localizacao) {
        super(localizacao);
        setImagem(new ImageIcon(getClass().getResource("Imagens/pessoa.png")).getImage());
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
                            System.out.println("VALOR FORA DA MATRIZ");
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
