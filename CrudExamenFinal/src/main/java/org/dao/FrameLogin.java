package org.dao;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FrameLogin extends JFrame {

    public static void main(String[] args) {
        FrameLogin frame = new FrameLogin();
        frame.setVisible(true);
    }
    public FrameLogin() {
        setTitle("Login");
        setSize(400, 670);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        ImageIcon backgroundImage = new ImageIcon("C:\\proyectos\\CrudExamenFinal\\src\\main\\java\\images\\logindefinitivo.png");

        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        setContentPane(backgroundLabel);

        // Crea y coloca elementos en coordenadas específicas

        JButton btlogin = new JButton("Login");
        btlogin.setBounds(145, 400, 100, 30); // x=200, y=200, ancho=100, alto=30
        btlogin.setBorder(new EmptyBorder(0, 0, 0, 0));

        JTextField txtUsername = new JTextField();
        txtUsername.setBounds(40, 240, 300, 30); // x=200, y=200, ancho=100, alto=30
        txtUsername.setBackground(new Color(40, 43, 158));
        txtUsername.setForeground(Color.WHITE);
        txtUsername.setBorder(null);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(40, 335, 300, 30); // x=200, y=200, ancho=100, alto=30
        txtPassword.setBackground(new Color(40, 43, 158));
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setBorder(null);

        // Asegúrate de que el layout del JFrame esté configurado como nulo
        setLayout(null);

        // Agrega los botones al JFrame

        add(btlogin);
        add(txtUsername);
        add(txtPassword);

        // Centra el JFrame en la pantalla
        setLocationRelativeTo(null);

        //Action listener del boton login
        btlogin.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            if (username.equals("admin") && password.equals("1234")) {
                JOptionPane.showMessageDialog(null, "Login exitoso");
                dispose();
                FrameLobby frame = new FrameLobby();
                frame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            }
        });
    }
}

