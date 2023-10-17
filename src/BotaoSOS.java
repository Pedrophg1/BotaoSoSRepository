import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BotaoSOS extends JButton {

    public BotaoSOS(String text) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setForeground(Color.WHITE);
        setPreferredSize(new Dimension(100, 100));
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextArea descricaoProblemaTextArea = new JTextArea();
                descricaoProblemaTextArea.setWrapStyleWord(true);
                descricaoProblemaTextArea.setLineWrap(true);
                descricaoProblemaTextArea.setFont(new Font("Montserrat", Font.BOLD, 12));
                descricaoProblemaTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                descricaoProblemaTextArea.setPreferredSize(new Dimension(450, 150));

                JScrollPane scrollPane = new JScrollPane(descricaoProblemaTextArea);

                int result = JOptionPane.showConfirmDialog(null, scrollPane,
                        "(Opcional) Descreva seu problema:", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String descricaoProblema = descricaoProblemaTextArea.getText();
                    JOptionPane.showMessageDialog(null, "SOS enviado com sucesso!");
                }
            }
        });
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
