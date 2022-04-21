package simulacao;

import javax.swing.ImageIcon;

/**
 * Classe para montagem da Mercadoria
 *
 * @author Danilo Aparecido Namitala and Pedro H. Marques Siqueira and Jonas
 * Fernandes dos Reis and Paulo Eduardo Soares Rezende
 */
public class Mercadoria extends Item {

    private Loja loja; // Loja de Destino

    /**
     * Construtor de uma mercadoria
     *
     * @param localizacao: uma localização fixa no mapa
     * @param loja: uma Loja de Destino para a mercadoria (fixa)
     */
    public Mercadoria(Localizacao localizacao, Loja loja) {
        super(localizacao);
        this.loja = loja;
        //Verificar ID da loja para alterar o icone da mercadoria para ficar da mesma cor da loja
        if (loja.getId() % 3 == 0) {
            setImagem(new ImageIcon(getClass().getResource("Imagens/pacote.png")).getImage());
        } else if (loja.getId() % 2 == 1) {
            setImagem(new ImageIcon(getClass().getResource("Imagens/pacote2.png")).getImage());
        } else {
            setImagem(new ImageIcon(getClass().getResource("Imagens/pacote3.png")).getImage());
        }
        super.setLocalizacaoDestino(loja.getLocalizacaoAtual());
    }

    /**
     * Coletar a Loja de Destino
     *
     * @return Loja -: retorna a loja de Destino da mercadoria
     */
    public Loja getLoja() {
        return loja;
    }
}
