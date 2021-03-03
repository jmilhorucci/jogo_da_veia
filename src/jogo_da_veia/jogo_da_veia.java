package jogo_da_veia;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author jmilhorucci
 * 
 * Projeto avaliativo feito em Java referente a aula de Programação III. 
 * 
 * Este projeto tem com foco principal o uso de eventos de teclados e mouse, 
 * junto a lógica do algoritmo Jogo da Velha. 
 */

//Criação da classe jogo_da_veia, objeto que a partir dele representa a criação de uma janela JFrame herdada da classe Main
public class jogo_da_veia extends JFrame {

    int vitoria = -1; //Utilizada para atribuir valor 0 ou mais para vitorias, estando -1 significa empate
    //Redefinição do tipo de fonte, tamanho da fonte e cores no jogo
    Font fontePadrao = new Font("Montserrat", Font.BOLD, 14);
    Color corPadraoBotao = Color.gray;
    Color corPadraoTexto = Color.white;
    Color corPadraoX = Color.green;
    Color corPadraoO = Color.red;
    Font fontePadraoPontos = new Font("Montserrat Bold", Font.ITALIC, 20);

    //Objetos para a tela de multiplayer
    JPanel telaMultiPlayer;
    JLabel telaMultiPlayer_lblJogador1;
    JLabel telaMultiPlayer_lblJogador2;
    JTextArea telaMultiPlayer_lblAtalhos;
    JTextField telaMultiPlayer_nomeJogador1;
    JTextField telaMultiPlayer_nomeJogador2;
    JButton telaMultiPlayer_btnIncial;
    JButton telaMultiPlayer_btnEncerrar;

    //Objetos para a tela do jogo
    JPanel telaJogo;
    JPanel telaJogo_painel;
    JLabel[] telaJogo_lbls;
    JLabel telaJogo_fundo;
    JLabel telaJogo_nomeJogador1;
    JLabel telaJogo_nomeJogador2;
    JLabel telaJogo_nomeEmpate;
    JLabel telaJogo_pontoJogador1;
    JLabel telaJogo_pontoJogador2;
    JLabel telaJogo_pontoEmpate;
    JLabel telaJogo_jogadorAtual;
    JButton telaJogo_btnVolta;
    JButton telaJogo_btnReiniciar;

    //Determina se o jogo será interrompido devido à vitória de um jogador
    boolean jogoEncerrado = false;

    //Determina se uma pessoa vai jogar o jogo ou duas
    boolean computador = false;

    //Parâmetro para calcular o número de cliques e para determinar a parada do jogo ou não
    int contadorXeO = 0;

    //Define quem está em jogo
    boolean proximoJogador = true;

    //Função para mudar o tamanho da fonte de todos os botões e textos no jogo de uma vez
    private void defineFonte(Font fonte) {

        telaMultiPlayer_lblJogador1.setFont(fonte);
        telaMultiPlayer_lblJogador2.setFont(fonte);
        telaMultiPlayer_lblAtalhos.setFont(fonte);
        telaMultiPlayer_nomeJogador1.setFont(fonte);
        telaMultiPlayer_nomeJogador2.setFont(fonte);
        telaMultiPlayer_btnIncial.setFont(fonte);
        telaMultiPlayer_btnEncerrar.setFont(fonte);

        telaJogo_painel.setFont(fonte);
        telaJogo_nomeJogador1.setFont(fonte);
        telaJogo_nomeJogador2.setFont(fonte);
        telaJogo_nomeEmpate.setFont(fonte);
        telaJogo_pontoJogador1.setFont(fonte);
        telaJogo_pontoJogador2.setFont(fonte);
        telaJogo_pontoEmpate.setFont(fonte);
        telaJogo_btnVolta.setFont(fonte);
        telaJogo_btnReiniciar.setFont(fonte);

        telaJogo_jogadorAtual.setFont(new Font("Montserrat", Font.BOLD, 25));

    }

