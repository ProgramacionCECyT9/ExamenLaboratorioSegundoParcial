package Swing;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Formulario implements ActionListener{
    
    JFrame framePrincipal = new JFrame("Formulario de Datos Personales");

    Container contenedor = framePrincipal.getContentPane();

    JLabel aMaternoLabel =  new JLabel("Apellido Materno");
    JLabel aPaternoLabel = new JLabel("Apellido Paterno");
    JLabel nombreLabel = new JLabel("Nombre");
    JLabel sexoLabel = new JLabel("Sexo");
    JLabel correoLabel = new JLabel("Email");


    JTextField aMaternoField = new JTextField();
    JTextField aPaternoField = new JTextField();
    JTextField nombreField = new JTextField();
    JTextField correoField = new JTextField();

    String [] sexoOptions = {};
    JComboBox sexoCombo = new JComboBox(sexoOptions);

    JButton enviarButton =  new JButton("Enviar");

    public Formulario(){

        contenedor.setLayout(null);
        framePrincipal.setSize(500,400);
        framePrincipal.setLocationRelativeTo(null);
        framePrincipal.setVisible(true);
        framePrincipal.setResizable(false);
        framePrincipal.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        aMaternoLabel.setBounds(50,100,130,13);
        contenedor.add(aMaternoLabel);

        aPaternoLabel.setBounds(50,150,130,13);
        contenedor.add(aPaternoLabel);

        correoLabel.setBounds(50,200,150,13);
        contenedor.add(correoLabel);

        nombreLabel.setBounds(50,50,130,13);
        contenedor.add(nombreLabel);

        sexoLabel.setBounds(50,250,130,13);
        contenedor.add(sexoLabel);

        aMaternoField.setBounds(220,95,200,20);
        contenedor.add(aMaternoField);

        aPaternoField.setBounds(220,145,200,20);
        contenedor.add(aPaternoField);

        nombreField.setBounds(220,45,200,20);
        contenedor.add(nombreField);

        sexoCombo.setBounds(220,245,200,20);
        contenedor.add(sexoCombo);

        correoField.setBounds(220,195,200,20);
        contenedor.add(correoField);

        enviarButton.setBounds(100,300,300,30);
        contenedor.add(enviarButton);
        enviarButton.addActionListener(this);

        BD.Conexion enlaceDB = new  BD.Conexion();
        
        // try{
            
        //     enlaceDB.conectarDB();
        //     ResultSet rsSexo = enlaceDB.consulta("call sp_getGenero()"); 
            
        //     while(rsSexo.next()){
        //         sexoCombo.addItem(rsSexo.getString("genero"));
        //     }

        // } catch(SQLException exx){
        //     System.out.println(exx);
        // }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String nombre = nombreField.getText();
        String amaterno = aMaternoField.getText();
        String apaterno = aPaternoField.getText();
        String correo = correoField.getText();

        Object sexoSelected = sexoCombo.getSelectedItem(); 
        String sexo = String.valueOf(sexoSelected);

        if (e.getSource() == enviarButton) {

        	if (nombre.equals("")||amaterno.equals("")||apaterno.equals("")||correoField.equals("")||sexo.equals("")){

                JOptionPane.showMessageDialog(framePrincipal,"No Se Admiten Campos Vacios","Alerta",JOptionPane.ERROR_MESSAGE);

            } else {

                try {

                    BD.Conexion enlaceDB = new  BD.Conexion();
                    enlaceDB.conectarDB();
                    ResultSet resultSet = enlaceDB.consulta("");
                    
                } catch(SQLException exx){

                    System.out.println(exx);
                }
            }
        }
    }
}
