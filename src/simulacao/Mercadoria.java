package simulacao;

import javax.swing.ImageIcon;
/**
 * Classe para montagem da Mercadoria
 *
 */
public class Mercadoria extends Item {

    private Loja loja;

    public Mercadoria(Localizacao localizacao, Loja loja) {
        super(localizacao);
        this.loja = loja;
        setImagem(new ImageIcon(getClass().getResource("Imagens/pacoteML.png")).getImage());
        super.setLocalizacaoDestino(loja.getLocalizacaoAtual());
    }

    // Sem uso
    /*
    public void executarAcao(){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(super.getLocalizacaoDestino());
            setLocalizacaoAtual(proximaLocalizacao);
        }
    }
     */
    public Loja getLoja() {
        return loja;
    }

    // Sem uso
    /*
	public void setLoja(Loja loja) {
		this.loja = loja;
	}
     */
}
