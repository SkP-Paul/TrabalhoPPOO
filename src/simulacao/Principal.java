package simulacao;

/**
 *
 * @author Luiz Merschmann
 */
public class Principal {

    public static void main(String[] args) {
        MenuPrincipal janela = new MenuPrincipal();
        janela.exibirJanela();

        while (!janela.simulacaoPronta()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        Simulacao sim = new Simulacao();
        sim.executarSimulacao(400);
        System.out.print("Fim");
    }
}
