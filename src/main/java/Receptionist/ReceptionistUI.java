package Receptionist;

import java.awt.FlowLayout;
import javax.swing.*;

public class ReceptionistUI extends JPanel{

    public ReceptionistUI(JFrame aJFrame){
      createUI(aJFrame);
    }
    private void createUI(JFrame f){

      JPanel panel = new JPanel();
      JLabel label = new JLabel("Receptionist Page");
      panel.add(label);
      panel.setBounds(200,100,100,200);
      f.getContentPane().add(panel);

    }
}