    //Função para mudar as cores dos botões e Labels do aplicativo
    private void setCoresObjetos(Color corTexto, Color corFundo) {

        telaMultiPlayer_lblJogador1.setForeground(corTexto);
        telaMultiPlayer_nomeJogador1.setForeground(Color.black);
        telaMultiPlayer_lblJogador2.setForeground(corTexto);
        telaMultiPlayer_nomeJogador2.setForeground(Color.black);
        telaMultiPlayer_lblAtalhos.setForeground(corTexto);
        telaMultiPlayer_lblAtalhos.setBackground(new Color(52, 73, 94));
        telaMultiPlayer_btnIncial.setForeground(corTexto);
        telaMultiPlayer_btnEncerrar.setForeground(corTexto);
        telaMultiPlayer_btnIncial.setBackground(new Color(76, 209, 55));
        telaMultiPlayer_btnEncerrar.setBackground(new Color(232, 65, 24));
        telaJogo.setBackground(new Color(52, 73, 94));
        telaJogo_painel.setBackground(new Color(52, 73, 94));
        telaMultiPlayer.setBackground(new Color(52, 73, 94));

        telaJogo_nomeJogador1.setForeground(corTexto);
        telaJogo_nomeJogador2.setForeground(corTexto);
        telaJogo_nomeEmpate.setForeground(corTexto);
        telaJogo_pontoJogador1.setForeground(corTexto);
        telaJogo_pontoJogador2.setForeground(corTexto);
        telaJogo_pontoEmpate.setForeground(corTexto);
        telaJogo_btnVolta.setForeground(corTexto);
        telaJogo_btnReiniciar.setForeground(corTexto);
        telaJogo_btnReiniciar.setBackground(new Color(0, 168, 255));
        telaJogo_btnVolta.setBackground(new Color(232, 65, 24));
    }

