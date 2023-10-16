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
                    JOptionPane.showMessageDialog(null, "SOS enviado com sucesso!");
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
