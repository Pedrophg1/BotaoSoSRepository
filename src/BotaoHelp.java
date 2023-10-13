import javax.swing.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BotaoHelp extends JButton {
    private List<String> funcionalidades;




    public BotaoHelp(List<String> funcionalidades) {
        this.funcionalidades = funcionalidades;
    }

    public BotaoHelp(String ajuda, String s) {
    }

    public void exibirAjuda() {
        StringBuilder mensagem = new StringBuilder("Funcionalidades disponíveis:\n");
        for (String funcionalidade : funcionalidades) {
            mensagem.append("- ").append(funcionalidade).append("\n");
        }
        JOptionPane.showMessageDialog(null, mensagem.toString(), "Ajuda", JOptionPane.INFORMATION_MESSAGE);
    }
}
