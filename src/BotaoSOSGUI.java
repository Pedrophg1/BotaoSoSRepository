import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class BotaoSOSGUI {
    private JFrame frame;

    public BotaoSOSGUI() {
        frame = new JFrame("Sistema de SOS da UFS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 420);
        frame.setLocationRelativeTo(null);

        ImageIcon frameIcon = new ImageIcon("assets/brasao_icon.png");
        frame.setIconImage(frameIcon.getImage());

        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new BorderLayout());

        ImageIcon logoIcone = new ImageIcon("assets/logo_ufs.png");

        Image logoImagem = logoIcone.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
        logoIcone = new ImageIcon(logoImagem);

        JPanel painelLogo = new JPanel();
        painelLogo.add(new JLabel(logoIcone));
        painelLogo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelSuperior.add(painelLogo, BorderLayout.WEST);

        JLabel tituloLabel = new JLabel("Sistema de SOS");
        tituloLabel.setFont(new Font("Montserrat", Font.BOLD, 24));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(0, 65, 0, 0));

        painelSuperior.add(tituloLabel, BorderLayout.CENTER);

        JLabel labelSelecioneLugar = new JLabel("Selecione abaixo o local do problema:");
        labelSelecioneLugar.setFont(new Font("Montserrat", Font.BOLD, 14));
        labelSelecioneLugar.setHorizontalAlignment(JLabel.CENTER);
        painelSuperior.add(labelSelecioneLugar, BorderLayout.SOUTH);

        frame.add(painelSuperior, BorderLayout.NORTH);

        JPanel painelListaLugares = new JPanel();
        String[] lugares = { "Entrada Principal", "Entrada de Automóveis", "Didática I", "Didática II", "Didática III",
                "Didática IV", "Didática V", "Didática VI", "Didática VII", "Resun", "Biblioteca", "Auditório",
                "Reitoria", "Laboratório" };
        JComboBox<String> listaLugares = new JComboBox<>(lugares);
        listaLugares.setPreferredSize(new Dimension(400, listaLugares.getPreferredSize().height));
        painelListaLugares.add(listaLugares);

        JPanel painelListaOcorrencias = new JPanel(new GridLayout(2, 1));
        JLabel labelSelecionarOcorrencia = new JLabel("Selecione abaixo o tipo de ocorrência:");
        labelSelecionarOcorrencia.setFont(new Font("Montserrat", Font.BOLD, 14));
        labelSelecionarOcorrencia.setHorizontalAlignment(JLabel.CENTER);
        painelListaOcorrencias.add(labelSelecionarOcorrencia, BorderLayout.NORTH);

        String[] ocorrencias = { "Ocorrência 1", "Ocorrência 2", "Ocorrência 3", "Ocorrência 4", "Ocorrência 5" };
        JComboBox<String> listaOcorrencias = new JComboBox<>(ocorrencias);
        listaOcorrencias.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        listaOcorrencias.setPreferredSize(new Dimension(400, listaOcorrencias.getPreferredSize().height));
        painelListaOcorrencias.add(listaOcorrencias);

        JPanel painelListas = new JPanel();

        painelListas.add(painelListaLugares);
        painelListas.add(painelListaOcorrencias);

        frame.add(painelListas);

        JPanel sosButtonPanel = new JPanel();
        RedRoundButtonSOS botaoSOS = new RedRoundButtonSOS("SOS");
        botaoSOS.setFont(new Font("Montserrat", Font.BOLD, 24));
        botaoSOS.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        botaoSOS.setEnabled(false);
        sosButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        sosButtonPanel.add(botaoSOS);

        frame.add(sosButtonPanel, BorderLayout.SOUTH);

        JButton botaoAjuda = new JButton("Ajuda");

        JButton botaoVerMapa = new JButton("Ver Mapa");

        JPanel botaoAjudaPanel = new JPanel();
        botaoAjuda.setBackground(new Color(0, 58, 106));
        botaoAjuda.setForeground(Color.WHITE);
        botaoAjudaPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        botaoAjudaPanel.add(botaoVerMapa);
        botaoAjudaPanel.add(botaoAjuda);
        painelSuperior.add(botaoAjudaPanel, BorderLayout.EAST);

        listaLugares.setSelectedItem(null);
        listaOcorrencias.setSelectedItem(null);

        listaOcorrencias.setEnabled(false);
        listaLugares.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listaLugares.getSelectedItem() != null) {
                    listaOcorrencias.setEnabled(true);
                } else {
                    listaOcorrencias.setEnabled(false);
                    botaoSOS.setEnabled(false);
                }
            }
        });

        listaOcorrencias.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                botaoSOS.setEnabled(listaOcorrencias.getSelectedItem() != null);
            }
        });

        botaoSOS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String matricula = JOptionPane.showInputDialog(frame, "Digite sua matrícula:");

                if (isNumeric(matricula) && matricula.length() == 12) {
                    String lugarProblema = (String) listaLugares.getSelectedItem();

                    JTextArea descricaoProblemaTextArea = new JTextArea();
                    descricaoProblemaTextArea.setWrapStyleWord(true);
                    descricaoProblemaTextArea.setLineWrap(true);
                    descricaoProblemaTextArea.setFont(new Font("Montserrat", Font.BOLD, 12));
                    descricaoProblemaTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    descricaoProblemaTextArea.setPreferredSize(new Dimension(450, 150));

                    JScrollPane scrollPane = new JScrollPane(descricaoProblemaTextArea);

                    int result = JOptionPane.showConfirmDialog(frame, scrollPane,
                            "Descreva o problema em " + lugarProblema + ":", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        String descricaoProblema = descricaoProblemaTextArea.getText();
                        JOptionPane.showMessageDialog(frame, "SOS enviado com sucesso!");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Matrícula inválida. Certifique-se de que seja uma matrícula válida.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoAjuda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame ajudaFrame = new JFrame("Ajuda");
                ajudaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ajudaFrame.setSize(500, 430);
                ajudaFrame.setLocationRelativeTo(null);
                ImageIcon frameIcon = new ImageIcon("assets/brasao_icon.png");
                ajudaFrame.setIconImage(frameIcon.getImage());

                JTextArea textoAjuda = new JTextArea();
                textoAjuda.setWrapStyleWord(true);
                textoAjuda.setLineWrap(true);
                textoAjuda.setEditable(false);
                textoAjuda.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                textoAjuda.setFont(new Font("Montserrat", Font.BOLD, 14));

                textoAjuda.setText("Bem-vindo(a) ao Sistema de SOS da UFS!\n\n"
                        + "Este sistema permite que você solicite ajuda em locais específicos da universidade.\n\n"
                        + "Para solicitar ajuda:\n"
                        + "1. Selecione o local do problema e o tipo de ocorrência nos menus suspensos.\n"
                        + "2. Preencha o campo com sua matrícula e em seguida preencha o próximo campo descrevendo o problema.\n"
                        + "3. Clique no botão 'SOS' para enviar sua solicitação de ajuda.\n\n"
                        + "Sua solicitação será enviada para o(a) segurança mais próximo de você, basta aguardar.\n\n"
                        + "Você também pode clicar no botão 'Ver Mapa' para visualizar o mapa da universidade.\n\n"
                        + "Para obter mais informações, entre em contato com o suporte.");

                JPanel painelAjuda = new JPanel(new BorderLayout());
                painelAjuda.add(new JScrollPane(textoAjuda), BorderLayout.CENTER);
                ajudaFrame.add(painelAjuda);
                ajudaFrame.setVisible(true);
            }
        });

        botaoVerMapa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    File imageFile = new File("assets/mapa_ufs.png");
                    BufferedImage image = ImageIO.read(imageFile);

                    JFrame mapaFrame = new JFrame("Mapa da UFS");
                    mapaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    mapaFrame.setSize(600, 800);
                    mapaFrame.setLocationRelativeTo(null);
                    ImageIcon frameIcon = new ImageIcon("assets/brasao_icon.png");
                    mapaFrame.setIconImage(frameIcon.getImage());

                    JPanel mapaPanel = new JPanel() {
                        @Override
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                        }
                    };

                    mapaFrame.add(mapaPanel, BorderLayout.CENTER);
                    mapaFrame.setVisible(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Erro ao carregar a imagem do mapa.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }

    private boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BotaoSOSGUI();
            }
        });
    }
}

class RedRoundButtonSOS extends JButton {
    public RedRoundButtonSOS(String text) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setForeground(Color.WHITE);
        setPreferredSize(new Dimension(100, 100));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(new Color(255, 102, 102));
        } else {
            g.setColor(new Color(255, 0, 0));
        }
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(255, 0, 0));
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }
}
