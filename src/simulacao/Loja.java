package simulacao;

import javax.swing.ImageIcon;

/**
 * Classe para representação das Lojas
 *
 * @author Danilo Aparecido Namitala and Pedro H. Marques Siqueira and Jonas
 * Fernandes dos Reis and Paulo Eduardo Soares Rezende
 *
 */
public class Loja extends Item {

    private int id;

    /**
     * Construtor da Loja Cria a imagem da Loja no mapa, em sua posição fixa
     * declarada.
     *
     * @param localizacao - uma Localização no mapa
     * @param id - um inteiro incremetado para definir um id para a loja
     */
    public Loja(Localizacao localizacao, int id) {
        super(localizacao);
        this.id = id;
        //Verifica o ID para alterar o icone da loja para suas mercadorias terem a mesma cor
        if (id % 3 == 0) {
            setImagem(new ImageIcon(getClass().getResource("Imagens/loja.png")).getImage());
        } else if (id % 2 == 1) {
            setImagem(new ImageIcon(getClass().getResource("Imagens/loja2.png")).getImage());
        } else {
            setImagem(new ImageIcon(getClass().getResource("Imagens/loja3.png")).getImage());
        }
        setLocalizacaoDestino(null);
    }

    /**
     * Função para retornar o ID da loja.
     *
     * @return id - um inteiro que é o id da loja
     */
    public int getId() {
        return id;
    }
}
