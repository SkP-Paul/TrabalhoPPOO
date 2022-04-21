package simulacao;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/**
 * Classe para montagem de interface de configuração da Simulação
 *
 * @author Danilo Aparecido Namitala and Pedro H. Marques Siqueira and Jonas
 * Fernandes dos Reis and Paulo Eduardo Soares Rezende
 */
public class MenuPrincipal {

    private JFrame janela;
    private JLabel rotuloPedestres;
    private JLabel rotuloCiclistas;
    private JLabel rotuloCaminhoes;
    private JSlider sliderPedestres;
    private JSlider sliderCiclistas;
    private JSlider sliderCaminhoes;
    private JButton botaoConfirmar;
    private boolean verificacao; // Verificador se o Usuário confirmou a configuração

    /**
     * Declaração de Atributos do Menu e chamada ao método de elaboração da
     * interface
     */
    public MenuPrincipal() {
        janela = new JFrame("Configurar Simulação");
        rotuloPedestres = new JLabel("Quantidade de Pedestres: ");
        rotuloCiclistas = new JLabel("Quantidade de Ciclistas: ");
        rotuloCaminhoes = new JLabel("Quantidade de Caminhoes: ");
        sliderPedestres = new JSlider(1, 21, 5);
        sliderCiclistas = new JSlider(1, 21, 5);
        sliderCaminhoes = new JSlider(1, 5, 1);
        botaoConfirmar = new JButton("Confirmar");
        verificacao = false;
        botaoConfirmar.addActionListener(new ActionListener() { // Confirmação do Usuário na tela de configuração
            @Override
            public void actionPerformed(ActionEvent e) {
                int pedestres = sliderPedestres.getValue();
                int ciclistas = sliderCiclistas.getValue();
                int caminhoes = sliderCaminhoes.getValue();
                Simulacao.ajustarConfiguracoes(pedestres, ciclistas, caminhoes); // Ajuste de configuração com as entradas do Usuário
                verificacao = true;
                janela.dispose();
            }
        });

        montarJanela();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Construção da Tela inserindo elementos na interface
     */
    private void montarJanela() {
        janela.setSize(400, 300);
        janela.setLayout(new BorderLayout());

        JPanel painelDireito = new JPanel();
        painelDireito.setLayout(new GridLayout(3, 1));
        montarSlider(sliderPedestres, 5);
        painelDireito.add(sliderPedestres);
        montarSlider(sliderCiclistas, 5);
        painelDireito.add(sliderCiclistas);
        montarSlider(sliderCaminhoes, 2);
        painelDireito.add(sliderCaminhoes);
        janela.add(painelDireito, BorderLayout.EAST);

        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setLayout(new GridLayout(3, 1));
        painelEsquerdo.add(rotuloPedestres);
        painelEsquerdo.add(rotuloCiclistas);
        painelEsquerdo.add(rotuloCaminhoes);
        janela.add(painelEsquerdo, BorderLayout.WEST);

        JPanel painelSul = new JPanel();
        painelSul.setLayout(new FlowLayout());
        painelSul.add(botaoConfirmar);
        janela.add(painelSul, BorderLayout.SOUTH);
    }

    /**
     * Elaboração do Slider
     *
     * @param s - um Slider
     * @param majorTicks - Espaçamento entre dois números
     */
    private void montarSlider(JSlider s, int majorTicks) {
        s.setPaintTicks(true);
        s.setPaintLabels(true);
        s.setMinorTickSpacing(1);
        s.setMajorTickSpacing(majorTicks);
    }

    /**
     * Exibir a janela da Simulação
     */
    public void exibirJanela() {
        janela.setVisible(true);
    }

    /**
     * retornar se o usuário finalizou a configuração
     *
     * @return verificacao - um booleano indicando o status da configuração
     * concluida (true) ou pendente (false)
     */
    public boolean simulacaoPronta() {
        return verificacao;
    }
}
