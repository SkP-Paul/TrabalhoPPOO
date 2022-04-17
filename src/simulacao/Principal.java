package simulacao;

/**
 *	Programa principal do Projeto, exibe a tela de configuração inicial e realiza a execução do programa. 
 *  @author 
 */

public class Principal {
	
    public static void main(String[] args) {
        MenuPrincipal janela = new MenuPrincipal();
        janela.exibirJanela();

        while (!janela.simulacaoPronta()) { // Congela a execução enquanto o usuário não confirma as configurações iniciais
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        Simulacao sim = new Simulacao();
        sim.executarSimulacao(900);
        System.out.print("Fim");
    }
}
