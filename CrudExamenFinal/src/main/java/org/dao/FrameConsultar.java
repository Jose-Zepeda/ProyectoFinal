package org.dao;
import entity.CursosEntity;
import entity.EstudiantesEntity;
import entity.InscripcionesEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class FrameConsultar extends JFrame
{

    public static void main(String[] args)
    {
        FrameConsultar frame = new FrameConsultar();
        frame.setVisible(true);
    }
    public FrameConsultar()
    {
        setTitle("Consultar");
        setSize(580, 210);
        setLocationRelativeTo(null);
        setResizable(false);
        ImageIcon backgroundImage = new ImageIcon("C:\\proyectos\\CrudExamenFinal\\src\\main\\java\\images\\frameconsultar.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        setContentPane(backgroundLabel);


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<EstudiantesEntity> estudiantes = entityManager.createQuery("SELECT c FROM EstudiantesEntity c").getResultList();
        entityManager.close();

        JComboBox<Integer> listModel = new JComboBox<>();
        for (EstudiantesEntity estudiante : estudiantes) {
            listModel.addItem(estudiante.getIdEstudiante());
        }
        listModel.setBounds(350, 10, 50, 30); // x=200, y=200, ancho=100, alto=30

        JButton btnConsultar = new JButton();
        btnConsultar.setText("Consultar");
        btnConsultar.setBounds(400, 10, 100, 30); // x=200, y=200, ancho=100, alto=30

        JLabel lblNombre = new JLabel();
        lblNombre.setBounds(20, 110, 100, 30); // x=200, y=200, ancho=100, alto=30
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setText("");

        JLabel lblApellido = new JLabel();
        lblApellido.setBounds(160, 110, 100, 30); // x=200, y=200, ancho=100, alto=30
        lblApellido.setForeground(Color.WHITE);
        lblApellido.setText("");

        JLabel lblCurso = new JLabel();
        lblCurso.setBounds(320, 110, 100, 30); // x=200, y=200, ancho=100, alto=30
        lblCurso.setForeground(Color.WHITE);
        lblCurso.setText("");

        JLabel lblEmail = new JLabel();
        lblEmail.setBounds(440, 110, 100, 30); // x=200, y=200, ancho=100, alto=30
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setText("");

        //ACTION LISTENER PARA EL BOTON CONSULTAR
        btnConsultar.addActionListener(e -> {
            EntityManagerFactory entityManagerFactory1 = Persistence.createEntityManagerFactory("default");
            EntityManager entityManager1 = entityManagerFactory1.createEntityManager();

            EstudiantesEntity estudiante = entityManager1.find(EstudiantesEntity.class, listModel.getSelectedItem());
            lblNombre.setText(estudiante.getNombre());
            lblApellido.setText(estudiante.getApellido());
            lblEmail.setText(estudiante.getEmail());

            InscripcionesEntity ins = entityManager1.find(InscripcionesEntity.class, estudiante.getIdEstudiante());
            CursosEntity curso = entityManager1.find(CursosEntity.class, ins.getIdCurso());
            lblCurso.setText(curso.getNombreCurso());
            entityManager1.close();
        });


        add(listModel);
        add(btnConsultar);
        add(lblEmail);
        add(lblNombre);
        add(lblApellido);
        add(lblCurso);
    }
}
