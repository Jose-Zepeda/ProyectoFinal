package org.dao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FrameLobby extends JFrame {


    public FrameLobby() {
        setTitle("Lobby");
        setSize(670, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon backgroundImage = new ImageIcon("C:\\proyectos\\CrudExamenFinal\\src\\main\\java\\images\\Lobby.png");

        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        setContentPane(backgroundLabel);

        JButton btagregar = new JButton("");
        btagregar.setBounds(118, 77, 140, 140);
        btagregar.setBorder(new EmptyBorder(0, 0, 0, 0));
        btagregar.setOpaque(false); // Establece la opacidad en cero
        btagregar.setContentAreaFilled(false); // No pinta el área del contenido
        btagregar.setBorderPainted(false); // No pinta el borde


        JButton btconsultar = new JButton("");
        btconsultar.setBounds(384, 77, 140, 140);
        btconsultar.setBorder(new EmptyBorder(0, 0, 0, 0));
        btconsultar.setOpaque(false); // Establece la opacidad en cero
        btconsultar.setContentAreaFilled(false); // No pinta el área del contenido
        btconsultar.setBorderPainted(false); // No pinta el borde


        btagregar.addActionListener(e -> {
            FrameInscribirse frame = new FrameInscribirse();
            frame.setVisible(true);
        });

        btconsultar.addActionListener(e -> {
            FrameConsultar frame = new FrameConsultar();
            frame.setVisible(true);
        });

        add(btagregar);
        add(btconsultar);
    }

    public static void main(String[] args)
    {
        FrameLobby frame = new FrameLobby();
        frame.setVisible(true);
    }
}
