package simulacao;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MenuPrincipal {

    private JFrame janela;
    private JLabel rotuloPedestres;
    private JLabel rotuloCiclistas;
    private JLabel rotuloCaminhoes;
    private JSlider sliderPedestres;
    private JSlider sliderCiclistas;
    private JSlider sliderCaminhoes;
    private JButton botaoConfirmar;
    private boolean verificacao;

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

        montarJanela();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

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
        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pedestres = sliderPedestres.getValue();
                int ciclistas = sliderCiclistas.getValue();
                int caminhoes = sliderCaminhoes.getValue();
                Simulacao.ajustarConfiguracoes(pedestres, ciclistas, caminhoes);
                verificacao = true;
                janela.dispose();
            }
        });

        janela.dispose();
    }

    private void montarSlider(JSlider s, int majorTicks) {
        s.setPaintTicks(true);
        s.setPaintLabels(true);
        s.setMinorTickSpacing(1);
        s.setMajorTickSpacing(majorTicks);
    }

    public void exibirJanela() {
        janela.setVisible(true);
    }

    public boolean simulacaoPronta() {
        return verificacao;
    }
}
