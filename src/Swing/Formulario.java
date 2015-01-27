package Swing;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

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
        
        aPaternoLabel.setBounds(50,100,130,13);
        contenedor.add(aMaternoLabel);

        aMaternoLabel.setBounds(50,150,130,13);
        contenedor.add(aPaternoLabel);

        correoLabel.setBounds(50,200,150,13);
        contenedor.add(correoLabel);

        nombreLabel.setBounds(50,50,130,13);
        contenedor.add(nombreLabel);

        sexoLabel.setBounds(50,250,130,13);
        contenedor.add(sexoLabel);

        aPaternoField.setBounds(220,93,200,25);
        contenedor.add(aMaternoField);

        aMaternoField.setBounds(220,143,200,25);
        contenedor.add(aPaternoField);

        nombreField.setBounds(220,43,200,25);
        contenedor.add(nombreField);

        sexoCombo.setBounds(220,243,200,25);
        contenedor.add(sexoCombo);

        correoField.setBounds(220,193,200,25);
        contenedor.add(correoField);

        enviarButton.setBounds(100,300,300,30);
        contenedor.add(enviarButton);
        enviarButton.addActionListener(this);

        BD.Conexion enlaceDB = new  BD.Conexion();
        
        try{
            
            enlaceDB.conectarDB();
            ResultSet rsSexo = enlaceDB.consulta("call sp_getGenero()");
            System.out.println("Traidos Los Generos Correctamente");
            
            while(rsSexo.next()){
                sexoCombo.addItem(rsSexo.getString("genero"));
            }

        } catch(SQLException exx){
            System.out.println(exx);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String nombre = nombreField.getText();
        String apaterno = aPaternoField.getText();
        String amaterno = aMaternoField.getText();
        String correo = correoField.getText();

        Object sexoSelected = sexoCombo.getSelectedItem(); 
        String sexo = String.valueOf(sexoSelected);

        if (e.getSource() == enviarButton) {

        	if (nombre.equals("")||amaterno.equals("")||apaterno.equals("")||correoField.equals("")||sexo.equals("")){

                JOptionPane.showMessageDialog(framePrincipal,"Hay Campos Vacios","Warning",JOptionPane.ERROR_MESSAGE);

            } else {

                try {

                    BD.Conexion enlaceDB = new  BD.Conexion();
                    enlaceDB.conectarDB();
                    ResultSet resultSet = enlaceDB.consulta("call sp_postDatosPersonales('"+nombre+"','"+apaterno+"','"+amaterno+"','"+correo+"','"+sexo+"');");
                    System.out.println("Dado de alta con exito");
                    
                } catch(SQLException exx){

                    System.out.println(exx);
                }
                
                try{
                    
                    Element elemento = new Element ("Datos_Personales");
                    elemento.detach();
                    Document doc = new Document(elemento);
                    
                    Element nombreElement= new Element("nombre");
                    nombreElement.addContent(new Element("nombre_id").setText("1"));
                    nombreElement.addContent(new Element("nombre_content").setText(nombre));
                    
                    doc.getRootElement().addContent(nombreElement);

                    Element apaternoElement= new Element("apaterno");
                    apaternoElement.addContent(new Element("apaterno_id").setText("2"));
                    apaternoElement.addContent(new Element("apaterno_content").setText(apaterno));
                    
                    doc.getRootElement().addContent(apaternoElement);

                    Element amaternoElement= new Element("amaterno");
                    amaternoElement.addContent(new Element("amaterno_id").setText("3"));
                    amaternoElement.addContent(new Element("amaterno_content").setText(amaterno));
                    
                    doc.getRootElement().addContent(amaternoElement);

                    Element correoElement= new Element("correo");
                    correoElement.addContent(new Element("correo_id").setText("4"));
                    correoElement.addContent(new Element("correo_content").setText(correo));
                    
                    doc.getRootElement().addContent(correoElement);

                    Element sexoElement= new Element("sexo");
                    sexoElement.addContent(new Element("sexo_id").setText("5"));
                    sexoElement.addContent(new Element("sexo_content").setText(sexo));
                    
                    doc.getRootElement().addContent(sexoElement);
                    
                    XMLOutputter xmlOutput = new XMLOutputter();

                    xmlOutput.setFormat(Format.getPrettyFormat());
                    xmlOutput.output(doc, new FileWriter("Datos.xml"));

                    System.out.println("Pum Paps Archivo Guardado");

                } catch (Exception io) {
                    
                    System.out.println(io.getMessage());
                }
            }
        }
    }
}
