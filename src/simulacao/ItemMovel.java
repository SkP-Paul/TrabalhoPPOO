package simulacao;

import simulacao.Localizacao;

public abstract class ItemMovel extends Item {

    public ItemMovel(Localizacao localizacao) {
        super(localizacao);
    }

    public abstract void executarAcao(Mapa mapa);
}
