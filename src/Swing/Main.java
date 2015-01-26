package Swing;

import java.awt.EventQueue;

public class Main {
    
   public static void main(String[] args) {

   		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
              Formulario form = new Formulario();
            }
        });
    }    
}