    //Função para criar o conteúdo dap tela multiplayer
    private void criarTelaMultiPlayer() {

        //Contêiner de base e os objetos utilizado
        telaMultiPlayer = new JPanel(null);
        
        telaMultiPlayer_lblJogador1 = new JLabel("Nome Jogador(a) X");
        telaMultiPlayer_lblJogador2 = new JLabel("Nome Jogador(a) O");
        telaMultiPlayer_nomeJogador1 = new JTextField("Jogador(a) 1", 8);
        telaMultiPlayer_nomeJogador2 = new JTextField("Jogador(a) 2", 8);
        telaMultiPlayer_lblAtalhos = new JTextArea("Atalhos do Jogo:\n\n"
                + "> Use CTRL + D para ver as dicas de jogo.\n"
                + "> Use CTRL + R para Reiniciar a partida.");
        
        
        telaMultiPlayer_btnIncial = new JButton("Iniciar");
        telaMultiPlayer_btnEncerrar = new JButton("Encerrar");

        //Vincula todos os objetos no contêiner
        telaMultiPlayer.add(telaMultiPlayer_lblJogador1);
        telaMultiPlayer.add(telaMultiPlayer_lblJogador2);
        telaMultiPlayer.add(telaMultiPlayer_lblAtalhos, "CENTER");
        telaMultiPlayer.add(telaMultiPlayer_nomeJogador1);
        telaMultiPlayer.add(telaMultiPlayer_nomeJogador2);
        telaMultiPlayer.add(telaMultiPlayer_btnIncial);
        telaMultiPlayer.add(telaMultiPlayer_btnEncerrar);

        //Definição de tamanho e a localização de todos os objetos no contêiner
        telaMultiPlayer_lblJogador1.setBounds(100, 130, 150, 30);
        telaMultiPlayer_nomeJogador1.setBounds(250, 130, 150, 30);
        telaMultiPlayer_lblJogador2.setBounds(100, 190, 150, 30);
        telaMultiPlayer_nomeJogador2.setBounds(250, 190, 150, 30);
        telaMultiPlayer_lblAtalhos.setBounds(50,350,400,100);
        telaMultiPlayer_btnIncial.setBounds(300, 250, 100, 30);
        telaMultiPlayer_btnEncerrar.setBounds(100, 250, 100, 30);
        
        
        telaMultiPlayer.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                telaMultiPlayer_btnIncial.requestFocus();
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
            }
        });
        
        telaMultiPlayer_btnIncial.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent event) {
            }

            @Override
            public void keyPressed(KeyEvent event) {
            }

            @Override
            public void keyReleased(KeyEvent event) {
                if (event.isControlDown() && (event.getKeyCode() == KeyEvent.VK_D)) { //Dicas
                    JOptionPane.showMessageDialog(null, "Dicas para Jogar:\n > O tabuleiro  é uma matriz  de três linhas por três colunas.\n"
                            + " > Dois jogadores escolhem uma marcação cada um, geralmente um círculo (O) e um xis (X).\n"
                            + " > Os jogadores jogam alternadamente, uma marcação por vez, numa lacuna que esteja vazia.\n"
                            + " > O objectivo é conseguir três círculos ou três xis em linha, quer horizontal, vertical\n"
                            + "ou diagonal , e ao mesmo tempo, quando possível, impedir o adversário de ganhar na próxima jogada.\n"
                            + " > Quando um jogador conquista o objetivo, costuma-se riscar os três símbolos.", "Dicas de como jogar", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    //Função para criar o conteúdo dap tela do jogo
    private void criarTelaJogo() {

        //Contêiner de base e os objetos utilizado
        telaJogo = new JPanel(null);
        //Imagem de fundo
        telaJogo_fundo = new JLabel();
        telaJogo_fundo.setIcon(new ImageIcon(this.getClass().getResource("/imgs/painel.png")));
        telaJogo_nomeJogador1 = new JLabel("", JLabel.CENTER);
        telaJogo_nomeJogador2 = new JLabel("", JLabel.CENTER);
        telaJogo_nomeEmpate = new JLabel("Empate", JLabel.CENTER);
        telaJogo_pontoJogador1 = new JLabel("0", JLabel.CENTER);
        telaJogo_pontoJogador2 = new JLabel("0", JLabel.CENTER);
        telaJogo_pontoEmpate = new JLabel("0", JLabel.CENTER);
        telaJogo_jogadorAtual = new JLabel("", JLabel.CENTER);
        telaJogo_painel = new JPanel(new GridLayout(3, 3, 8, 8));
        telaJogo_lbls = new JLabel[9];
        telaJogo_btnVolta = new JButton("Voltar");
        telaJogo_btnReiniciar = new JButton("Reiniciar");

        //Define os 9 Labels em telaJogo_painel 
        for (int i = 0; i < telaJogo_lbls.length; i++) {
            telaJogo_lbls[i] = new JLabel("", JLabel.CENTER);
            telaJogo_lbls[i].setFont(new Font("Montserrat", Font.BOLD, 50));
            telaJogo_lbls[i].setBackground(new Color(52, 73, 94));
            telaJogo_painel.add(telaJogo_lbls[i]);
        }

        //Vincula todos os objetos no contêiner
        telaJogo.add(telaJogo_nomeJogador1);
        telaJogo.add(telaJogo_nomeJogador2);
        telaJogo.add(telaJogo_nomeEmpate);
        telaJogo.add(telaJogo_pontoJogador1);
        telaJogo.add(telaJogo_pontoJogador2);
        telaJogo.add(telaJogo_pontoEmpate);
        telaJogo.add(telaJogo_jogadorAtual);
        telaJogo.add(telaJogo_fundo);
        telaJogo.add(telaJogo_painel);
        telaJogo.add(telaJogo_btnVolta);
        telaJogo.add(telaJogo_btnReiniciar);
        telaJogo.add(telaJogo_pontoEmpate);

        //Definição de tamanho e a localização de todos os objetos no contêiner
        telaJogo_nomeJogador1.setBounds(50, 10, 150, 30);
        telaJogo_nomeJogador2.setBounds(300, 10, 150, 30);
        telaJogo_nomeEmpate.setBounds(175, 410, 150, 30);
        telaJogo_pontoEmpate.setBounds(175, 440, 150, 30);
        telaJogo_pontoJogador1.setBounds(50, 40, 150, 30);
        telaJogo_pontoJogador2.setBounds(300, 40, 150, 30);
        telaJogo_jogadorAtual.setBounds(175, 25, 150, 30);
        telaJogo_fundo.setBounds(100, 105, 300, 300);
        telaJogo_painel.setBounds(100, 105, 300, 300);
        telaJogo_btnVolta.setBounds(100, 475, 100, 30);
        telaJogo_btnReiniciar.setBounds(300, 475, 100, 30);

        telaMultiPlayer_btnEncerrar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                telaMultiPlayer_btnEncerrar.setBackground(new Color(194, 54, 22));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                telaMultiPlayer_btnEncerrar.setBackground(new Color(232, 65, 24));
            }
        });

        telaMultiPlayer_btnIncial.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                telaMultiPlayer_btnIncial.setBackground(new Color(0, 128, 0));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                telaMultiPlayer_btnIncial.setBackground(new Color(76, 209, 55));
            }
        });

        telaJogo_btnReiniciar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                telaJogo_btnReiniciar.setBackground(new Color(41, 128, 185));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                telaJogo_btnReiniciar.setBackground(new Color(0, 168, 255));
            }
        });

        telaJogo_btnVolta.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                telaMultiPlayer_btnIncial.requestFocus();
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                telaJogo_btnVolta.setBackground(new Color(194, 54, 22));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                telaJogo_btnVolta.setBackground(new Color(232, 65, 24));
            }
        });

        //Função para identificar o código do jogador que irá jogar
        JogadorAtual();

    }

    //Função para verificar se existe um vencedor ou não
    //Se houver um vencedor, a função de cor de fundo será chamada através do trataVencedor
    //Finaliza o jogo. Pontuação do vencedor é add a jogoEncerrado e altera o valor da variavel
    public void verificaVencedor() {
        //Posições de 0 a 8
        String p0 = telaJogo_lbls[0].getText();
        String p1 = telaJogo_lbls[1].getText();
        String p2 = telaJogo_lbls[2].getText();
        String p3 = telaJogo_lbls[3].getText();
        String p4 = telaJogo_lbls[4].getText();
        String p5 = telaJogo_lbls[5].getText();
        String p6 = telaJogo_lbls[6].getText();
        String p7 = telaJogo_lbls[7].getText();
        String p8 = telaJogo_lbls[8].getText();

        int pontoJogador1 = Integer.valueOf(telaJogo_pontoJogador1.getText());
        int pontoJogador2 = Integer.valueOf(telaJogo_pontoJogador2.getText());

        //Imagens para idicar na mensagem quem ganhou, X ou O
        ImageIcon imgX = new ImageIcon(getClass().getResource("/imgs/x.png"));
        ImageIcon imgO = new ImageIcon(getClass().getResource("/imgs/o.png"));

        if (p0.equals(p1) && p0.equals(p2) && !p0.equals("")) { //ok
            if (p0.equals("X")) {
                telaJogo_pontoJogador1.setText((pontoJogador1 + 1) + "");
                JOptionPane.showMessageDialog(null, "Parabéns " + telaJogo_nomeJogador1.getText() + "\nVocê levou essa! \\o/", "X é o(a) Vendedor(a)!", JOptionPane.INFORMATION_MESSAGE, imgX);
                vitoria++; //Incrementa para vitorias X
            } else {
                telaJogo_pontoJogador2.setText((pontoJogador2 + 1) + "");
                JOptionPane.showMessageDialog(null, "Parabéns " + telaJogo_nomeJogador2.getText() + "\nVocê levou essa! \\o/", "O é o(a) Vendedor(a)!", JOptionPane.INFORMATION_MESSAGE, imgO);
                vitoria++; //Incrementa para vitorias O
            }
            //Responsável por travar as demais Labels, impedindo de serem clicadas após uma vitória
            trataVencedor(telaJogo_lbls[0], telaJogo_lbls[1], telaJogo_lbls[2]);
        }

        if (p3.equals(p4) && p3.equals(p5) && !p3.equals("")) {  //ok
            if (p3.equals("X")) {
                telaJogo_pontoJogador1.setText((pontoJogador1 + 1) + "");
                JOptionPane.showMessageDialog(null, "Parabéns " + telaJogo_nomeJogador1.getText() + "\nVocê levou essa! \\o/", "X é o(a) Vendedor(a)!", JOptionPane.INFORMATION_MESSAGE, imgX);
                vitoria++;
            } else {
                telaJogo_pontoJogador2.setText((pontoJogador2 + 1) + "");
                JOptionPane.showMessageDialog(null, "Parabéns " + telaJogo_nomeJogador2.getText() + "\nVocê levou essa! \\o/", "O é o(a) Vendedor(a)!", JOptionPane.INFORMATION_MESSAGE, imgO);
                vitoria++;
            }
            trataVencedor(telaJogo_lbls[3], telaJogo_lbls[4], telaJogo_lbls[5]);
        }

        if (p6.equals(p7) && p6.equals(p8) && !p6.equals("")) { //ok
            if (p6.equals("X")) {
                telaJogo_pontoJogador1.setText((pontoJogador1 + 1) + "");
                JOptionPane.showMessageDialog(null, "Parabéns " + telaJogo_nomeJogador1.getText() + "\nVocê levou essa! \\o/", "X é o(a) Vendedor(a)!", JOptionPane.INFORMATION_MESSAGE, imgX);
                vitoria++;

            } else {
                telaJogo_pontoJogador2.setText((pontoJogador2 + 1) + "");
                JOptionPane.showMessageDialog(null, "Parabéns " + telaJogo_nomeJogador2.getText() + "\nVocê levou essa! \\o/", "O é o(a) Vendedor(a)!", JOptionPane.INFORMATION_MESSAGE, imgO);
                vitoria++;
            }
            trataVencedor(telaJogo_lbls[6], telaJogo_lbls[7], telaJogo_lbls[8]);
        }

        if (p0.equals(p3) && p0.equals(p6) && !p0.equals("")) { //ok
            if (p0.equals("X")) {
                telaJogo_pontoJogador1.setText((pontoJogador1 + 1) + "");
                JOptionPane.showMessageDialog(null, "Parabéns " + telaJogo_nomeJogador1.getText() + "\nVocê levou essa! \\o/", "X é o(a) Vendedor(a)!", JOptionPane.INFORMATION_MESSAGE, imgX);
                vitoria++;
            } else {
                telaJogo_pontoJogador2.setText((pontoJogador2 + 1) + "");
                JOptionPane.showMessageDialog(null, "Parabéns " + telaJogo_nomeJogador2.getText() + "\nVocê levou essa! \\o/", "O é o(a) Vendedor(a)!", JOptionPane.INFORMATION_MESSAGE, imgO);
                vitoria++;
            }
            trataVencedor(telaJogo_lbls[0], telaJogo_lbls[3], telaJogo_lbls[6]);
        }

        if (p1.equals(p4) && p1.equals(p7) && !p1.equals("")) { //ok
            if (p1.equals("X")) {
                telaJogo_pontoJogador1.setText((pontoJogador1 + 1) + "");
                JOptionPane.showMessageDialog(null, "Parabéns " + telaJogo_nomeJogador1.getText() + "\nVocê levou essa! \\o/", "X é o(a) Vendedor(a)!", JOptionPane.INFORMATION_MESSAGE, imgX);
                vitoria++;
            } else {
                telaJogo_pontoJogador2.setText((pontoJogador2 + 1) + "");
                JOptionPane.showMessageDialog(null, "Parabéns " + telaJogo_nomeJogador2.getText() + "\nVocê levou essa! \\o/", "O é o(a) Vendedor(a)!", JOptionPane.INFORMATION_MESSAGE, imgO);
                vitoria++;
            }
            trataVencedor(telaJogo_lbls[1], telaJogo_lbls[4], telaJogo_lbls[7]);
        }

        if (p2.equals(p5) && p2.equals(p8) && !p2.equals("")) { //ok
            if (p2.equals("X")) {
                telaJogo_pontoJogador1.setText((pontoJogador1 + 1) + "");
                JOptionPane.showMessageDialog(null, "Parabéns " + telaJogo_nomeJogador1.getText() + "\nVocê levou essa! \\o/", "X é o(a) Vendedor(a)!", JOptionPane.INFORMATION_MESSAGE, imgX);
                vitoria++;
            } else {
                telaJogo_pontoJogador2.setText((pontoJogador2 + 1) + "");
                JOptionPane.showMessageDialog(null, "Parabéns " + telaJogo_nomeJogador2.getText() + "\nVocê levou essa! \\o/", "O é o(a) Vendedor(a)!", JOptionPane.INFORMATION_MESSAGE, imgO);
                vitoria++;
            }
            trataVencedor(telaJogo_lbls[2], telaJogo_lbls[5], telaJogo_lbls[8]);
        }

        if (p0.equals(p4) && p0.equals(p8) && !p0.equals("")) { //ok
            if (p0.equals("X")) {
                telaJogo_pontoJogador1.setText((pontoJogador1 + 1) + "");
                JOptionPane.showMessageDialog(null, "Parabéns " + telaJogo_nomeJogador1.getText() + "\nVocê levou essa! \\o/", "X é o(a) Vendedor(a)!", JOptionPane.INFORMATION_MESSAGE, imgX);
                vitoria++;
            } else {
                telaJogo_pontoJogador2.setText((pontoJogador2 + 1) + "");
                JOptionPane.showMessageDialog(null, "Parabéns " + telaJogo_nomeJogador2.getText() + "\nVocê levou essa! \\o/", "O é o(a) Vendedor(a)!", JOptionPane.INFORMATION_MESSAGE, imgO);
                vitoria++;
            }
            trataVencedor(telaJogo_lbls[0], telaJogo_lbls[4], telaJogo_lbls[8]);
        }

        if (p2.equals(p4) && p2.equals(p6) && !p2.equals("")) { //ok
            if (p2.equals("X")) {
                telaJogo_pontoJogador1.setText((pontoJogador1 + 1) + "");
                JOptionPane.showMessageDialog(null, "Parabéns " + telaJogo_nomeJogador1.getText() + "\nVocê levou essa! \\o/", "X é o(a) Vendedor(a)!", JOptionPane.INFORMATION_MESSAGE, imgX);
                vitoria++;
            } else {
                telaJogo_pontoJogador2.setText((pontoJogador2 + 1) + "");
                JOptionPane.showMessageDialog(null, "Parabéns " + telaJogo_nomeJogador2.getText() + "\nVocê levou essa! \\o/", "O é o(a) Vendedor(a)!", JOptionPane.INFORMATION_MESSAGE, imgO);
                vitoria++;
            }
            trataVencedor(telaJogo_lbls[2], telaJogo_lbls[4], telaJogo_lbls[6]);
        }

        telaJogo.repaint(); //Recria o layout
        setBackground(new Color(52, 73, 94));
    }

    //Função para finalizar o jogo
    private void trataVencedor(JLabel lbl1, JLabel lbl2, JLabel lbl3) {
        lbl1.setOpaque(true);
        lbl2.setOpaque(true);
        lbl3.setOpaque(true);
        jogoEncerrado = true;
    }

    //Função para identificar a jogada atual para multiplayer
    private boolean verificaJogadaAtual(JLabel lblJogada) {

        int pontoEmpate = Integer.valueOf(telaJogo_pontoEmpate.getText());
        //Imagens para idicar o emapate na mensagem
        ImageIcon imgE = new ImageIcon(getClass().getResource("/imgs/velha.gif"));

        if (lblJogada.getText().equals("")) {

            if (proximoJogador == true) {
                lblJogada.setText("X");
                lblJogada.setForeground(corPadraoX);
                telaJogo_btnReiniciar.requestFocus(); //Focus no botão Reiniciar após o click
                proximoJogador = false;
            } else {
                lblJogada.setText("O");
                lblJogada.setForeground(corPadraoO);
                telaJogo_btnReiniciar.requestFocus();
                proximoJogador = true;
            }

            telaJogo.repaint(); //Recria o layout
            verificaVencedor();

            contadorXeO++;
            if (contadorXeO == 9 /*|| jogoEncerrado == true*/) {
                //Verifica se for igual a -1, deu empate
                //Impede que na ultima jogada (9) não seja uma vitória e empate ao mesmo tempo
                if (vitoria == -1) {
                    removerEventoXeOListener();
                    telaJogo_pontoEmpate.setText((pontoEmpate + 1) + "");
                    JOptionPane.showMessageDialog(null, "Deuuu Velha!!!\nPartida empatada ( ͡° ͜ʖ ͡°)", "Ninguém ganhou. Empate!", JOptionPane.INFORMATION_MESSAGE, imgE);
                    contadorXeO = 0;
                    //Maior que -1 é considerado vitória
                } else if (vitoria > -1) {
                    removerEventoXeOListener();
                    contadorXeO = 0;
                }
            }
        }

        return jogoEncerrado;
    }

    //Função para definir o código do jogador que agora irá jogar de acordo com sua função
    private void JogadorAtual() {

        if (proximoJogador == true) {
            telaJogo_jogadorAtual.setText("X");
            telaJogo_jogadorAtual.setForeground(corPadraoX);
        } else {
            telaJogo_jogadorAtual.setText("O");
            telaJogo_jogadorAtual.setForeground(corPadraoO);
        }
        repaint(); //Recria o layout
    }

    //Função para bloquear o click do jogador quando o jogo é interrompido
    private void removerEventoXeOListener() {
        for (JLabel telaJogo_travaLabel : telaJogo_lbls) {
            telaJogo_travaLabel.removeMouseListener(XeOListener);
        }
    }

    //Evento quando o usuário clica nos botões do mouse em um objeto
    MouseListener XeOListener = new MouseListener() {
        @Override
        public void mousePressed(MouseEvent e) {
            JLabel clicaLabel = (JLabel) e.getSource();
            if (jogoEncerrado == false) {
                if (computador == false) {
                    verificaJogadaAtual(clicaLabel);
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    };

    //Função ao iniciar uma nova seleção para zerar o tabuleiro e anexar o mouse ao objeto
    private void iniciarNovoJogo() {
        vitoria = -1; //Redefine para -1 para que possa ser validada em empates
        jogoEncerrado = false;
        contadorXeO = 0;

        JogadorAtual();

        telaJogo_lbls[0].setOpaque(false);
        telaJogo_lbls[0].setText("");
        telaJogo_lbls[1].setOpaque(false);
        telaJogo_lbls[1].setText("");
        telaJogo_lbls[2].setOpaque(false);
        telaJogo_lbls[2].setText("");
        telaJogo_lbls[3].setOpaque(false);
        telaJogo_lbls[3].setText("");
        telaJogo_lbls[4].setOpaque(false);
        telaJogo_lbls[4].setText("");
        telaJogo_lbls[5].setOpaque(false);
        telaJogo_lbls[5].setText("");
        telaJogo_lbls[6].setOpaque(false);
        telaJogo_lbls[6].setText("");
        telaJogo_lbls[7].setOpaque(false);
        telaJogo_lbls[7].setText("");
        telaJogo_lbls[8].setOpaque(false);
        telaJogo_lbls[8].setText("");

        repaint();

        telaJogo_lbls[0].addMouseListener(XeOListener);
        telaJogo_lbls[1].addMouseListener(XeOListener);
        telaJogo_lbls[2].addMouseListener(XeOListener);
        telaJogo_lbls[3].addMouseListener(XeOListener);
        telaJogo_lbls[4].addMouseListener(XeOListener);
        telaJogo_lbls[5].addMouseListener(XeOListener);
        telaJogo_lbls[6].addMouseListener(XeOListener);
        telaJogo_lbls[7].addMouseListener(XeOListener);
        telaJogo_lbls[8].addMouseListener(XeOListener);

    }

    //criarExibirInterfaceGrafica() do construtor que irá chamar a função Main ao criar um objeto da classe
    public jogo_da_veia() {
        criarExibirInterfaceGrafica();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    //Função constrói tudo no jogo e determina o que acontecerá quando o usuário interagir com o conteúdo da janela
    private void criarExibirInterfaceGrafica() {

        //Funções que irão criar todas as telas do programa
        criarTelaMultiPlayer();
        criarTelaJogo();

        //As telas ficaram atrás umas das outras, CardLayout
        CardLayout painel = new CardLayout();
        Container container = getContentPane();
        container.setLayout(painel);

        //Add todas as telas (containers) na janela
        add(telaMultiPlayer);
        add(telaJogo);

        //Nome para cada tela (container) para poder especificar a tela que será exibida pelo seu nome
        container.getLayout().addLayoutComponent("telaMultiPlayer", telaMultiPlayer);
        container.getLayout().addLayoutComponent("telaJogo", telaJogo);

        setCoresObjetos(corPadraoTexto, corPadraoBotao);
        defineFonte(fontePadrao);

        //Torná as 9 Labels clicáveis ​​(XeOListener) com o objeto
        for (JLabel telaJogo_PainelLabel : telaJogo_lbls) {
            telaJogo_PainelLabel.addMouseListener(XeOListener);
        }

        //Para MultiPlayer a telaJogo abre a telaMultiPlayer_btnIncial
        telaMultiPlayer_btnIncial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaJogo_pontoJogador1.setForeground(Color.green);
                telaJogo_pontoJogador2.setForeground(Color.red);
                telaJogo_pontoEmpate.setForeground(Color.cyan);
                telaJogo_pontoEmpate.setFont(fontePadraoPontos);
                telaJogo_pontoJogador1.setFont(fontePadraoPontos);
                telaJogo_pontoJogador2.setFont(fontePadraoPontos);
                telaJogo_nomeJogador1.setText("X - " + telaMultiPlayer_nomeJogador1.getText());
                telaJogo_nomeJogador2.setText("O - " + telaMultiPlayer_nomeJogador2.getText());
                computador = false;
                telaJogo_pontoJogador1.setText("0");
                telaJogo_pontoJogador2.setText("0");
                telaJogo_pontoEmpate.setText("0");
                painel.show(container, "telaJogo");
            }
        });

        //Encerra a aplicação
        telaMultiPlayer_btnEncerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //Dependendo da tela que foi aberta, telaMultiPlayer ou singlePlayerPage retorna para a tela telaJogo_btnVolta
        telaJogo_btnVolta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (computador == false) {
                    painel.show(container, "telaMultiPlayer");
                }
                iniciarNovoJogo();
            }
        });

        //Encontrado no tabuleiro O e X remove qualquer telaJogo_btnReiniciar
        telaJogo_btnReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarNovoJogo();
            }
        });

        telaJogo_btnReiniciar.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent event) {
            }

            @Override
            public void keyPressed(KeyEvent event) {
            }

            @Override
            public void keyReleased(KeyEvent event) {
                if (event.isControlDown() && (event.getKeyCode() == KeyEvent.VK_R)) { //Reiniciar
                    iniciarNovoJogo();

                }
            }
        });

        //Define algumas propriedades da janela e torna elas visíveis
        setTitle("Jogo da Velha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Criará a janela criarExibirInterfaceGrafica() e assim a função Main será chamada
                new jogo_da_veia();
            }
        });
    }

}
