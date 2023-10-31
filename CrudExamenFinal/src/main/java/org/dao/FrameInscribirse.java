package org.dao;
import entity.CursosEntity;
import entity.EstudiantesEntity;
import entity.InscripcionesEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.JList;
import java.awt.*;
import java.sql.Date;
import java.util.List;


public class FrameInscribirse extends JFrame
{

    public static void main(String[] args)
    {
        FrameInscribirse frame = new FrameInscribirse();
        frame.setVisible(true);
    }
    public FrameInscribirse()
    {

        setTitle("Inscribirse");
        setSize(670, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon backgroundImage = new ImageIcon("C:\\proyectos\\CrudExamenFinal\\src\\main\\java\\images\\inscribirse.png");

        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        setContentPane(backgroundLabel);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(325, 130, 110, 30); // x=200, y=200, ancho=100, alto=30
        txtNombre.setBackground(new Color(40, 43, 158));
        txtNombre.setForeground(Color.WHITE);
        txtNombre.setBorder(null);

        JTextField txtApellido = new JTextField();
        txtApellido.setBounds(525, 127, 110, 30); // x=200, y=200, ancho=100, alto=30
        txtApellido.setBackground(new Color(40, 43, 158));
        txtApellido.setForeground(Color.WHITE);
        txtApellido.setBorder(null);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<CursosEntity> cursos = entityManager.createQuery("SELECT c FROM CursosEntity c").getResultList();
        entityManager.close();

        JComboBox<String> listModel = new JComboBox<>();
        for (CursosEntity curso : cursos) {
            listModel.addItem(curso.getNombreCurso());
        }


        listModel.setBounds(325, 185, 110, 30); // x=200, y=200, ancho=100, alto=30




        JButton btInscribirse = new JButton("Inscribirse");
        btInscribirse.setBounds(450, 185, 110, 30); // x=200, y=200, ancho=100, alto=30

        //ActionListener del boton inscribirse
        btInscribirse.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String curso = listModel.getSelectedItem().toString();

            if (nombre.isEmpty() || apellido.isEmpty() || curso.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
            }
            else {

                EntityManagerFactory entityManagerFactory1 = Persistence.createEntityManagerFactory("default");
                EntityManager entityManager1 = entityManagerFactory1.createEntityManager();
                EntityTransaction transaction = entityManager1.getTransaction();


                try
                {
                    transaction.begin();
                    EstudiantesEntity estudiante = new EstudiantesEntity();
                    estudiante.setNombre(nombre);
                    estudiante.setApellido(apellido);
                    estudiante.setEmail(nombre + apellido + "@gmail.com");
                    entityManager1.persist(estudiante);
                    transaction.commit();

                    transaction.begin();
                    InscripcionesEntity inscripcion = new InscripcionesEntity();
                    inscripcion.setFechaInscripcion(new Date(System.currentTimeMillis())); // Establece la fecha de inscripción
                    inscripcion.setIdEstudiante(estudiante.getIdEstudiante());
                    inscripcion.setIdCurso(cursos.get(listModel.getSelectedIndex()).getIdCurso());
                    entityManager1.persist(inscripcion);
                    transaction.commit();

                }
                finally
                {
                    if (transaction.isActive())
                    {
                        transaction.rollback();
                    }
                    entityManager1.close();
                    entityManagerFactory1.close();
                }


                JOptionPane.showMessageDialog(null, "Inscripcion exitosa");
                dispose();



            }
        });

        add(txtApellido);
        add(txtNombre);
        add(listModel);
        add(btInscribirse);

    }


}
