package simulacao;

import java.awt.Image;

/**
 * Representa os itens da simulacao.
 *
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Item {

    private Localizacao localizacaoAtual;
    private Localizacao localizacaoDestino;
    private Image imagem;

    /**
     * Construtor de um item
     *
     * @param localizacao: uma Localização no Mapa
     */
    public Item(Localizacao localizacao) {
        this.localizacaoAtual = localizacao;
        localizacaoDestino = null;
    }

    /**
     * Função para retornar a Localização atual do Item.
     *
     * @return Localizacao -: a localização onde está o item no momento chamado
     */
    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    /**
     * Função para retornar a Localização destino do Item.
     *
     * @return Localizacao -: a localização onde está o item deverá chegar
     */
    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }

    /**
     * Função para retornar a Imagem do Item.
     *
     * @return Image -: uma imagem definida na instanciação
     */
    public Image getImagem() {
        return imagem;
    }

    /**
     * Função para adicionar uma Imagem ao Item.
     *
     * @param imagem: uma imagem para a representação do item no mapa
     */
    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    /**
     * Função para adicionar uma Localização atual ao Item.
     *
     * @param localizacaoAtual: uma Localização válida no mapa onde o Item está
     */
    protected void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    /**
     * Função para adicionar uma Localização destino ao Item.
     *
     * @param localizacaoDestino: uma Localização válida no mapa para onde o
     * Item irá
     */
    protected void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }

    /**
     * Retorna se o Item está na Localização de destino
     *
     * @return boolean -: Item está no destino (true) ou Item não está no destino
     * (false)
     */
    public boolean chegouDestino() {
        return getLocalizacaoAtual() == getLocalizacaoDestino();
    }
}
